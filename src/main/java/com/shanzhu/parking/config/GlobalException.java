package com.shanzhu.parking.config;

import com.shanzhu.parking.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Global exception handling, global exception interception (intercept all exceptions in the project)
 *
 * @author: Cheng Zi
 * @date: 2024-09-21
 */
@Slf4j
@ControllerAdvice
public class GlobalException {

    @ResponseBody
    @ExceptionHandler
    public R<Object> handlerException(Exception e, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        log.error("Global exception capture", e);
        // Return to the front end
        return R.error(e.getMessage());
    }

}
