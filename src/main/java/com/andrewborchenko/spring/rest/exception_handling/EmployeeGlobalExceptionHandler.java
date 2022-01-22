package com.andrewborchenko.spring.rest.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*данный класс вынесен отдельно для обработки всех restController*/
@ControllerAdvice
public class EmployeeGlobalExceptionHandler {
    /*данная аннотация показывает, что данный метод ответственнен за обработку исключений*/
    @ExceptionHandler
    /*ResponseEntity<EmployeeIncorrectData> возвращает http response EmployeeIncorrectData
    это тип объекта, который отправлется в http body выбрасывается когда получаем на вход
    NoSuchEmployeeException */
    public ResponseEntity<EmployeeIncorrectData> handleException(
            NoSuchEmployeeException exception) {
        EmployeeIncorrectData data = new EmployeeIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<EmployeeIncorrectData> handleException(
            Exception exception) {
        EmployeeIncorrectData data = new EmployeeIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
