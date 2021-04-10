package com.rabelo.tecfood.core.validation;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MultiploValidator implements ConstraintValidator<Multiplo, Number>{
	
	private int numeroMultiplo;
	
	@Override
	public void initialize(Multiplo constraintAnnotation) {

		this.numeroMultiplo = constraintAnnotation.numero();
	}

	@Override
	public boolean isValid(Number value, ConstraintValidatorContext context) {
		boolean flag = true;
		
		if (value != null) {
			 
			var valorDecimal = BigDecimal.valueOf(value.doubleValue());
			var multiDecimal = BigDecimal.valueOf(this.numeroMultiplo);
			var resto = valorDecimal.remainder(multiDecimal);
			
			flag = BigDecimal.ZERO.compareTo(resto) == 0;
			
		}
		return flag;
	}

}
