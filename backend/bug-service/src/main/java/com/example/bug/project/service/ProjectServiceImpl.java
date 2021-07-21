package com.example.bug.project.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bug.project.entity.Project;
import com.example.bug.project.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements IProjectService {

	@Autowired
	ProjectRepository projectRepository;

	@Override
	public String save(Project project) {
		project.setCreatedDate(LocalDate.now());
		Project saved = projectRepository.save(project);

		return saved.getId();
	}

	@Override
	public List<Project> findAll() {
		return projectRepository.findAll();
	}

	@Override
	public void updateUser(Project project) {
		projectRepository.save(project);
	}
}
