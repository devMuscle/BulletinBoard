package com.bb.app.Mapper;

import com.bb.app.DTO.VoteBoardDto;
import com.bb.app.entity.VoteBoardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VoteBoardMapper {
    VoteBoardMapper INSTANCE = Mappers.getMapper(VoteBoardMapper.class);

    VoteBoardEntity toEntity(VoteBoardDto voteBoardDto);
    VoteBoardDto toDto(VoteBoardEntity voteBoardEntity);
}
