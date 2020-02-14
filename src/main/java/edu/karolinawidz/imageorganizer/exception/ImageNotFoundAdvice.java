package edu.karolinawidz.imageorganizer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ImageNotFoundAdvice {
	@ExceptionHandler(ImageNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String imageNotFoundHandler(ImageNotFoundException e){
		return e.getMessage();
	}
}
