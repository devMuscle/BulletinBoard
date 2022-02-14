package com.bb.app.controller;

import com.bb.app.entity.MemberEntity;
import com.bb.app.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/*")
public class MemberController {

    @Autowired
    private MemberService service;

    @PostMapping("/signup")
    public ResponseEntity<Void> Signup(@RequestBody MemberEntity member) {
        MemberEntity m = member;
        service.Signup(m);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
