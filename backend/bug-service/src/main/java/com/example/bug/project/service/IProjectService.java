package com.example.bug.project.service;

import java.util.List;

import com.example.bug.project.entity.Project;

public interface IProjectService {

	String save(Project project);

	List<Project> findAll();

	void updateUser(Project project);

}
