package com.example.bug.project.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.bug.project.entity.Bug;

public interface BugRepository extends MongoRepository<Bug, String>{

}
