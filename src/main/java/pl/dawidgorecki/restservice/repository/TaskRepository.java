package pl.dawidgorecki.restservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dawidgorecki.restservice.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}