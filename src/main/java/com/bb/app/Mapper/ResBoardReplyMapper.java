package com.bb.app.Mapper;

import com.bb.app.DTO.ResBoardReplyDto;
import com.bb.app.entity.BoardReplyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ResBoardReplyMapper {

    @Mapping(source = "boardNo", target = "board.id")
    @Mapping(source = "writerId", target = "writer.id")
    BoardReplyEntity toEntity(ResBoardReplyDto boardReplyDto);

    @Mapping(source = "board.id", target = "boardNo")
    @Mapping(source = "writer.id", target = "writerId")
    @Mapping(source = "writer.nickName", target = "nickName")
    ResBoardReplyDto toDto(BoardReplyEntity boardReplyEntity);

    List<ResBoardReplyDto> toDtoList(List<BoardReplyEntity> boardReplyEntityList);
}
