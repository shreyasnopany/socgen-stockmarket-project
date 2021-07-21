package com.example.bug.project.service;

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
import com.example.bug.project.repository.ProjectRepository;

@ExtendWith(MockitoExtension.class)
class ProjectServiceImplTest {

	@InjectMocks
	private ProjectServiceImpl projectServiceimpl;

	@Mock
	ProjectRepository projectRepository;

	@Test
	void testSave() {
		Project project = new Project();
		project.setId("hello");
		when(projectRepository.save(project)).thenReturn(project);
		String projectReturned = projectServiceimpl.save(project);
		assertEquals(projectReturned, project.getId());
	}

	@Test
	void testFindAll() {
		List<Project> projectList = new ArrayList<>();
		when(projectRepository.findAll()).thenReturn(projectList);
		List<Project> projectListReturned = projectServiceimpl.findAll();
		assertIterableEquals(projectList, projectListReturned);
	}

}
