package com.gft.api.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.gft.api.dto.ApiErrorDTO;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler({ApiNoticiasException.class})
	public ResponseEntity<ApiErrorDTO> handleLojaException(ApiNoticiasException ex, WebRequest request){
		String error = "Erro no sistema";
		
		ApiErrorDTO apiErrorDTO = new ApiErrorDTO(ex.getMessage(), error, HttpStatus.INTERNAL_SERVER_ERROR);
		
		return new ResponseEntity<ApiErrorDTO>(apiErrorDTO, new HttpHeaders(),apiErrorDTO.getStatus());
	}
	
	@ExceptionHandler({EntityNotFoundException.class})
	public ResponseEntity<ApiErrorDTO> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request){
		String error = "Recurso não encontrado";
		
		ApiErrorDTO apiErrorDTO = new ApiErrorDTO(ex.getMessage(), error, HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<ApiErrorDTO>(apiErrorDTO, new HttpHeaders(),apiErrorDTO.getStatus());
	}
	
	@ExceptionHandler({EntityExistException.class})
	public ResponseEntity<ApiErrorDTO> handleEntityExistExceptionException(EntityExistException ex, WebRequest request){
		String error = "Recurso já existe";
		
		ApiErrorDTO apiErrorDTO = new ApiErrorDTO(ex.getMessage(), error, HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<ApiErrorDTO>(apiErrorDTO, new HttpHeaders(),apiErrorDTO.getStatus());
	}
	
	
//	@ExceptionHandler({AuthenticationException.class})
//	public ResponseEntity<ApiErrorDTO> handleAuthenticationFailException(AuthenticationException ex, WebRequest request){
//		String error = "Usuário ou senha inválido";
//		
//		ApiErrorDTO apiErrorDTO = new ApiErrorDTO(ex.getMessage(), error, HttpStatus.UNAUTHORIZED);
//		
//		return new ResponseEntity<ApiErrorDTO>(apiErrorDTO, new HttpHeaders(),apiErrorDTO.getStatus());
//	}
//	
//	@ExceptionHandler({JWTVerificationException.class})
//	public ResponseEntity<ApiErrorDTO> handleJWTVerificationExceptionException(AuthenticationException ex, WebRequest request){
//		String error = "Token inválido";
//		
//		ApiErrorDTO apiErrorDTO = new ApiErrorDTO(ex.getMessage(), error, HttpStatus.BAD_REQUEST);
//		
//		return new ResponseEntity<ApiErrorDTO>(apiErrorDTO, new HttpHeaders(),apiErrorDTO.getStatus());
//	}

}