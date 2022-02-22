package com.bb.app.Mapper;

import com.bb.app.DTO.VoteBoardDto;
import com.bb.app.DTO.VoteReplyDto;
import com.bb.app.entity.VoteBoardEntity;
import com.bb.app.entity.VoteReplyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VoteReplyMapper {
    VoteReplyMapper INSTANCE = Mappers.getMapper(VoteReplyMapper.class);

    @Mapping(source = "voteBoardId", target = "voteBoard.id")
    @Mapping(source = "memberId", target = "writer.id")
    VoteReplyEntity toEntity(VoteReplyDto voteReplyDto);

    VoteReplyDto toDto(VoteReplyEntity voteReplyEntity);

    List<VoteReplyEntity> toEntity(List<VoteReplyDto> voteReplyDto);
    List<VoteReplyDto> toDto(List<VoteReplyEntity> voteReplyEntity);
}
