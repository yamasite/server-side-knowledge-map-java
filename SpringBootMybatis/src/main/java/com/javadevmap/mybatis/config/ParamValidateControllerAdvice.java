package com.javadevmap.mybatis.config;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.ls.LSInput;

import com.javadevmap.mybatis.result.Result;


@ControllerAdvice
public class ParamValidateControllerAdvice {

    private final static Logger logger = LoggerFactory.getLogger(ParamValidateControllerAdvice.class);

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public Result<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    	return handleFieldErrors(e.getBindingResult());
    }

    public Result<String> handleFieldErrors(BindingResult bindingResult) {

        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        
        List<String> errors = new ArrayList();
        for(FieldError error : fieldErrors) {
        	errors.add(""+error.getField()+":"+error.getDefaultMessage());
        }

        Result<String> result = new Result<>();
        result.setResultCode(400);
        result.setMsg(errors.toString());
        return result;
    }
}