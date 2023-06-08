package com.ivw.spring.demo.controller;

import com.ivw.spring.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;

@Controller
public class TestController {

    @Autowired
    TestService testService;
    @GetMapping("/getAll")
    public ResponseEntity<String> getAll() throws SQLException {
        testService.getAll();
        return new ResponseEntity("Success", HttpStatus.OK);

    }
}
