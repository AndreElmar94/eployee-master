package com.mastery.java.task.util;

import com.mastery.java.task.entity.Employee;
import com.mastery.java.task.entity.Gender;

import java.time.LocalDate;
import java.util.List;

public class EntityGenerationUtils {

    public static Employee generateEmployee() {

        return Employee.builder()
                .firstName("Alex")
                .lastName("Ololo")
                .dateOfBirth(LocalDate.of(1994, 12, 02))
                .departmentId(1)
                .jobTittle("Back-end")
                .gender(Gender.MALE)
                .build();
    }

    public static List<Employee> generateListEmployee() {
        return List.of(
                Employee.builder()
                        .firstName("Alex")
                        .lastName("Ololo")
                        .dateOfBirth(LocalDate.of(1994, 12, 2))
                        .departmentId(1)
                        .jobTittle("Back-end")
                        .gender(Gender.MALE)
                        .build(),
                Employee.builder()
                        .firstName("Bob")
                        .lastName("Bobson")
                        .dateOfBirth(LocalDate.of(1990, 3, 10))
                        .departmentId(2)
                        .jobTittle("Front-end")
                        .gender(Gender.MALE)
                        .build(),
                Employee.builder()
                        .firstName("Masha")
                        .lastName("Upa")
                        .dateOfBirth(LocalDate.of(1996, 10, 1))
                        .departmentId(1)
                        .jobTittle("Back-end")
                        .gender(Gender.FEMALE)
                        .build());
    }
}