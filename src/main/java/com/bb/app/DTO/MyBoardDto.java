package com.bb.app.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MyBoardDto {
    private Long id;
    private String title;
    private Long writer;
    private int view;
}
