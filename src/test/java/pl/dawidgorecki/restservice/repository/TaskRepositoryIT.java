package pl.dawidgorecki.restservice.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import pl.dawidgorecki.restservice.entity.Task;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.from;

@DataJpaTest
class TaskRepositoryIT {

    @Autowired
    private TaskRepository repository;

    @Test
    @Sql(statements = "INSERT INTO tasks(t_id, t_name, t_description, t_deadline, t_is_done) " +
            "VALUES (1, 'First task', 'Task description', '2022-12-12', false)")
    void testMarkAsDone() {
        repository.markAsDone(1);

        Optional<Task> task = repository.findById(1);

        assertThat(task)
                .get()
                .returns("First task", Task::getName)
                .returns("Task description", Task::getDescription)
                .returns(LocalDate.parse("2022-12-12"), Task::getDeadline)
                .returns(true, from(Task::isDone));
    }
}