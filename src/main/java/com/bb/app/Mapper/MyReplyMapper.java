package com.bb.app.Mapper;

import com.bb.app.DTO.MyReplyDto;
import com.bb.app.DTO.ResBoardReplyDto;
import com.bb.app.entity.BoardReplyEntity;
import com.bb.app.entity.VoteReplyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MyReplyMapper {

    @Mapping(source = "nickName", target = "writer.nickName")
    BoardReplyEntity toEntity(MyReplyDto myReplyDto);

    @Mapping(source = "writer.nickName", target = "nickName")
    MyReplyDto boardReplyToDto(BoardReplyEntity boardReplyEntity);

    @Mapping(source = "writer.nickName", target = "nickName")
    MyReplyDto voteReplyToDto(VoteReplyEntity voteReplyEntity);

    List<MyReplyDto> boardReplyToDtoList(List<BoardReplyEntity> boardReplyEntityList);

    List<MyReplyDto> voteReplyToDtoList(List<VoteReplyEntity> voteReplyEntity);
}
