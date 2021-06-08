package com.mastery.java.task.dto;

import com.mastery.java.task.entity.Gender;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
public class EmployeeFullDto {

    @ApiModelProperty(example = "1")
    private Integer employeeId;

    @ApiModelProperty(example = "Bob")
    private String firstName;

    @ApiModelProperty(example = "Bobson")
    private String lastName;

    @ApiModelProperty(example = "1")
    private int departmentId;

    @ApiModelProperty(example = "Back-end Developer")
    private String jobTittle;

    @ApiModelProperty(example = "MALE")
    private Gender gender;

    @ApiModelProperty(example = "1990-12-01")
    private LocalDate dateOfBirth;
}