package com.example.bug.project.service;

import java.util.List;
import java.util.Optional;

import com.example.bug.project.entity.Bug;

public interface IBugService {

	String save(Bug bug);

	List<Bug> findAll();

	Optional<Bug> find(String id);

}
