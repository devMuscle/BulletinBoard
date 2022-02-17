package com.bb.app.Mapper;

import com.bb.app.DTO.MessageDto;
import com.bb.app.entity.MessageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    //@Mapping(target="register_date", expression = "java(java.time.LocalDateTime.now())")
    MessageEntity toEntity(MessageDto messageDto);

    MessageDto toDto(MessageEntity messageEntity);

}
