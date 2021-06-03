package com.mastery.java.task.dto;

import com.mastery.java.task.entity.Gender;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeePreviewDto {

    @ApiModelProperty(example = "1")
    private Long employee_id;

    @ApiModelProperty(example = "Bob")
    private String first_name;

    @ApiModelProperty(example = "Bobson")
    private String last_name;

    @ApiModelProperty(example = "1")
    private int department_id;

    @ApiModelProperty(example = "Back-end Developer")
    private String job_tittle;

    @ApiModelProperty(example = "MALE")
    private Gender gender;

    @ApiModelProperty(example = "1990-12-01")
    private LocalDate date_of_birth;
}