package pl.dawidgorecki.restservice.service;

import org.springframework.data.domain.Pageable;
import pl.dawidgorecki.restservice.dto.CreateTaskDTO;
import pl.dawidgorecki.restservice.dto.TaskDTO;
import pl.dawidgorecki.restservice.dto.UpdateTaskDTO;

import java.util.List;
import java.util.Optional;

public interface TaskCrud {

    List<TaskDTO> getAllTasks(Pageable pageable);

    Optional<TaskDTO> findById(int id);

    TaskDTO createTask(CreateTaskDTO createTaskDTO);

    Optional<TaskDTO> updateTask(int id, UpdateTaskDTO updateTaskDTO);

    boolean deleteTask(int id);

    boolean markAsDone(int id);

}
