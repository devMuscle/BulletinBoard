package com.bb.app.service;

import com.bb.app.entity.MemberEntity;
import com.bb.app.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    MemberRepository repository;

    public void Signup(MemberEntity member) {
        repository.save(member);
    }
}
