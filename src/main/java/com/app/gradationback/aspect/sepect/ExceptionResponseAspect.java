package com.app.gradationback.aspect.sepect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Configuration
public class ExceptionResponseAspect {
    @Around("@annotation(com.app.gradationback.aspect.annotation.ExceptionResponse)")
    public ResponseEntity<Map<String, Object>> handleExceptionAndRespond(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return (ResponseEntity<Map<String, Object>>)joinPoint.proceed();
        }catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "API 응답 실패");
            response.put("error", e.getMessage());
            response.put("timestamp", LocalDateTime.now());

            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }
}
