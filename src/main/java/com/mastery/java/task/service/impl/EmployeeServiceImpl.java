package com.mastery.java.task.service.impl;

import com.mastery.java.task.dto.EmployeeCreateDto;
import com.mastery.java.task.dto.EmployeeFullDto;
import com.mastery.java.task.dto.EmployeePreviewDto;
import com.mastery.java.task.dto.EmployeeUpdateDto;
import com.mastery.java.task.entity.Employee;
import com.mastery.java.task.entity.Gender;
import com.mastery.java.task.exception.EmployeeNotFoundException;
import com.mastery.java.task.exception.EntityIsNotFoundException;
import com.mastery.java.task.mapper.EmployeeMapper;
import com.mastery.java.task.repository.EmployeeRepository;
import com.mastery.java.task.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

//    private static final Logger log = Logger.getLogger(EmployeeServiceImpl.class);

    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;

    @Override
    @Transactional(readOnly = true)
    public EmployeeFullDto findById(Integer id) {
        EmployeeFullDto foundEmployee = employeeRepository.findById(id)
                .map(employeeMapper::mapToFullDto)
                .orElseThrow(() -> new EntityIsNotFoundException(String.format("Employee was not found by id: %s", id)));
        log.info("EmployeeServiceImpl -> found employee: {}", foundEmployee);
//        log.info("EmployeeServiceImpl -> find employee ID" + id);
//        log.error("Employee wasn't found");
        return foundEmployee;
    }

    @Override
    public Page<EmployeePreviewDto> findAll(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page == null ? 0 : page,
                size == null ? Integer.MAX_VALUE : size);
        Page<EmployeePreviewDto> foundEmployees = employeeRepository.findAll(pageRequest).map(employeeMapper::mapToPreviewDto);
        log.info("EmployeeServiceImpl -> found employees: {}", foundEmployees);
//        log.info("EmployeeServiceImpl -> found employees");
//        log.error("Employees weren't found");
        return foundEmployees;
    }

    @Override
    public EmployeeFullDto create(EmployeeCreateDto employeeCreateDto) {
        Employee employeeToSave = employeeMapper.mapToEntity(employeeCreateDto);

        employeeToSave.setFirstName(employeeCreateDto.getFirstName());
        employeeToSave.setLastName(employeeCreateDto.getLastName());
        employeeToSave.setGender(Gender.MALE);
        employeeToSave.setDepartmentId(employeeCreateDto.getDepartmentId());
        employeeToSave.setJobTittle(employeeCreateDto.getJobTittle());
        employeeToSave.setDateOfBirth(employeeCreateDto.getDateOfBirth());

        Employee savedEmployee = employeeRepository.save(employeeToSave);

//        EmployeeFullDto employeeFullDto = saveEmployee(employeeToSave);
//        log.info("EmployeeServiceImpl -> employee {} successfully saved", employeeFullDto);
        log.info("EmployeeServiceImpl -> employee {} successfully saved", savedEmployee);
//        log.info("EmployeeServiceImpl -> employee {} successfully saved");
//        log.error("Employee wasn't saved");
        return employeeMapper.mapToFullDto(savedEmployee);
    }

    @Override
    @Transactional
    public EmployeeFullDto update(EmployeeUpdateDto employeeUpdateDto) {
        Employee employeeToUpdate = employeeRepository.findById(employeeUpdateDto.getEmployeeId())
                .orElseThrow(() -> new EntityIsNotFoundException(String.format("Employee was not found by id: %s", employeeUpdateDto.getEmployeeId())));

        employeeMapper.updateEntity(employeeUpdateDto, employeeToUpdate);
        Employee updatedEmployee = employeeRepository.save(employeeToUpdate);
        log.info("EmployeeServiceImpl -> employee {} was successfully updated", updatedEmployee);
//        log.info("EmployeeServiceImpl -> employee {} was successfully updated");
//        log.error("Employee wasn't update");
        return employeeMapper.mapToFullDto(updatedEmployee);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(String.format("Employee was not found by id: %s", id)));
        ;
        employeeRepository.deleteById(id);
        log.info("EmployeeServiceImpl -> employee by id: {} successfully deleted", id);
//        log.info("EmployeeServiceImpl -> employee successfully deleted");
//        log.error("Employee wasn't deleted");
    }

    @Override
    public EmployeeFullDto findByFirstName(String name) {
        EmployeeFullDto foundEmployee = employeeRepository.findByFirstName(name)
                .map(employeeMapper::mapToFullDto)
                .orElseThrow(() -> new EntityIsNotFoundException(String.format("Employee was not found by firstName: %s", name)));

        log.info("EmployeeServiceImpl -> found employee : {} by firstName: {}", foundEmployee, name);
        return foundEmployee;
    }

//    private EmployeeFullDto saveEmployee(Employee newEmployee) {
//        Employee employee = employeeRepository.save(newEmployee);
//        EmployeeFullDto employeeFullDto = employeeMapper.mapToFullDto(employee);
//        employeeFullDto.setFirst_name(employee.getFirst_name());
//        employeeFullDto.setLast_name(employee.getLast_name());
//        employeeFullDto.setGender(employee.getGender());
//        employeeFullDto.setDepartment_id(employee.getDepartment_id());
//        employeeFullDto.setJob_tittle(employee.getJob_tittle());
//        employeeFullDto.setDate_of_birth(employee.getDate_of_birth());
//        return employeeFullDto;
//    }


}