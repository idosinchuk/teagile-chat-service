package com.soprasteria.hackaton.teagile.core.service.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.soprasteria.hackaton.teagile.core.service.dto.ProjectRequestDTO;
import com.soprasteria.hackaton.teagile.core.service.service.ProjectService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "API Rest for Project.")
@RequestMapping(value = "/api/v1")
public class ProjectController {

	public static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

	@Autowired
	ProjectService projectService;

	@GetMapping(path = "/projects", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Retrieve list of all projects filtered by userId")
	public ResponseEntity<?> getAllProjectsByUserId(@RequestParam("userId") int userId) {

		logger.info("Fetching all projects by UserId");
		return projectService.getAllProjectsByUserId(userId);
	}

	@GetMapping(path = "/projects/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Retrieve project by id.")
	public ResponseEntity<?> getProject(@PathVariable("id") int id) {

		logger.info("Fetching project with id {}", id);
		return projectService.getProject(id);
	}

	@PostMapping(path = "/projects", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Add a project.")
	public ResponseEntity<?> addProject(@Valid @RequestBody ProjectRequestDTO projectRequestDTO,
			@RequestParam("userId") int userId) {

		logger.info("Process add project");

		return projectService.addProject(projectRequestDTO, userId);

	}

	@PatchMapping(path = "/projects/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Update the project.")
	public ResponseEntity<?> updateProject(@PathVariable("id") int id,
			@RequestBody ProjectRequestDTO projectRequestDTO) {

		logger.info("Process patch project");

		return projectService.updateProject(id, projectRequestDTO);

	}

	@DeleteMapping(path = "/projects/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Delete project by Id.")
	public ResponseEntity<?> deleteUser(@PathVariable("id") int id) {

		logger.info("Deleting user with id {} ", id);
		return projectService.deleteProject(id);
	}
}
