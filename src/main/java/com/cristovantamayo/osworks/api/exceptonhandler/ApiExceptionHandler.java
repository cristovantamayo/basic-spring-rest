package com.cristovantamayo.osworks.api.exceptonhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cristovantamayo.osworks.domain.exception.BusinessException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Object> handlerBusiness(BusinessException ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Problem problem = Problem.of(status.value(), OffsetDateTime.now(), ex.getMessage(), null);
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<ProblemField> fields = new ArrayList<ProblemField>();
		
		for(ObjectError error : ex.getBindingResult().getAllErrors()) {
			String field = ((FieldError) error).getField();	
			String errorMessage = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			fields.add(ProblemField.of(field, errorMessage));
		}
		
		Problem problem = Problem.of(
				status.value(),
				OffsetDateTime.now(),
				"Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.",
				fields
			);
		
		return super.handleExceptionInternal(ex, problem, headers, status, request);
	}
	
}
