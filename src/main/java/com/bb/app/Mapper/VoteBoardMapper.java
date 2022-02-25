package com.bb.app.Mapper;

import com.bb.app.DTO.BoardDto;
import com.bb.app.DTO.VoteBoardDto;
import com.bb.app.entity.BoardEntity;
import com.bb.app.entity.VoteBoardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VoteBoardMapper {
    VoteBoardMapper INSTANCE = Mappers.getMapper(VoteBoardMapper.class);

    @Mapping(source = "member", target = "member.id")
    VoteBoardEntity toEntity(VoteBoardDto voteBoardDto);
    @Mapping(source = "member.id", target = "member")
    VoteBoardDto toDto(VoteBoardEntity voteBoardEntity);

    List<VoteBoardEntity> toEntityList(List<VoteBoardDto> DtoList);
    List<VoteBoardDto> toDtoList(List<VoteBoardEntity> EntityList);
}
