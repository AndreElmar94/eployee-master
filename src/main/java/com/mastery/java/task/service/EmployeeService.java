package com.mastery.java.task.service;

import com.mastery.java.task.dto.EmployeeCreateDto;
import com.mastery.java.task.dto.EmployeeFullDto;
import com.mastery.java.task.dto.EmployeePreviewDto;
import com.mastery.java.task.dto.EmployeeUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {
    EmployeeFullDto findById(Long id);

    Page<EmployeePreviewDto> findAll(Pageable pageable);

    EmployeeFullDto create(EmployeeCreateDto employeeCreateDto);

    EmployeeFullDto update(EmployeeUpdateDto employeeUpdateDto);

    void deleteById(Long id);
}