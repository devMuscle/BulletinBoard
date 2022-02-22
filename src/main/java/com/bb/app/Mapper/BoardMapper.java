package com.bb.app.Mapper;

import com.bb.app.DTO.BoardDto;
import com.bb.app.entity.BoardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BoardMapper {
    BoardMapper INSTANCE = Mappers.getMapper(BoardMapper.class);

    @Mapping(source = "member", target = "member.id")
    BoardEntity toEntity(BoardDto boardDto);
    @Mapping(source = "member.id", target = "member")
    BoardDto toDto(BoardEntity boardEntity);

    List<BoardEntity> toEntityList(List<BoardDto> DtoList);
    List<BoardDto> toDtoList(List<BoardEntity> EntityList);
}
