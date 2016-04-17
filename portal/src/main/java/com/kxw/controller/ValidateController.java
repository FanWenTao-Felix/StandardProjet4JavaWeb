package com.kxw.controller;

import com.kxw.model.RequestParameter;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by kxw on 2015/9/30.
 */

@RestController
@RequestMapping("/validate")
public class ValidateController {


    @ExceptionHandler
    public String handleException(Exception e) {
        return e.getMessage();
    }

    /**
     * {<a href='http://haohaoxuexi.iteye.com/blog/1812584'>@link</a>}
     * {<a href='http://localhost:8084/portal/validate?number=df'>@link</a>}
     * 暂未调通
     */
    @RequestMapping
    public String validate(@Valid RequestParameter input,BindingResult result){

        if (result.hasErrors()) {
            return result.getFieldError().getDefaultMessage();
        }

        return "number:" + input.getNumber();
    }

}
