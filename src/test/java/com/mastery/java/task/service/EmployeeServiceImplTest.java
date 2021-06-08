package com.mastery.java.task.service;

import com.mastery.java.task.dto.EmployeeCreateDto;
import com.mastery.java.task.dto.EmployeeFullDto;
import com.mastery.java.task.dto.EmployeePreviewDto;
import com.mastery.java.task.dto.EmployeeUpdateDto;
import com.mastery.java.task.entity.Employee;
import com.mastery.java.task.exception.EntityIsNotFoundException;
import com.mastery.java.task.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.mastery.java.task.util.DtoGenerationUtils.generateEmployeeCreateDto;
import static com.mastery.java.task.util.DtoGenerationUtils.generateEmployeeUpdateDto;
import static com.mastery.java.task.util.EntityGenerationUtils.generateEmployee;
import static com.mastery.java.task.util.EntityGenerationUtils.generateListEmployee;
import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EmployeeServiceImplTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @BeforeEach
    void setup() {
        employeeRepository.deleteAll();
    }

    @Test
    void findById_happyPath() {
        //given
        Employee employeeSaved = employeeRepository.save(generateEmployee());

        //when
        EmployeeFullDto employeeFound = employeeService.findById(employeeSaved.getEmployeeId());

        //then
        assertNotNull(employeeFound);
        assertEquals(employeeSaved.getEmployeeId(), employeeFound.getEmployeeId());
    }

    @Test
    void findByName_happyPath() {
        //given
        Employee employeeSaved = employeeRepository.save(generateEmployee());

        //when
        EmployeeFullDto employeeFound = employeeService.findByFirstName(employeeSaved.getFirstName());

        //then
        assertNotNull(employeeFound);
        assertEquals(employeeSaved.getEmployeeId(), employeeFound.getEmployeeId());
    }

    @Test
    void findAll_happyPath() {
        //given
        List<Employee> employeesSaved = employeeRepository.saveAll(generateListEmployee());

        //when
        List<EmployeePreviewDto> employeesFound = employeeService.findAll(0, 10).getContent();

        //then
        assertEquals(employeesSaved.size(), employeesFound.size());
    }

    @Test
    void create_happyPath() {
        //given
        EmployeeCreateDto employeeToSave = generateEmployeeCreateDto();

        //when
        EmployeeFullDto savedEmployee = employeeService.create(employeeToSave);

        //then
        assertNotNull(savedEmployee);
        assertEquals(employeeToSave.getFirstName(), savedEmployee.getFirstName());
        assertEquals(employeeToSave.getLastName(), savedEmployee.getLastName());
        assertEquals(employeeToSave.getDateOfBirth(), savedEmployee.getDateOfBirth());
        assertEquals(employeeToSave.getGender(), savedEmployee.getGender());
        assertEquals(employeeToSave.getJobTittle(), savedEmployee.getJobTittle());
        assertEquals(employeeToSave.getDepartmentId(), savedEmployee.getDepartmentId());
    }

    @Test
    void update_happyPath() {
        //given
        EmployeeCreateDto employeeCreateDto = generateEmployeeCreateDto();

        EmployeeFullDto employeeCreated = employeeService.create(employeeCreateDto);

        EmployeeUpdateDto employeeUpdateDto = generateEmployeeUpdateDto(employeeCreated.getEmployeeId());

        //when
        EmployeeFullDto employeeUpdated = employeeService.update(employeeUpdateDto);

        //then
        assertNotNull(employeeUpdated);
        assertEquals(employeeCreated.getEmployeeId(), employeeUpdated.getEmployeeId());
        assertNotEquals(employeeCreated.getFirstName(), employeeUpdated.getFirstName());
        assertNotEquals(employeeCreated.getLastName(), employeeUpdated.getLastName());
    }

    @Test
    void deleteById_happyPath() {
        //given
        EmployeeFullDto savedEmployee = employeeService.create(generateEmployeeCreateDto());
        Integer savedEmployeeId = savedEmployee.getEmployeeId();

        //when
        employeeService.deleteById(savedEmployeeId);

        //then
        assertThrows(EntityIsNotFoundException.class, () -> employeeService.findById(savedEmployeeId));
    }

    @Test
    void deleteById_whenNotFound() {
        //given
        Integer notExisting = 1000;

        //when
        Exception exception = assertThrows(EntityIsNotFoundException.class, () -> employeeService.deleteById(notExisting));

        //then
        assertTrue(exception.getMessage().contains(String.valueOf(notExisting)));
    }
}