package com.bb.app.controller;

import com.bb.app.DTO.MemberDto;
import com.bb.app.entity.BoardEntity;
import com.bb.app.entity.MemberEntity;
import com.bb.app.exception.DuplicatedIdException;
import com.bb.app.service.BoardService;
import com.bb.app.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;
import java.util.*;

@RestController
@RequestMapping("/user/*")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService service;
    private final BoardService bservice;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping("/signup")
    public ResponseEntity<Void> Signup(@RequestBody MemberDto member) {
        service.Signup(member);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping("/idcheck")
    public ResponseEntity<Map<String, String>> IdCheck(String loginId) {
        Map<String, String> response = new HashMap<>();

        try {
            service.IdCheck(loginId);
            response.put("msg","사용가능한 아이디 입니다");
        } catch(DuplicatedIdException e) {
            response.put("msg", e.getMessage());
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<MemberDto> Login(@RequestBody MemberDto member) {
        try{
            MemberDto m = service.Login(member.getLoginId(), member.getPassword());
            return new ResponseEntity<>(m, HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{loginId}")
    public Object MemberInfo(@PathVariable String loginId) {

        logger.info("loginId : " + loginId);

        Optional<MemberEntity> m = service.FindInfo(loginId);

        MemberEntity member = m.get();

        return member;
    }

    @PutMapping("/{loginId}")
    public Map<String, String> MemberUpdate(@PathVariable String loginId, @RequestBody MemberEntity member) {

        Map<String, String> response = new HashMap<>();
        Optional<MemberEntity> m = service.FindInfo(loginId);

        if(m.isPresent()) {
            MemberEntity updateMember = m.get();
            updateMember.UpdatePassword(member.getPassword());
            updateMember.UpdateNickname(member.getNickName());
            updateMember.UpdateEmail(member.getEmail());
            service.MemberUpdate(updateMember);
            response.put("msg","정보변경 성공");
        }else {
            response.put("msg","정보변경 실패");
        }
        logger.info("loginId : " + loginId);

        return response;
    }
    @DeleteMapping("/{loginId}")
    @Transactional
    public Map<String, String> MemberDelete(@PathVariable String loginId){
        Map<String, String> response = new HashMap<>();
        service.MemberDelete(loginId);

        Optional<MemberEntity> m = service.FindInfo(loginId);

        if(m.isPresent()){
            response.put("msg","삭제 실패");
        }else{
            response.put("msg","삭제 성공");
        }

        return response;
    }
    @GetMapping("/my-write/board/{loginId}")
    public Object myWriteBoard(@PathVariable String loginId){
        List<BoardEntity> board;
        Optional<MemberEntity> m = service.FindInfo(loginId);
        board = m.get().getBoardList();

        return board;
    }
}
