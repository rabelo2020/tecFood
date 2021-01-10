package com.rabelo.tecfood.api.exceptionhandler;



import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.rabelo.tecfood.domain.service.exception.EntidadeEmUsoException;
import com.rabelo.tecfood.domain.service.exception.EntidadeNaoEncontradaException;
import com.rabelo.tecfood.domain.service.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	//Tratando uma URL, com paramentro inválido
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
	        HttpStatus status, WebRequest request) {
	    
	    if (ex instanceof MethodArgumentTypeMismatchException) {
	        return handleMethodArgumentTypeMismatch(
	                (MethodArgumentTypeMismatchException) ex, headers, status, request);
	    }

	    return super.handleTypeMismatch(ex, headers, status, request);
	}
	
	//Tratando uma URL, com paramentro inválido
	private ResponseEntity<Object> handleMethodArgumentTypeMismatch(
	        MethodArgumentTypeMismatchException ex, HttpHeaders headers,
	        HttpStatus status, WebRequest request) {

	    ProblemType problemType = ProblemType.PARAMETRO_INVALIDO;

	    String detail = String.format("O parâmetro de URL '%s' recebeu o valor '%s', "
	            + "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
	            ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());

	    Problem problem = createProblemBuilder(status, problemType, detail).build();

	    return handleExceptionInternal(ex, problem, headers, status, request);
	}	
	
	
	//Tratando uma Exception, ao enviar uma Propiedade na requisição não existente em uma Entidade
	private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request){
		
		String path = joinPath(ex.getPath());
		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		String detail = String.format("A propriedade '%s' não existe "
				+ "Corrija ou remova essa propriedade e tente novamente", path);
		
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	
//Sobrescrevendo esse metodo, para exibir, a causa da Exception
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		Throwable rootCause = ExceptionUtils.getRootCause(ex);
		
		if (rootCause instanceof InvalidFormatException) {
			
			System.out.println("InvalidFormatException");
		return handleInvalidFormat((InvalidFormatException)rootCause, headers, status, request);
		
		}else if (rootCause instanceof PropertyBindingException) {
			
			System.out.println("PropertyBindingException");
			return handlePropertyBindingException((PropertyBindingException)rootCause, headers, status, request);
		}
		
		//Erro de Sintaxe, ao enviar uma Requisição
		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		String detail = "O corpo da requisição está inválido. Verifique erro de sintaxe";
		
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	

	private ResponseEntity<Object> handleInvalidFormat(InvalidFormatException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
	String path = joinPath(ex.getPath());
	
//		ex.getPath().forEach(ref -> System.out.println(ref.getFieldName()));
		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		String detail = String.format("A propiedade '%s' recebeu o valor '%s' "
				+ "que é de um tipo inválido. Corrija  e informe um valor  compatível com o tipo %s !", path,
				ex.getValue(), ex.getTargetType().getSimpleName());
		
		Problem problem = createProblemBuilder(status, problemType, detail).build();

		return handleExceptionInternal(ex, problem, headers, status, request);
	}


	@ExceptionHandler({EntidadeNaoEncontradaException.class})
	public ResponseEntity<?> tratarEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, WebRequest request){
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.ENTIDADE_NAO_ENCOTRADA;
		String detail = ex.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		//String detail = ex.getMessage();
		
		/*Problem problem = Problem.builder()
				          .status(status.value())
				          .type("https://algafood.com.br/entidade-nao-encotrada")
				          .title("Entidade não encontrada")
				          .detail(ex.getMessage())
				          .build(); */
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
				
		/*Problema problema = Problema.builder()
				            .dataHora(LocalDateTime.now())
				            .mensagem(e.getMessage())
		                    .build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problema); */
	}
	
	@ExceptionHandler({NegocioException.class})
	public ResponseEntity<?> tratarNegocioException(NegocioException ex, WebRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ProblemType problemType = ProblemType.ERRO_NEGOCIO;
		String detail = ex.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
		
		//return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
		/*Problema problema = Problema.builder()
				           .dataHora(LocalDateTime.now())
				           .mensagem(e.getMessage())
				           .build();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problema);*/
	}
	
	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> tratarEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request) {
		HttpStatus status = HttpStatus.CONFLICT;
		ProblemType problemType = ProblemType.ENTIDADE_EM_USO;
		String detail = ex.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
		
		//return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
	    /*Problema problema = Problema.builder()
	            .dataHora(LocalDateTime.now())
	            .mensagem(e.getMessage()).build();
	    
	    return ResponseEntity.status(HttpStatus.CONFLICT)
	            .body(problema); */
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		if(body == null) {
		body = Problem.builder()		        
		        .title(status.getReasonPhrase())
		        .status(status.value())
		        .build();
		
		}else if (body instanceof String) {
			body = Problem.builder()			        
			        .title((String) body)
			        .status(status.value())
			        .build();	
		}
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
		
	private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail) {
		
		return Problem.builder()
			   .status(status.value())
			   .type(problemType.getUri())
			   .title(problemType.getTitle())
			   .detail(detail);
	}
	
private String joinPath(List<Reference> references) {

		
		return references.stream()
				         .map(ref -> ref.getFieldName())
				         .collect(Collectors.joining("."));
	}
}
