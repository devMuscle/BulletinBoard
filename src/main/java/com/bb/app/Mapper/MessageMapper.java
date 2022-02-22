package com.bb.app.Mapper;

import com.bb.app.DTO.MessageDto;
import com.bb.app.entity.MessageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    //@Mapping(target="register_date", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(source = "senderId", target = "sender.id")
    @Mapping(source = "receiverId", target = "receiver.id")
    MessageEntity toEntity(MessageDto messageDto);

    @Mapping(source = "sender.id", target = "senderId")
    @Mapping(source = "receiver.id", target = "receiverId")
    MessageDto toDto(MessageEntity messageEntity);


    List<MessageEntity> toEntityList(List<MessageDto> DtoList);

    List<MessageDto> toDtoList(List<MessageEntity> EntityList);

}