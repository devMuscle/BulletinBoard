package com.bb.app.controller;

import com.bb.app.DTO.MessageDto;
import com.bb.app.entity.MemberEntity;
import com.bb.app.entity.MessageEntity;
import com.bb.app.service.MemberService;
import com.bb.app.service.MessageService;
import lombok.AllArgsConstructor;
import org.aspectj.bridge.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/memo")
@AllArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping("")
    public Object MsgAllBox(String loginId, @PageableDefault(size = 8) Pageable pageable){
        List<MessageDto> msgList = messageService.MessageAllBox(loginId, pageable);
        return msgList;
    }


    @GetMapping("/inbox/{loginId}")
    public Object MsgInBox(@PathVariable String loginId, @PageableDefault(size = 8) Pageable pageable){
        List<MessageDto> msgList = messageService.MessageInBox(loginId, pageable);

        return msgList;
    }

    @GetMapping("/sent/{loginId}")
    public Object MsgSent(@PathVariable String loginId, @PageableDefault(size = 8) Pageable pageable){
        List<MessageDto> msgList = messageService.MessageSent(loginId, pageable);

        return msgList;
    }

    @PostMapping("")
    public ResponseEntity<Void> MsgWrite(@RequestBody MessageDto msg){
        messageService.MessageWrite(msg);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{mno}")
    public Object MsgView(@PathVariable long mno){
        MessageDto msgDto = messageService.MessageView(mno);

        return msgDto;
    }

    @DeleteMapping("/{mno}")
    public void MsgDelete(@PathVariable long mno){
        messageService.MessageDelete(mno);
    }

}
