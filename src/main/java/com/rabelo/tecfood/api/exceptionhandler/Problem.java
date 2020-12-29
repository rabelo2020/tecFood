package com.rabelo.tecfood.api.exceptionhandler;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@JsonInclude(Include.NON_NULL)
public class Problem {
	private Integer status;
	private String type;
	private String title;
	private String detail;
	}
