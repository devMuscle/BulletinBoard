package com.bb.app.controller;

import com.bb.app.DTO.MessageDto;
import com.bb.app.entity.MemberEntity;
import com.bb.app.entity.MessageEntity;
import com.bb.app.service.MemberService;
import com.bb.app.service.MessageService;
import org.aspectj.bridge.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/memo/*")
public class MessageController {
    //@Autowired
    private final MessageService messageService;
    //@Autowired
    private final MemberService memberService;
    Logger logger = LoggerFactory.getLogger(getClass());

    public MessageController(MessageService messageService, MemberService memberService){
        this.messageService = messageService;
        this.memberService = memberService;
    }
    @GetMapping("/*")
    public Object MsgAllBox(String loginId){
        logger.info(loginId);
        List<MessageDto> msgList = messageService.MessageAllBox(loginId);
        return msgList;
    }

    @GetMapping("/inbox/{mno}")
    public Object MsgInBox(@PathVariable Long mno){
        List<MessageDto> msgList = messageService.MessageInBox(mno);

        logger.info("MsgInBox 컨트롤어: " + msgList.size());
        return msgList;
    }
    @GetMapping("/sent/{mno}")
    public Object MsgSent(@PathVariable Long mno){
        List<MessageDto> msgList = messageService.MessageSent(mno);

        logger.info("MsgInBox 컨트롤어: " + msgList.size());
        return msgList;
    }
    @PostMapping("/*")
    public ResponseEntity<Void> MsgWrite(@RequestBody MessageDto msg){
        logger.info(String.valueOf("컨트롤러: " + msg.getSenderId()));
        messageService.MessageWrite(msg);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/view/{mno}")
    public Object MsgView(@PathVariable long mno){
        MessageDto msgDto = messageService.MessageView(mno);

//        Map<String, String> msgJason = new HashMap<>();
//        logger.info(String.valueOf(msgDto.getReceiver()));

//        msgJason.put("message_id", String.valueOf(msgDto.getId()));
//        msgJason.put("content", msgDto.getContent());
//        msgJason.put("read_status", String.valueOf(msgDto.getReadStatus()));
//        msgJason.put("receive_delete_status", String.valueOf(msgDto.getReceiveDeleteStatus()));
//        msgJason.put("send_delete_status", String.valueOf(msgDto.getSendDeleteStatus()));
//        msgJason.put("title", msgDto.getTitle());
//        msgJason.put("receiver_id", String.valueOf(msgDto.getReceiver().getId()));
        //msgJason.put("sender_id", String.valueOf(msgDto.getSender().getId()));

        return msgDto;
    }
    @DeleteMapping("/{mno}")
    public void MsgDelete(@PathVariable long mno){
        messageService.MessageDelete(mno);
    }
//    @GetMapping("/inbox/{id}")
//    public Object MsgInBox(@PathVariable String id){
//        List<MessageEntity> msg;
//        Optional<MemberEntity> m = memberService.FindInfo(id);
//
//        msg = m.get().getMessageList();
//        logger.info(String.valueOf(msg));
//        return msg;
//    }

}
