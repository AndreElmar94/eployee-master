package com.mastery.java.task.service;

import com.mastery.java.task.dto.EmployeeCreateDto;
import com.mastery.java.task.dto.EmployeeFullDto;
import com.mastery.java.task.dto.EmployeePreviewDto;
import com.mastery.java.task.dto.EmployeeUpdateDto;
import org.springframework.data.domain.Page;

public interface EmployeeService {

    EmployeeFullDto findById(Integer id);

    Page<EmployeePreviewDto> findAll(Integer page, Integer size);

    EmployeeFullDto create(EmployeeCreateDto employeeCreateDto);

    EmployeeFullDto update(EmployeeUpdateDto employeeUpdateDto);

    void deleteById(Integer id);

    EmployeeFullDto findByFirstName(String name);
}