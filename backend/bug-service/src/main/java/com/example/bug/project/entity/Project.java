package com.example.bug.project.entity;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Project {

	private String id;
	@NotBlank(message = "Name is mandatory")
	private String name;
	@Size(min = 5, max = 255)
	private String description;
	private LocalDate createdDate;
	private LocalDate completedDate;

}
