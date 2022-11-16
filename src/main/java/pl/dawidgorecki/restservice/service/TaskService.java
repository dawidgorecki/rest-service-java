package pl.dawidgorecki.restservice.service;

import pl.dawidgorecki.restservice.dto.CreateTaskDTO;
import pl.dawidgorecki.restservice.dto.TaskDTO;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    List<TaskDTO> getAllTasks();

    Optional<TaskDTO> findById(int id);

    TaskDTO createTask(CreateTaskDTO createTaskDTO);

    void updateTask(int id, TaskDTO taskDTO);

    void deleteTask(int id);

}
