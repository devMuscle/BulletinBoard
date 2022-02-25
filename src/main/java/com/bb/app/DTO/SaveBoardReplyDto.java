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
public class SaveBoardReplyDto {
    private Long id;
    @Builder.Default
    private Long parentReplyId = 0L;
    private Long boardNo;
    private Long writer;
    private LocalDateTime registerDate;
    private String comment;
}
