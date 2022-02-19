package com.bb.app.Mapper;

import com.bb.app.DTO.VoteAttachDto;
import com.bb.app.entity.VoteAttachEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VoteAttachMapper {
    VoteAttachMapper INSTANCE = Mappers.getMapper(VoteAttachMapper.class);

    VoteAttachEntity toEntity(VoteAttachDto voteAttachDto);
    VoteAttachDto toDto(VoteAttachEntity voteAttachEntity);
}
