package com.mastery.java.task.controller;

import com.mastery.java.task.dto.EmployeeCreateDto;
import com.mastery.java.task.dto.EmployeeFullDto;
import com.mastery.java.task.dto.EmployeePreviewDto;
import com.mastery.java.task.dto.EmployeeUpdateDto;
import com.mastery.java.task.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/employees")
@Api(description = "Controller dedicated to manage employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find one employee by id")
    public EmployeeFullDto findById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @GetMapping
    @ApiOperation(value = "Find all employees")
    public Page<EmployeePreviewDto> findAll(@PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {
        return employeeService.findAll(pageable);
    }

    @PostMapping
    @ApiOperation(value = "Create employee")
    public EmployeeFullDto create(@Valid @RequestBody EmployeeCreateDto employeeCreateDto) {
        return employeeService.create(employeeCreateDto);
    }

    @PutMapping
    @ApiOperation(value = "Update employees")
    public EmployeeFullDto update(@Valid @RequestBody EmployeeUpdateDto employeeUpdateDto) {
        return employeeService.update(employeeUpdateDto);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete employee by id")
    public void deleteById(@PathVariable Long id) {
        employeeService.deleteById(id);
    }
}