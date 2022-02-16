package com.bb.app.controller;

import com.bb.app.entity.BoardEntity;
import com.bb.app.entity.MemberEntity;
import com.bb.app.service.BoardService;
import com.bb.app.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user/*")
public class MemberController {

    @Autowired
    private MemberService service;
    @Autowired
    private BoardService bservice;
    Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping("/signup")
    public ResponseEntity<Void> Signup(@RequestBody MemberEntity member) {
        MemberEntity m = member;
        service.Signup(m);


        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping("/idcheck")
    public Map<String, String> IdCheck(String id) {
        Map<String, String> response = new HashMap<>();
        Optional<MemberEntity> m = service.IdCheck(id);

        if(m.isPresent()) {
            response.put("msg","사용중인 아이디 입니다");
        } else {
            response.put("msg","사용가능한 아이디 입니다");
        }

        return response;
    }

    @PostMapping("/login")
    public Map<String, String> Login(@RequestBody MemberEntity member) {
        Map<String, String> response = new HashMap<>();

        logger.info("id : " + member.getLoginId() + ", password : " + member.getPassword());

        Optional<MemberEntity> m = service.Login(member.getLoginId(), member.getPassword());

        if(m.isPresent()) {
            response.put("msg","로그인 성공");
        } else {
            response.put("msg","로그인 실패");
        }

        return response;
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
