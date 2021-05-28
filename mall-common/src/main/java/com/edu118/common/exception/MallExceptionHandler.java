package com.edu118.common.exception;

import com.edu118.common.utils.R;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.BindException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *
 * @Date 2021-01-27 13:53
 * @Author huangshaowu
 *
 */
@RestControllerAdvice
public class MallExceptionHandler {
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public R MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        Map<String, Object> errorData = new HashMap<String, Object>();
        for (FieldError error : fieldErrors) {
            errorData.put(error.getField() + "Error",error.getDefaultMessage());
        }
        return R.error(400, "数据验证失败！").put("data", errorData);
    }
}
