package com.bb.app.DTO;

import com.bb.app.constant.AgreeStatus;
import com.bb.app.entity.MemberEntity;
import com.bb.app.entity.VoteBoardEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VoteReplyDto {
    private Long id;
    private Long parentReplyId = 0L;
    private String nickName;
    private Long voteBoardId;               //조인
    private Long memberId;                  //조인
    private LocalDateTime registerDate;
    private String comment;
    private AgreeStatus agreeStatus;
}
