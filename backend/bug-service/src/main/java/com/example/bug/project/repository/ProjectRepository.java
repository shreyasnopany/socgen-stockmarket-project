package com.example.bug.project.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.bug.project.entity.Project;

@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {

}
