package com.bb.app.DTO;

import com.bb.app.entity.BoardEntity;
import com.bb.app.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardReplyDto {
    private Long id;

    private Long parentReplyId = 0L;
    private BoardEntity board;
    private MemberEntity writer;
    private LocalDateTime registerDate;
    private String comment;
}
