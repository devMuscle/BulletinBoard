package com.bb.app.controller;

import com.bb.app.entity.MemberEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://ec2-13-209-50-25.ap-northeast-2.compute.amazonaws.com/", allowedHeaders = "*")
@RequestMapping("/main/*")
public class HomeController {

    @GetMapping("/index")
    public Map<String, Integer> Main() {
        Map<String, Integer> responseMap = new HashMap<>();

        responseMap.put("response", 1);

        return responseMap;
    }
}
