package com.example.bug.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.bug.project.entity.Project;
import com.example.bug.project.service.IProjectService;
@CrossOrigin
@RestController
public class ProjectController {

	@Autowired
	private IProjectService projectServiceImpl;

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/api/project")
	public String createProject(@Valid @RequestBody Project project) {
		return projectServiceImpl.save(project);
	} 

	@GetMapping("/api/project")
	public List<Project> getAllUsers() {
		return projectServiceImpl.findAll();

	}

	@PatchMapping("/api/project/{id}")
	public void updateUser(@RequestBody Project project, @PathVariable String id) {
		project.setId(id);
		projectServiceImpl.updateUser(project);
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
