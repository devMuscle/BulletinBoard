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
//    public List<MessageEntity> MessageSendBox(long mno){
//        List<MessageEntity> msg = messageRepository.findBySenderId(mno);
//
//        return msg;
//    }
    public List<MessageDto> MessageInBox(long mno){
        List<MessageEntity> msg = messageRepository.findBySenderId(mno);
        List<MessageDto> msgList = MessageMapper.INSTANCE.toDtoList(msg);
        logger.info(String.valueOf(msgList.get(0).getSenderId()));
        return msgList;
    }
}
