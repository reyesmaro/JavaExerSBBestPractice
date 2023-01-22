package com.maro.crud.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.maro.crud.data.ErrorData;
import com.maro.crud.dto.APIResponse;
import com.maro.crud.exception.CityNotFoundException;

@RestControllerAdvice
public class CityExceptionHandler {
	
	@ExceptionHandler
//	public ResponseEntity<APIResponse<List<ErrorData>>> InvalidArgumentHandler(MethodArgumentNotValidException exception) {
//		List<ErrorData> errorList = new ArrayList<ErrorData>();
//		exception.getBindingResult().getFieldErrors().forEach(e -> errorList.add(new ErrorData(e.getField() , e.getDefaultMessage())));
//		return new ResponseEntity<APIResponse<List<ErrorData>>>(
//				APIResponse.<List<ErrorData>>builder().status("Erorr").results(errorList).build(), HttpStatus.BAD_REQUEST);
//	}
	public ResponseEntity<?> InvalidArgumentHandler(MethodArgumentNotValidException exception) {
		List<ErrorData> errorList = new ArrayList<ErrorData>();
		exception.getBindingResult().getFieldErrors().forEach(e -> errorList.add(new ErrorData(e.getField() , e.getDefaultMessage())));
		return new ResponseEntity<>(APIResponse.<List<ErrorData>>builder().status("Error").results(errorList).build(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<APIResponse<ErrorData>> CityNotFoundHandler(CityNotFoundException exception) {
		ErrorData errorData = new ErrorData("ID" , "City ID not found");
		APIResponse<ErrorData> apiResponse = APIResponse.<ErrorData>builder()
								.status("Error")
								.results(errorData)
								.build();
		return new ResponseEntity<APIResponse<ErrorData>>(apiResponse , HttpStatus.OK);
	}
}
