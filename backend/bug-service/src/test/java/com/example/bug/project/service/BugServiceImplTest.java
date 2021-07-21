package com.example.bug.project.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.bug.project.entity.Bug;
import com.example.bug.project.repository.BugRepository;

@ExtendWith(MockitoExtension.class)
class BugServiceImplTest {

	@InjectMocks
	private BugServiceImpl bugServiceimpl;

	@Mock
	BugRepository bugRepository;

	@Test
	void testSave() {
		Bug bug = new Bug();
		bug.setId("hello");
		when(bugRepository.save(bug)).thenReturn(bug);
		String bugReturned = bugServiceimpl.save(bug);
		assertEquals(bugReturned, bug.getId());
	}

	@Test
	void testFindAll() {
		List<Bug> bugList = new ArrayList<>();
		when(bugRepository.findAll()).thenReturn(bugList);
		List<Bug> bugListReturned = bugServiceimpl.findAll();
		assertIterableEquals(bugList, bugListReturned);

	}

	@Test
	void testFind() {
		Optional<Bug> bug = Optional.ofNullable(new Bug());
		String id = "4485461dwjdgvwdjv";
		when(bugRepository.findById(id)).thenReturn(bug);
		Optional<Bug> bugReturned = bugServiceimpl.find(id);
		assertEquals(bug, bugReturned);

	}

}
