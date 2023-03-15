package org.iesvdm.controller;


import lombok.extern.slf4j.Slf4j;
import org.iesvdm.exception.CategoriaNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class CategoriaNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(CategoriaNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String CategoriaNotFoundHandler(CategoriaNotFoundException categoriaNotFoundException) {
        log.debug("Excepción - %s", categoriaNotFoundException.toString());
        return categoriaNotFoundException.getMessage();
    }

}
