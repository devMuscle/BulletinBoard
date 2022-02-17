package com.bb.app.controller;

import com.bb.app.entity.MessageEntity;
import com.bb.app.service.MemberService;
import com.bb.app.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/memo/*")
public class MessageController {
    @Autowired
    private MessageService service;
    @Autowired
    private MemberService mService;
    Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping("/write")
    public ResponseEntity<Void> MsgWrite(@RequestBody MessageEntity msg){
        service.MessageWrite(msg);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/view/{mno}")
    public Object MsgView(@PathVariable long mno){
        Optional<MessageEntity> msg = service.MessageView(mno);
        Map<String, String> response = new HashMap<>();
        if(msg.isPresent()){
            response.put("제목", msg.get().getTitle());
            response.put("내용", msg.get().getContent());
            response.put("보낸이", String.valueOf(msg.get().getSender().getLoginId()));
            response.put("받는이", String.valueOf(msg.get().getReceiver().getLoginId()));
        }

        return response;
    }
    @DeleteMapping("/{mno}")
    public void MsgDelete(@PathVariable long mno){
        service.MessageDelete(mno);
    }
//    @GetMapping("/inbox/{id}")
//    public Object MsgInBox(@PathVariable String id){
//        List<MessageEntity> msg;
//        Optional<MemberEntity> m = mService.FindInfo(id);
//
//        msg = m.get().getMessageList();
//        logger.info(String.valueOf(msg));
//        return msg;
//    }
    @GetMapping("/inbox/{id}")
    public Object MsgInBox(@PathVariable long id){
        List<MessageEntity> msg = service.MessageInBox(id);

        logger.info(String.valueOf(msg));
        return msg;
    }
}
