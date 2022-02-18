package com.bb.app.service;

import com.bb.app.DTO.MessageDto;
import com.bb.app.Mapper.MessageMapper;
import com.bb.app.entity.MessageEntity;
import com.bb.app.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    private MessageRepository repository;

    public void MessageWrite(MessageDto msg){
        MessageEntity MsgEntity = MessageMapper.INSTANCE.toEntity(msg);
        repository.save(MsgEntity);
    }
    public MessageDto MessageView(long mno) {
        Optional<MessageEntity> msg = repository.findById(mno);
        MessageEntity msgEntity = msg.get();
        MessageDto msgDto = MessageMapper.INSTANCE.toDto(msgEntity);

        return msgDto;
    }

    public void MessageDelete(Long mno){
        repository.deleteById(mno);
    }
    public List<MessageEntity> MessageSendBox(long mno){
        List<MessageEntity> msg = repository.findBySenderId(mno);

        return msg;
    }
    public List<MessageEntity> MessageInBox(long mno){
        List<MessageEntity> msg = repository.findByReceiverId(mno);

        return msg;
    }
}
