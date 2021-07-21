package com.example.bug.project.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.bug.project.entity.Project;
import com.example.bug.project.service.IProjectService;

@ExtendWith(MockitoExtension.class)
class ProjectControllerTest {

	@InjectMocks
	private ProjectController projectController;

	@Mock
	IProjectService iProjectServiceImpl;

	@Test
	void testCreateProject() {
		Project project = new Project();
		when(iProjectServiceImpl.save(project)).thenReturn("Hello");
		String id = projectController.createProject(project);
		assertNotNull(id);
	}

	@Test
	void testGetAllUsers() {
		List<Project> response = new ArrayList<Project>();
		response.add(new Project());
		when(iProjectServiceImpl.findAll()).thenReturn(response);
		List<Project> responseReturned = projectController.getAllUsers();
		assertIterableEquals(response, responseReturned);
	}

}
