package pl.dawidgorecki.restservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UpdateTaskDTO {
    @NotBlank(message = "{name.notblank}")
    @Size(min = 3, max = 255, message = "{name.size}")
    private String name;

    @NotBlank(message = "{description.notblank}")
    @Size(min = 3, max = 255, message = "{description.size}")
    private String description;

    @NotNull(message = "{deadline.notnull}")
    @Pattern(regexp = "^(\\d{4})-?(1[0-2]|0[1-9])-?(3[01]|0[1-9]|[12]\\d)$", message = "{deadline.format}")
    private String deadline;
}
