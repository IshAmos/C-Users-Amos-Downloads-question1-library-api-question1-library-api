package com.example.question1_library_api.controller.task;

import com.example.question1_library_api.model.task.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private List<Task> tasks = new ArrayList<>();

    // Constructor - initialize sample tasks
    public TaskController() {
        tasks.add(new Task(1L, "Complete Project", "Finish the Spring Boot project", false, "HIGH", "2026-02-20"));
        tasks.add(new Task(2L, "Review Code", "Code review for the team", false, "MEDIUM", "2026-02-18"));
        tasks.add(new Task(3L, "Write Documentation", "Write API documentation", true, "LOW", "2026-02-15"));
        tasks.add(new Task(4L, "Test APIs", "Test all API endpoints", false, "HIGH", "2026-02-19"));
        tasks.add(new Task(5L, "Deploy Application", "Deploy to production", false, "HIGH", "2026-02-25"));
    }

    // GET all tasks
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    // GET task by ID
    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId) {
        for (Task task : tasks) {
            if (task.getTaskId().equals(taskId)) {
                return new ResponseEntity<>(task, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // GET tasks by completion status
    @GetMapping("/status")
    public ResponseEntity<List<Task>> getTasksByStatus(@RequestParam boolean completed) {
        List<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isCompleted() == completed) {
                result.add(task);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // GET tasks by priority
    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<Task>> getTasksByPriority(@PathVariable String priority) {
        List<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getPriority().equalsIgnoreCase(priority)) {
                result.add(task);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // POST - Create new task
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task newTask) {
        tasks.add(newTask);
        return new ResponseEntity<>(newTask, HttpStatus.CREATED);
    }

    // PUT - Update task
    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task updatedTask) {
        for (Task task : tasks) {
            if (task.getTaskId().equals(taskId)) {
                task.setTitle(updatedTask.getTitle());
                task.setDescription(updatedTask.getDescription());
                task.setPriority(updatedTask.getPriority());
                task.setDueDate(updatedTask.getDueDate());
                return new ResponseEntity<>(task, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // PATCH - Mark task as completed
    @PatchMapping("/{taskId}/complete")
    public ResponseEntity<Task> markTaskAsCompleted(@PathVariable Long taskId) {
        for (Task task : tasks) {
            if (task.getTaskId().equals(taskId)) {
                task.setCompleted(true);
                return new ResponseEntity<>(task, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // DELETE task
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        for (Task task : tasks) {
            if (task.getTaskId().equals(taskId)) {
                tasks.remove(task);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
