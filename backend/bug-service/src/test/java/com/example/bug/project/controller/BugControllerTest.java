package com.example.bug.project.controller;

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
import com.example.bug.project.service.IBugService;

@ExtendWith(MockitoExtension.class)
class BugControllerTest {

	@InjectMocks
	BugController bugController;

	@Mock
	IBugService iBugService;

	@Test
	void testCreateBug() {
		Bug bug = new Bug();
		when(iBugService.save(bug)).thenReturn("bugSavedid");
		String response = bugController.createBug(bug);
		assertEquals("bugSavedid", response);
	}

	@Test
	void testGetAllBugs() {
		List<Bug> response = new ArrayList<Bug>();
		response.add(new Bug());
		when(iBugService.findAll()).thenReturn(response);
		List<Bug> bugListReturned = bugController.getAllBugs();
		assertEquals(response, bugListReturned);

	}

	@Test
	void testFindBugById() {
		Optional<Bug> bug = Optional.ofNullable(new Bug());
		String id = "6097858768bjsdcb";
		when(iBugService.find(id)).thenReturn(bug);
		Optional<Bug> bugReturned = bugController.findBugById(id);
		assertEquals(bug, bugReturned);
	}

}
