package com.bb.app.service;

import com.bb.app.DTO.MessageDto;
import com.bb.app.Mapper.MessageMapper;
import com.bb.app.entity.MemberEntity;
import com.bb.app.entity.MessageEntity;
import com.bb.app.repository.MemberRepository;
import com.bb.app.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    //@Autowired
    private final MessageRepository messageRepository;
    private final MemberRepository memberRepository;
    Logger logger = LoggerFactory.getLogger(getClass());

    public MessageService(MessageRepository messageRepository, MemberRepository memberRepository){
        this.messageRepository = messageRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public void MessageWrite(MessageDto msg){
        logger.info(String.valueOf("서비스 센더: "+ msg.getSenderId()));
        MessageEntity msgEntity = MessageMapper.INSTANCE.toEntity(msg);
        Optional<MemberEntity> mem = memberRepository.findById(msg.getSenderId());
        MemberEntity member = mem.get();
        member.UpdateMessageList(msgEntity);
    }
    public MessageDto MessageView(long mno) {
        Optional<MessageEntity> msg = messageRepository.findById(mno);
        MessageEntity msgEntity = msg.get();
        MessageDto msgDto = MessageMapper.INSTANCE.toDto(msgEntity);

        logger.info("뷰 서비스: "+msgDto.getSenderId());

        return msgDto;
    }

    public void MessageDelete(Long mno){
        messageRepository.deleteById(mno);
    }

    public List<MessageDto> MessageAllBox(String loginId){
        Optional<MemberEntity> member = memberRepository.findByloginId(loginId);
        Long mno = member.get().getId();
        logger.info("서비스 mno : "+String.valueOf(mno));

        List<MessageEntity> sentMsg = messageRepository.findBySenderId(mno);
        List<MessageEntity> receiveMsg = messageRepository.findByReceiverId(mno);
        List<MessageEntity> msgList = new ArrayList<>();
//        logger.info(sentMsg.get().getReceiver());
//        logger.info(receiveMsg.get().getReceiver());
        msgList.addAll(sentMsg);
        msgList.addAll(receiveMsg);
        logger.info(String.valueOf(msgList));

        List<MessageDto> msgDto = MessageMapper.INSTANCE.toDtoList(msgList);

        return msgDto;
    }

    public List<MessageDto> MessageInBox(Long mno){
        List<MessageEntity> msg = messageRepository.findBySenderId(mno);
        List<MessageDto> msgList = MessageMapper.INSTANCE.toDtoList(msg);
        logger.info(String.valueOf(msgList.get(0).getSenderId()));
        return msgList;
    }

    public List<MessageDto> MessageSent(Long mno){
        List<MessageEntity> msg = messageRepository.findByReceiverId(mno);
        List<MessageDto> msgList = MessageMapper.INSTANCE.toDtoList(msg);
        logger.info(String.valueOf(msgList.get(0).getSenderId()));
        return msgList;
    }
}













