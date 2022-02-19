package com.bb.app.Mapper;

import com.bb.app.DTO.BoardAttachDto;
import com.bb.app.DTO.MessageDto;
import com.bb.app.entity.BoardAttachEntity;
import com.bb.app.entity.MessageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BoardAttachMapper {
    BoardAttachMapper INSTANCE = Mappers.getMapper(BoardAttachMapper.class);

    BoardAttachEntity toEntity(BoardAttachDto boardAttachDto);
    BoardAttachDto toDto(BoardAttachEntity boardAttachEntity);
}
