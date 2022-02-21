package com.bb.app.Mapper;

import com.bb.app.DTO.MyBoardDto;
import com.bb.app.entity.BoardEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface MyBoardDtoMapper {


    MyBoardDto toMyBoardDto(BoardEntity boardEntity);

    List<MyBoardDto> toMyBoardDtoList(List<BoardEntity> boardEntityLIst);
}
