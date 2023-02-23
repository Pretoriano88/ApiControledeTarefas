package com.example.api_to_do.controllers;


import com.example.api_to_do.entities.Task;
import com.example.api_to_do.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/tasks")


public class TaskController {

    @Autowired
    private TaskService service;

    @GetMapping
    @Operation(summary = "List all tasks")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Suscess Operation"),
            @ApiResponse(responseCode = "400,", description = "When does not exist in the Database")
    })
    public ResponseEntity<List<Task>> findAll() {
        // List<TaskDto> tasks = service.findAllTasks();
        return ResponseEntity.ok().body(service.findAllTasks());
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "List task by id")
    public ResponseEntity<Task> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping("/done")
    @Operation(summary = "List tasks done")
    public List<Task> findTaskDone() {
        List<Task> tasks = service.findAllTasks();
        List<Task> tasksDone = service.findTaskDone(tasks);
        return tasksDone;
    }

    @GetMapping("/notcomplete")
    @Operation(summary = "List tasks notcompleted")
    public List<Task> findTaskNotComplete(){
        List<Task> tasks = service.findAllTasks();
        List<Task> tasksNotComplete = service.findTaskNotComplete(tasks);
        return tasksNotComplete;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete task by id")
    public ResponseEntity<Task> deleteById(@PathVariable Long id) {
        service.deleteTasks(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping
    @Operation(summary = "Create a new task")
    public ResponseEntity<Task> create(@RequestBody Task task) {
        Task taskCreate = service.create(task);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(taskCreate.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a task by id")
    public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody Task task) {
        Task taskUpdate = service.update(id, task);
        return ResponseEntity.ok().body(taskUpdate);

    }

    @PutMapping("/updateStatus/{id}")
    @Operation(summary = "Update status of a task")
    public ResponseEntity<Task> updateStatus(@PathVariable Long id, @RequestBody Task task) {
        Task taskUpdateStatus = service.updateStatus(id, task);
        return ResponseEntity.ok().body(taskUpdateStatus);
    }


}
