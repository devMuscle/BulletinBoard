package com.bb.app.controller;

import com.bb.app.entity.MemberEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/main/*")
public class HomeController {

    @GetMapping("/index")
    public Map<String, Integer> Main() {
        Map<String, Integer> responseMap = new HashMap<>();

        responseMap.put("response", 1);

        return responseMap;
    }
}