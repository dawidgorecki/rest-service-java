package pl.dawidgorecki.restservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dawidgorecki.restservice.dto.CreateTaskDTO;
import pl.dawidgorecki.restservice.dto.TaskDTO;
import pl.dawidgorecki.restservice.dto.UpdateTaskDTO;
import pl.dawidgorecki.restservice.service.TaskCrud;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(
        path = "/api/v1",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class TaskController {

    private final Logger logger = LoggerFactory.getLogger(TaskController.class);
    private final TaskCrud taskService;

    public TaskController(TaskCrud taskService) {
        this.taskService = taskService;
    }

    private void logNotFound(int id) {
        logger.info("Task with id {} not found", id);
    }

    @GetMapping(path = "/tasks", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<TaskDTO>> findAll(Pageable pageable) {
        logger.info("Getting all tasks");
        return ResponseEntity.ok(taskService.getAllTasks(pageable));
    }

    @GetMapping(path = "/tasks/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<TaskDTO> findById(@PathVariable int id) {
        logger.info("Getting single task with id {}", id);
        Optional<TaskDTO> task = taskService.findById(id);

        if (task.isEmpty()) {
            logNotFound(id);
        }

        return ResponseEntity.of(task);
    }

    @PostMapping(path = "/tasks", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskDTO> createTask(@RequestBody @Valid CreateTaskDTO createTaskDTO) {
        logger.info("Creating a new task: {}", createTaskDTO.getName());
        TaskDTO newTask = taskService.createTask(createTaskDTO);

        logger.info("Task created. ID: {}, name: {}", newTask.getId(), newTask.getName());
        return ResponseEntity.created(URI.create("/api/v1/" + newTask.getId())).body(newTask);
    }

    @DeleteMapping(path = "/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable int id) {
        logger.info("Deleting task {}", id);

        if (taskService.deleteTask(id)) {
            logger.info("Task with id {} was deleted", id);
            return ResponseEntity.noContent().build();
        }

        logNotFound(id);
        return ResponseEntity.notFound().build();
    }

    @PutMapping(path = "/tasks/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskDTO> updateTask(@PathVariable int id, @RequestBody @Valid UpdateTaskDTO updateTaskDTO) {
        logger.info("Updating task {}", id);
        Optional<TaskDTO> updatedTask = taskService.updateTask(id, updateTaskDTO);

        if (updatedTask.isEmpty()) {
            logNotFound(id);
        }

        return ResponseEntity.of(updatedTask);
    }

    @PatchMapping(path = "/tasks/{id}")
    public ResponseEntity<Void> markAsDone(@PathVariable int id) {
        if (taskService.markAsDone(id)) {
            logger.info("Task with id {} marked as done", id);
            return ResponseEntity.noContent().build();
        }

        logNotFound(id);
        return ResponseEntity.notFound().build();
    }
}
