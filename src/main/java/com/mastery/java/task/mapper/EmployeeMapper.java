package com.mastery.java.task.mapper;

import com.mastery.java.task.dto.EmployeeCreateDto;
import com.mastery.java.task.dto.EmployeeFullDto;
import com.mastery.java.task.dto.EmployeePreviewDto;
import com.mastery.java.task.dto.EmployeeUpdateDto;
import com.mastery.java.task.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

//    EmployeeMapper EMPLOYEE_MAPPER = Mappers.getMapper(EmployeeMapper.class);

    EmployeeFullDto mapToFullDto(Employee employee);

    Employee mapToEntity(EmployeeCreateDto employeeCreateDto);

    EmployeePreviewDto mapToPreviewDto(Employee employee);

    void updateEntity(EmployeeUpdateDto employeeUpdateDto, @MappingTarget Employee employee);
}