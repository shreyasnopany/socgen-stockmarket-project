package com.example.bug.project.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bug.project.entity.Bug;
import com.example.bug.project.repository.BugRepository;

@Service
public class BugServiceImpl implements IBugService {

	@Autowired
	BugRepository bugRepository;

	@Override
	public String save(Bug bug) {
		bug.setCreatedDate(LocalDateTime.now());
		Bug saved = bugRepository.save(bug);
		return saved.getId();
	}

	@Override
	public List<Bug> findAll() {

		return bugRepository.findAll();
	}

	@Override
	public Optional<Bug> find(String id) {

		return bugRepository.findById(id);
	}

}
