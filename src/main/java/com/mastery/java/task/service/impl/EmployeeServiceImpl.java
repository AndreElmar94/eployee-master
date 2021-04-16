package com.mastery.java.task.service.impl;

import com.mastery.java.task.dto.EmployeeCreateDto;
import com.mastery.java.task.dto.EmployeeFullDto;
import com.mastery.java.task.dto.EmployeePreviewDto;
import com.mastery.java.task.dto.EmployeeUpdateDto;
import com.mastery.java.task.entity.Employee;
import com.mastery.java.task.exception.EmployeeNotFoundException;
import com.mastery.java.task.exception.EntityIsNotFoundException;
import com.mastery.java.task.repository.EmployeeRepository;
import com.mastery.java.task.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.mastery.java.task.mapper.EmployeeMapper.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    @Transactional(readOnly = true)
    public EmployeeFullDto findById(Long id) {
        EmployeeFullDto foundEmployee = employeeRepository.findById(id)
                .map(EMPLOYEE_MAPPER::mapToFullDto)
                .orElseThrow(() -> new EntityIsNotFoundException(String.format("Employee was not found by id: %s", id)));
        log.info("EmployeeServiceImpl -> found employee: {}", foundEmployee);
        return foundEmployee;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EmployeePreviewDto> findAll(Pageable pageable) {
        Page<Employee> foundEmployees = employeeRepository.findAll(pageable);
        log.info("EmployeeServiceImpl -> found employees: {}", foundEmployees);
        return foundEmployees.map(EMPLOYEE_MAPPER::mapToPreviewDto);
    }

    @Override
    @Transactional
    public EmployeeFullDto create(EmployeeCreateDto employeeCreateDto) {
        Employee employeeToSave = EMPLOYEE_MAPPER.mapToEntity(employeeCreateDto);

        employeeToSave.setFirst_name(employeeCreateDto.getFirst_name());
        employeeToSave.setLast_name(employeeCreateDto.getLast_name());
        employeeToSave.setGender(employeeCreateDto.getGender());
        employeeToSave.setDepartament_id(employeeCreateDto.getDepartament_id());
        employeeToSave.setJob_tittle(employeeCreateDto.getJob_tittle());
        employeeToSave.setDate_of_birth(employeeCreateDto.getDate_of_birth());

        EmployeeFullDto employeeFullDto = saveEmployee(employeeToSave);
        log.info("EmployeeServiceImpl -> employee {} successfully saved", employeeFullDto);
        return employeeFullDto;
    }

    @Override
    @Transactional
    public EmployeeFullDto update(EmployeeUpdateDto employeeUpdateDto) {
        Employee employeeToUpdate = employeeRepository.findById(employeeUpdateDto.getEmployee_id())
                .orElseThrow(() -> new EntityIsNotFoundException(String.format("Employee was not found by id: %s", employeeUpdateDto.getEmployee_id())));

        EMPLOYEE_MAPPER.updateEntity(employeeUpdateDto,employeeToUpdate);
        Employee updatedEmployee = employeeRepository.save(employeeToUpdate);
        log.info("EmployeeServiceImpl -> employee {} was successfully updated", updatedEmployee);
        return EMPLOYEE_MAPPER.mapToFullDto(updatedEmployee);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(String.format("Employee was not found by id: %s", id)));;
        employeeRepository.deleteById(id);
        log.info("EmployeeServiceImpl -> employee by id: {} successfully deleted", id);
    }

    private EmployeeFullDto saveEmployee(Employee newEmployee) {
        Employee employee = employeeRepository.save(newEmployee);
        EmployeeFullDto employeeFullDto = EMPLOYEE_MAPPER.mapToFullDto(employee);
        employeeFullDto.setFirst_name(employee.getFirst_name());
        employeeFullDto.setLast_name(employee.getLast_name());
        employeeFullDto.setGender(employee.getGender());
        employeeFullDto.setDepartament_id(employee.getDepartament_id());
        employeeFullDto.setJob_tittle(employee.getJob_tittle());
        employeeFullDto.setDate_of_birth(employee.getDate_of_birth());
        return employeeFullDto;
    }
}