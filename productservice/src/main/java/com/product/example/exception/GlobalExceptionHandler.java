package com.product.example.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.AllArgsConstructor;

/**
 * 
 */
@RestControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	private final ErrorAttributes errorAttributes;

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> appException(Exception ex, WebRequest request) {
		Map<String, Object> body = errorAttributes.getErrorAttributes(request, null);
		HttpStatus status = HttpStatus.ACCEPTED;
		body.put("status", status.value());
		body.put("error", status.getReasonPhrase());
		body.put("message", ex.getMessage());
		return ResponseEntity.status(status).body(body);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Map<String, String> validationErrors = new HashMap<String, String>();

		List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

		validationErrorList.forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String validationMsg = error.getDefaultMessage();
			validationErrors.put(fieldName, validationMsg);
		});

		return new ResponseEntity<Object>(validationErrors, HttpStatus.BAD_REQUEST);
	}

}