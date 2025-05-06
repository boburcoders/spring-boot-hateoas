package com.company.SpiringBootDataJpa.models;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "ToDo filter criteria")
public class ToDoCriteria {

    @Schema(description = "Title of the task", required = true)
    private String title;

    @Schema(description = "User ID", required = true)
    private Integer userId;

    @Min(1)
    @Max(5)
    @Schema(description = "Priority level from 1 to 5", required = true)
    private Integer priority;

    @Schema(description = "Completion status", required = true)
    private boolean completed;
}

