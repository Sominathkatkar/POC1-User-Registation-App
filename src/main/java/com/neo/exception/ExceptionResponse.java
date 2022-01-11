package com.neo.exception;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ExceptionResponse {
	
	private String message;
    private LocalDateTime dateTime;
    
   

}
