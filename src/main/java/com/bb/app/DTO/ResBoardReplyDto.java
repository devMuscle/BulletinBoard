package com.bb.app.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResBoardReplyDto {
    private Long id;
    private Long parentReplyId;
    private String nickName;
    private Long boardNo;
    private Long writerId;
    private LocalDateTime registerDate;
    private String comment;
}
