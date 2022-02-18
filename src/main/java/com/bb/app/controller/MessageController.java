package com.bb.app.controller;

import com.bb.app.DTO.MessageDto;
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
    public ResponseEntity<Void> MsgWrite(@RequestBody MessageDto msg){
        logger.info(String.valueOf(msg.getTitle()));
        System.out.println("test"+ msg.getTitle());
        service.MessageWrite(msg);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/view/{mno}")
    public Map<String, String> MsgView(@PathVariable long mno){
        MessageDto msgDto = service.MessageView(mno);
        Map<String, String> msgJason = new HashMap<>();
        logger.info(String.valueOf(msgDto.getReceiver()));

        msgJason.put("message_id", String.valueOf(msgDto.getId()));
        msgJason.put("content", msgDto.getContent());
        msgJason.put("read_status", String.valueOf(msgDto.getReadStatus()));
        msgJason.put("receive_delete_status", String.valueOf(msgDto.getReceiveDeleteStatus()));
        msgJason.put("send_delete_status", String.valueOf(msgDto.getSendDeleteStatus()));
        msgJason.put("title", msgDto.getTitle());
        msgJason.put("receiver_id", String.valueOf(msgDto.getReceiver().getId()));
        msgJason.put("sender_id", String.valueOf(msgDto.getSender().getId()));

        return msgJason;
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
