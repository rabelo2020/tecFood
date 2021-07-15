package com.rabelo.tecfood.api.exceptionhandler;

import java.time.OffsetDateTime;
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
	private OffsetDateTime timestamp;
	private List<Object> objects;
	
	@Builder
	@Getter
	static class Object{
		private String name;
		private String userMessage;
	}
	
	}
