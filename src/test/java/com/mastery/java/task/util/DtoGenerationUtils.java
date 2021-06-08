package com.mastery.java.task.util;

import com.mastery.java.task.dto.EmployeeCreateDto;
import com.mastery.java.task.dto.EmployeeUpdateDto;
import com.mastery.java.task.entity.Gender;

import java.time.LocalDate;

public class DtoGenerationUtils {

    public static EmployeeCreateDto generateEmployeeCreateDto() {

        EmployeeCreateDto createDto = new EmployeeCreateDto();
        createDto.setFirstName("Vova");
        createDto.setLastName("Ololo");
        createDto.setDateOfBirth(LocalDate.of(1990, 5, 4));
        createDto.setDepartmentId(1);
        createDto.setGender(Gender.MALE);
        createDto.setJobTittle("Back-end");

        return createDto;
    }

    public static EmployeeUpdateDto generateEmployeeUpdateDto(Integer employeeId) {

        EmployeeUpdateDto updateDto = new EmployeeUpdateDto();
        updateDto.setEmployeeId(employeeId);
        updateDto.setGender(Gender.FEMALE);
        updateDto.setDateOfBirth(LocalDate.of(1986, 10, 8));
        updateDto.setDepartmentId(1);
        updateDto.setFirstName("Olga");
        updateDto.setLastName("Mola");
        updateDto.setJobTittle("Back-end");

        return updateDto;
    }
}
