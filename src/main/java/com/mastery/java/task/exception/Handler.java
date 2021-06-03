package com.mastery.java.task.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j    // рные лояуты законфигурировать, время формат даты когда случился лог! logforJ logforbag
// почему используется и что этам вообще @Slf4j

@RestControllerAdvice
public class Handler {

    // аннотацию возврата вместо -> ResponseEntity<Object> чтоб вернул СТРИНг
    // возвращает статус через аннотацию

    @ExceptionHandler(EntityIsNotFoundException.class) // Employyee -> вместо Entity
    public ResponseEntity<Object> handle(EntityIsNotFoundException exception) {
        log.info(exception.getMessage(), exception); // -> выводит с stack trace
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);

        // при получении списка ВСЕХ -> детальный лог расписать

        // error Hanler EmployeeServiceNotFoundExeption -> ДЛЯ всех общий
        // 2 exception - EmployeeServiceException
        // 3 RestControllerAdvice - центральный для всех обработчиков
        //  клиент олжен получать сообщение об ошибке
        // todo ОДИН одбщий хэндлер ДЛЯ всех существующих ситуаций ДОПЕРЕТЬ какой ?????

    }
}