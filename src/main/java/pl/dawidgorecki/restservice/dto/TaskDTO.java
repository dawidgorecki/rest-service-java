package pl.dawidgorecki.restservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class TaskDTO {
    private int id;
    private String name;
    private String description;
    private LocalDate deadline;
    private boolean isDone = false;
}
