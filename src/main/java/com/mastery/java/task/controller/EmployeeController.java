package com.mastery.java.task.controller;

import com.mastery.java.task.dto.EmployeeCreateDto;
import com.mastery.java.task.dto.EmployeeFullDto;
import com.mastery.java.task.dto.EmployeePreviewDto;
import com.mastery.java.task.dto.EmployeeUpdateDto;
import com.mastery.java.task.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/employees")
@Api
@AllArgsConstructor
public class EmployeeController {
    
    private final EmployeeService employeeService;


    @GetMapping("/{id}")
    @ApiOperation(value = "Find one employee by id")
    public EmployeeFullDto findById(@PathVariable Integer id) {
        return employeeService.findById(id);
    }

    @GetMapping
    @ApiOperation(value = "Find all employees")
    public Page<EmployeePreviewDto> findAll(@RequestParam(required = false) Integer page,
                                            @RequestParam(required = false) Integer size) {
        return employeeService.findAll(page, size);
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
    public void deleteById(@PathVariable Integer id) {
        employeeService.deleteById(id);
    }

    @GetMapping("FirstName/{firstName}")
    @ApiOperation(value = "Find one employee by first name")
    public EmployeeFullDto findByFirstName(@PathVariable String firstName) {
        return employeeService.findByFirstName(firstName);
    }
}