package com.app.gradationback.aspect.sepect;

import com.app.gradationback.exception.AuctionException;
import com.app.gradationback.exception.BiddingException;
import com.app.gradationback.exception.PaymentException;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.exceptions.PersistenceException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.nio.file.NoSuchFileException;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Configuration
@Slf4j
public class ExceptionResponseAspect {
    @Around("@annotation(com.app.gradationback.aspect.annotation.ExceptionResponse)")
    public Object handleExceptionAndRespond(ProceedingJoinPoint joinPoint) throws Throwable{
        Map<String, Object> response = new HashMap<>();
        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            response.put("request" + arg.getClass().getSimpleName(), arg);
        }

        response.put("timestamp", LocalDateTime.now());

        try {
            return joinPoint.proceed();
        }catch (NoSuchFileException noSuchFileException) {
            return FileCopyUtils.copyToByteArray(new File("c:/upload/images/exception/no_such_file.jpg"));
        }catch (AuctionException auctionException) {
            response.put("message", "경매 API 응답 실패");
            response.put("error", auctionException.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }catch (BiddingException biddingException) {
            response.put("message", "응찰 API 응답 실패");
            response.put("error", biddingException.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }catch (PaymentException paymentException) {
            response.put("message", "결제 API 응답 실패");
            response.put("error", paymentException.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }catch (Exception e) {
            response.put("message", "알수 없는 오류");
            response.put("error", e.getMessage());
            response.put("cause", e.getCause());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }
}
