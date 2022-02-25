package com.bb.app.Mapper;

import com.bb.app.DTO.SaveBoardReplyDto;
import com.bb.app.entity.BoardReplyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SaveBoardReplyMapper {

    @Mapping(source = "boardNo", target = "board.id")
    @Mapping(source = "writer", target = "writer.id")
    BoardReplyEntity toEntity(SaveBoardReplyDto boardReplyDto);

    @Mapping(source = "board.id", target = "boardNo")
    @Mapping(source = "writer.id", target = "writer")
    SaveBoardReplyDto toDto(BoardReplyEntity boardReplyEntity);
}
