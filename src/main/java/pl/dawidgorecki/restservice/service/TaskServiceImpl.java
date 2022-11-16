package pl.dawidgorecki.restservice.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.dawidgorecki.restservice.dto.CreateTaskDTO;
import pl.dawidgorecki.restservice.dto.TaskDTO;
import pl.dawidgorecki.restservice.entity.Task;
import pl.dawidgorecki.restservice.repository.TaskRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
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
    public TaskDTO createTask(CreateTaskDTO createTaskDTO) {
        Task newTask = taskRepository.save(new Task(createTaskDTO));
        return mapper.map(newTask, TaskDTO.class);
    }

    @Override
    public void updateTask(int id, TaskDTO taskDTO) {
        taskRepository.findById(id)
                .map(task -> {
                    task.setName(taskDTO.getName());
                    task.setDescription(taskDTO.getDescription());
                    task.setDeadline(taskDTO.getDeadline());
                    task.setDone(taskDTO.isDone());

                    return task;
                })
                .map(taskRepository::save);
    }

    @Override
    public void deleteTask(int id) {
        Optional<Task> task = taskRepository.findById(id);
        task.ifPresent(taskRepository::delete);
    }
}
