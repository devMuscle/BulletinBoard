package com.bb.app.DTO;

import com.bb.app.entity.MemberEntity;
import com.bb.app.entity.VoteAttachEntity;
import com.bb.app.entity.VoteReplyEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VoteBoardDto {
    private Long id;

    private int agreeCount = 0;
    private int disagreeCount = 0;

    private String title;
    private String content;
    private LocalDateTime registerDate;
    private int viewCount = 0;
    private String writer;
    private Long member;

    private List<VoteAttachEntity> attach;

    private List<VoteReplyEntity> replyList;
}
