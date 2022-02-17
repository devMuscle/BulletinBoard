package com.bb.app.service;

import com.bb.app.entity.MemberEntity;
import com.bb.app.exception.DuplicatedIdException;
import com.bb.app.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    MemberRepository repository;

    public void Signup(MemberEntity member) {
        repository.save(member);
    }

    public void IdCheck(String id) throws DuplicatedIdException {
        Optional<MemberEntity> member = repository.findByloginId(id);

        if(member.isPresent()) {
            throw new DuplicatedIdException("이미 존재하는 id 입니다.");
        }
    }

    public Optional<MemberEntity> Login(String id, String pwd) {
        Optional<MemberEntity> member = repository.findByLoginIdAndPassword(id, pwd);
        return member;
    }

    public Optional<MemberEntity> FindInfo(String id) {
        Optional<MemberEntity> member = repository.findByloginId(id);
        return member;
    }
    public void MemberUpdate(MemberEntity member){
        repository.save(member);
    }
    public void MemberDelete(String id){
        repository.deleteByLoginId(id);
    }
}
