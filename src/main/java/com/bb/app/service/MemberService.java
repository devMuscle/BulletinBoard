package com.bb.app.service;

import com.bb.app.DTO.MemberDto;
import com.bb.app.Mapper.MemberMapper;
import com.bb.app.entity.MemberEntity;
import com.bb.app.exception.DuplicatedIdException;
import com.bb.app.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void Signup(MemberDto memberDto) {
        MemberEntity memberEntity = MemberMapper.INSTANCE.toEntity(memberDto);
        memberRepository.save(memberEntity);
    }

    public void IdCheck(String loginId) throws DuplicatedIdException {
        Optional<MemberEntity> member = memberRepository.findByLoginId(loginId);

        if(member.isPresent()) {
            throw new DuplicatedIdException("이미 존재하는 id 입니다.");
        }
    }

    public MemberDto Login(String id, String pwd) {
        Optional<MemberEntity> opMember = memberRepository.findByLoginIdAndPassword(id, pwd);
        MemberEntity member = opMember.orElseThrow();
        MemberDto memberDto = MemberMapper.INSTANCE.toDto(member);
        return memberDto;
    }

    public Optional<MemberEntity> FindInfo(String id) {
        Optional<MemberEntity> member = memberRepository.findByLoginId(id);
        return member;
    }
    public void MemberUpdate(MemberEntity member){
        memberRepository.save(member);
    }
    public void MemberDelete(String id){
        memberRepository.deleteByLoginId(id);
    }
}
