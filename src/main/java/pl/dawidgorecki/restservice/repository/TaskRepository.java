package pl.dawidgorecki.restservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.dawidgorecki.restservice.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Modifying
    @Query("update Task set isDone = true where id = :id")
    void markAsDone(int id);
}