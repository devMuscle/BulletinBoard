package com.bb.app.Mapper;

import com.bb.app.DTO.BoardDto;
import com.bb.app.entity.BoardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BoardMapper {
    BoardMapper INSTANCE = Mappers.getMapper(BoardMapper.class);

    BoardEntity toEntity(BoardDto boardDto);
    BoardDto toDto(BoardEntity boardEntity);
}
