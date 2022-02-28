package com.bb.app.Mapper;

import com.bb.app.DTO.BoardDetailDto;
import com.bb.app.DTO.VoteBoardDetailDto;
import com.bb.app.entity.BoardEntity;
import com.bb.app.entity.VoteBoardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VoteBoardDetailMapper {

    @Mapping(source = "memberId", target = "member.id")
    @Mapping(source = "writer", target = "member.nickName")
    VoteBoardEntity toEntity(VoteBoardDetailDto voteBoardDetailDto);

    @Mapping(source = "member.id", target = "memberId")
    @Mapping(source = "member.nickName", target = "writer")
    VoteBoardDetailDto toDto(VoteBoardEntity voteBoardEntity);

}
