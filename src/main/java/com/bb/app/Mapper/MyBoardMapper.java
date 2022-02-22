package com.bb.app.Mapper;

import com.bb.app.DTO.MyBoardDto;
import com.bb.app.entity.BoardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MyBoardMapper {

    @Mapping(source = "member.nickName", target = "writer")
    MyBoardDto toMyBoardDto(BoardEntity boardEntity);

    List<MyBoardDto> toMyBoardDtoList(List<BoardEntity> boardEntityLIst);
}
