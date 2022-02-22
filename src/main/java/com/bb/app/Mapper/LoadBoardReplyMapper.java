package com.bb.app.Mapper;

import com.bb.app.DTO.LoadBoardReplyDto;
import com.bb.app.entity.BoardReplyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LoadBoardReplyMapper {

    @Mapping(source = "boardNo", target = "board.id")
    @Mapping(source = "writer", target = "writer.nickName")
    BoardReplyEntity toEntity(LoadBoardReplyDto boardReplyDto);

    @Mapping(source = "board.id", target = "boardNo")
    @Mapping(source = "writer.nickName", target = "writer")
    LoadBoardReplyDto toDto(BoardReplyEntity boardReplyEntity);

    List<LoadBoardReplyDto> toDto(List<BoardReplyEntity> boardReplyEntityList);
}
