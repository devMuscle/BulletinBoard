package com.bb.app.Mapper;

import com.bb.app.DTO.BoardReplyDto;
import com.bb.app.entity.BoardReplyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BoardReplyMapper {
    BoardReplyMapper INSTANCE = Mappers.getMapper(BoardReplyMapper.class);

    BoardReplyEntity toEntity(BoardReplyDto boardReplyDto);
    BoardReplyDto toDto(BoardReplyEntity boardReplyEntity);
}
