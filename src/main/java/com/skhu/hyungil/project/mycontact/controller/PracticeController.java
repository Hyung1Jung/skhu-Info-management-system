package com.skhu.hyungil.project.mycontact.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PracticeController {
    @GetMapping(value = "/api/practice")
    public String practice() {
        return "practice";
    }

    @GetMapping(value = "/api/practiceException")
    public String practiceException() {
        throw new RuntimeException("practice RuntimeException"); // 말도 안되는 API지만 항상 오류를 발생시킨다.
    }

}
