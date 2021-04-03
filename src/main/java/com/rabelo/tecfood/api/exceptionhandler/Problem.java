package com.rabelo.tecfood.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(Include.NON_NULL)
public class Problem {
	private Integer status;
	private String type;
	private String title;
	private String detail;
	
	private String userMassage;
	private LocalDateTime timestamp;
	private List<Field> fields;
	
	@Builder
	@Getter
	static class Field{
		private String name;
		private String userMessage;
	}
	
	}
