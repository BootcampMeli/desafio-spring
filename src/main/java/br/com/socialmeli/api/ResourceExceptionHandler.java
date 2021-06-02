package br.com.socialmeli.api;

import br.com.socialmeli.api.exception.ErrorDetail;
import br.com.socialmeli.api.exception.NotPermittedActionException;
import br.com.socialmeli.api.exception.ResourceNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@RestControllerAdvice
public class ResourceExceptionHandler {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ResourceNotFoundException.class)
	public ErrorDetail handleResourceNotFoundException(ResourceNotFoundException e) {
		log.error(e.getMessage());
		return new ErrorDetail(e.getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();

		List<FieldError> fieldErrors = result.getFieldErrors();

		List<ErrorDetail> listError = new ArrayList<>();

		fieldErrors.forEach(objectError -> {
			listError.add(new ErrorDetail(objectError.getField(),objectError.getDefaultMessage()));
		});

		log.error(listError);

		return ResponseEntity.badRequest().body(listError);
	}

	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ErrorDetail handleDataIntegrityViolationException(DataIntegrityViolationException e) {
		log.error(e.getMessage());
		return new ErrorDetail("Error to remove, data violation");
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NotPermittedActionException.class)
	public ErrorDetail handleNotPermittedActionException(NotPermittedActionException e) {
		log.error(e.getMessage());
		return new ErrorDetail(e.getMessage());
	}
}
