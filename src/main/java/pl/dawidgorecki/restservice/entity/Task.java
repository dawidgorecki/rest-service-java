package pl.dawidgorecki.restservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.dawidgorecki.restservice.dto.CreateTaskDTO;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = Task.TABLE_NAME)
@Getter
@Setter
@NoArgsConstructor
public class Task {
    public static final String TABLE_NAME = "tasks";
    public static final String COLUMN_PREFIX = "t_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_PREFIX + "id", nullable = false)
    private int id;

    @Column(name = COLUMN_PREFIX + "name", nullable = false)
    private String name;

    @Column(name = COLUMN_PREFIX + "description", nullable = false)
    private String description;

    @Column(name = COLUMN_PREFIX + "deadline", nullable = false)
    private LocalDate deadline;

    @Column(name = COLUMN_PREFIX + "is_done", nullable = false)
    private boolean isDone = false;

    public Task(String name, String description, LocalDate deadline) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
    }

    public Task(CreateTaskDTO createTaskDTO) {
        name = createTaskDTO.getName();
        description = createTaskDTO.getDescription();
        deadline = LocalDate.parse(createTaskDTO.getDeadline());
    }
}
