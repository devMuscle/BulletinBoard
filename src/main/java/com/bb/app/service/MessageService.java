package com.bb.app.service;

import com.bb.app.DTO.MessageDto;
import com.bb.app.Mapper.MessageMapper;
import com.bb.app.entity.MemberEntity;
import com.bb.app.entity.MessageEntity;
import com.bb.app.repository.MemberRepository;
import com.bb.app.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class MessageService {

    private final MessageRepository messageRepository;
    private final MemberRepository memberRepository;

    public void MessageWrite(MessageDto msg){
        MessageEntity msgEntity = MessageMapper.INSTANCE.toEntity(msg);
        Optional<MemberEntity> mem = memberRepository.findById(msg.getSenderId());
        MemberEntity member = mem.get();
        member.UpdateMessageList(msgEntity);

    }

    @Transactional(readOnly = true)
    public MessageDto MessageView(long mno) {
        Optional<MessageEntity> msg = messageRepository.findById(mno);
        MessageEntity msgEntity = msg.get();
        MessageDto msgDto = MessageMapper.INSTANCE.toDto(msgEntity);

        return msgDto;
    }

    public void MessageDelete(Long mno){
        messageRepository.deleteById(mno);
    }


    public List<MessageDto> MessageAllBox(String loginId, Pageable pageable){
        Optional<MemberEntity> opMember = memberRepository.findByLoginId(loginId);
        MemberEntity memberEntity = opMember.orElseThrow();
        Long memberId = memberEntity.getId();

        /*
        List<MessageEntity> sentMsg = messageRepository.findBySenderId(mno);
        List<MessageEntity> receiveMsg = messageRepository.findByReceiverId(mno);
        List<MessageEntity> msgList = new ArrayList<>();
        msgList.addAll(sentMsg);
        msgList.addAll(receiveMsg);
        */

        List<MessageEntity> messageEntityList = messageRepository.findAllBySenderIdOrReceiverId(memberId, pageable);

        List<MessageDto> msgDto = MessageMapper.INSTANCE.toDtoList(messageEntityList);

        return msgDto;
    }


    public List<MessageDto> MessageInBox(String loginId, Pageable pageable){
        System.out.println(loginId);
        Optional<MemberEntity> opMember = memberRepository.findByLoginId(loginId);
        MemberEntity memberEntity = opMember.orElseThrow();
        Long memberId = memberEntity.getId();

        List<MessageEntity> msg = messageRepository.findByReceiverId(memberId, pageable);
        List<MessageDto> msgList = MessageMapper.INSTANCE.toDtoList(msg);
        return msgList;
    }

    public List<MessageDto> MessageSent(String loginId, Pageable pageable){
        Optional<MemberEntity> opMember = memberRepository.findByLoginId(loginId);
        MemberEntity memberEntity = opMember.orElseThrow();
        Long memberId = memberEntity.getId();

        List<MessageEntity> msg = messageRepository.findBySenderId(memberId, pageable);
        List<MessageDto> msgList = MessageMapper.INSTANCE.toDtoList(msg);
        return msgList;
    }
}













