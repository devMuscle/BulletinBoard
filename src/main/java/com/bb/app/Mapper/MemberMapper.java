package com.bb.app.Mapper;

import com.bb.app.DTO.MemberDto;
import com.bb.app.entity.MemberEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    MemberEntity toEntity(MemberDto MemberDto);
    MemberDto toDto(MemberEntity MemberEntity);
}
