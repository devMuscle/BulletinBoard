package com.bb.app.Mapper;

import com.bb.app.DTO.BoardDetailDto;
import com.bb.app.DTO.BoardDto;
import com.bb.app.DTO.MyBoardDto;
import com.bb.app.entity.BoardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BoardDetailMapper {

    @Mapping(source = "memberId", target = "member.id")
    @Mapping(source = "writer", target = "member.nickName")
    BoardEntity toEntity(BoardDetailDto boardDetailDto);

    @Mapping(source = "member.id", target = "memberId")
    @Mapping(source = "member.nickName", target = "writer")
    BoardDetailDto toDto(BoardEntity boardEntity);

}
