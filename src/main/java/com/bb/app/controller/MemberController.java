package com.bb.app.controller;

import com.bb.app.DTO.MemberDto;
import com.bb.app.DTO.MyBoardDto;
import com.bb.app.exception.DeleteException;
import com.bb.app.exception.DuplicatedIdException;
import com.bb.app.service.MemberService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody MemberDto member) {
        memberService.signup(member);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/idcheck")
    public ResponseEntity<Map<String, String>> idCheck(String loginId) {
        Map<String, String> response = new HashMap<>();

        try {
            memberService.idCheck(loginId);
            response.put("msg","사용가능한 아이디 입니다");
        } catch(DuplicatedIdException e) {
            response.put("msg", e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<MemberDto> login(@RequestBody MemberDto member) {
        try{
            MemberDto m = memberService.login(member.getLoginId(), member.getPassword());
            return new ResponseEntity<>(m, HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{loginId}")
    public ResponseEntity<MemberDto> memberInfo(@PathVariable String loginId) {
        try {
            MemberDto memberDto = memberService.findMemberInfo(loginId);
            return new ResponseEntity<>(memberDto, HttpStatus.OK);
        } catch(NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{loginId}")
    public ResponseEntity<Map<String, String>> memberUpdate(@PathVariable String loginId, @RequestBody MemberDto memberDto) {
        Map<String, String> response = new HashMap<>();

        try{
            memberService.memberUpdate(loginId, memberDto);
            response.put("msg","정보변경 성공");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (NoSuchElementException e){
            response.put("msg","정보변경 실패");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{loginId}")
    public ResponseEntity<Map<String, String>> memberDelete(@PathVariable String loginId){
        Map<String, String> response = new HashMap<>();

        try{
            memberService.memberDelete(loginId);
            response.put("msg","삭제 성공");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }catch (DeleteException e){
            response.put("msg",e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/my-write/board/{loginId}")
    public ResponseEntity<List<MyBoardDto>> myWriteBoard(@PathVariable String loginId){
        try{
            List<MyBoardDto> myBoardDtoList = memberService.findMyBoards(loginId);
            return new ResponseEntity<>(myBoardDtoList, HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
