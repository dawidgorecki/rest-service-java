package pl.dawidgorecki.restservice.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.dawidgorecki.restservice.dto.CreateTaskDTO;
import pl.dawidgorecki.restservice.dto.TaskDTO;
import pl.dawidgorecki.restservice.dto.UpdateTaskDTO;
import pl.dawidgorecki.restservice.entity.Task;
import pl.dawidgorecki.restservice.repository.TaskRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService implements TaskCrud {
    private final TaskRepository taskRepository;
    private final ModelMapper mapper;

    @Override
    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(task -> mapper.map(task, TaskDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TaskDTO> findById(int id) {
        return taskRepository.findById(id)
                .map(task -> mapper.map(task, TaskDTO.class));
    }

    @Override
    @Transactional
    public TaskDTO createTask(CreateTaskDTO createTaskDTO) {
        Task newTask = taskRepository.save(new Task(createTaskDTO));
        return mapper.map(newTask, TaskDTO.class);
    }

    @Override
    @Transactional
    public Optional<TaskDTO> updateTask(int id, UpdateTaskDTO updateTaskDTO) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setName(updateTaskDTO.getName());
                    task.setDescription(updateTaskDTO.getDescription());
                    task.setDeadline(LocalDate.parse(updateTaskDTO.getDeadline()));

                    return task;
                })
                .map(taskRepository::save)
                .map(t -> mapper.map(t, TaskDTO.class));
    }

    @Override
    @Transactional
    public boolean deleteTask(int id) {
        Optional<Task> task = taskRepository.findById(id);

        if (task.isPresent()) {
            taskRepository.delete(task.get());
            return true;
        }

        return false;
    }

    @Transactional
    public boolean markAsDone(int id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isEmpty()) {
            return false;
        }

        taskRepository.markAsDone(id);
        return true;
    }
}
