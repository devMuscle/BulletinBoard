package com.bb.app.Mapper;

import com.bb.app.DTO.MyBoardDto;
import com.bb.app.entity.BoardEntity;
import com.bb.app.entity.VoteBoardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MyBoardMapper {

    @Mapping(source = "member.nickName", target = "writer")
    MyBoardDto boardToMyBoardDto(BoardEntity boardEntity);

    @Mapping(source = "member.nickName", target = "writer")
    MyBoardDto voteBoardToMyBoardDto(VoteBoardEntity voteBoardEntity);

    List<MyBoardDto> boardListToMyBoardDtoList(List<BoardEntity> boardEntityLIst);

    List<MyBoardDto> voteListToMyBoardDtoList(List<VoteBoardEntity> voteBoardEntityList);
}
