package com.mastery.java.task.dto;

import com.mastery.java.task.entity.Gender;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class EmployeeCreateDto {

    @ApiModelProperty(example = "Bob")
    @NotEmpty(message = "name can not be empty")
    private String firstName;

    @ApiModelProperty(example = "Bobson")
    @NotEmpty(message = "lastName can not be empty")
    private String lastName;

    @ApiModelProperty(example = "1")
    @NotNull(message = "department_id can not be null")
    private int departmentId;

    @ApiModelProperty(example = "Back-end Developer")
    @NotEmpty(message = "job_tittle can not be empty")
    private String jobTittle;

    @ApiModelProperty(example = "MALE")
    private Gender gender;

    @ApiModelProperty(example = "1990-12-01")
    @NotNull(message = "date_of_birth can not be null")
    private LocalDate dateOfBirth;
}