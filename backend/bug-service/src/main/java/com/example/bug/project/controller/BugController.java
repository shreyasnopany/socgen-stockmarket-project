package com.example.bug.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.bug.project.entity.Bug;
import com.example.bug.project.service.IBugService;

@RestController
public class BugController {

	@Autowired
	IBugService iBugService;

	@PostMapping("/api/bug")
	public String createBug(@Valid @RequestBody Bug bug) {
		return iBugService.save(bug);
	}

	@GetMapping("/api/bug")
	public List<Bug> getAllBugs() {
		return iBugService.findAll();
	}

	@GetMapping("/api/bug/{id}")
	public Optional<Bug> findBugById(String id) {
		return iBugService.find(id);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

}
