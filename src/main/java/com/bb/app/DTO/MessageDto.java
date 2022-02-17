package com.bb.app.DTO;

import com.bb.app.constant.DeleteStatus;
import com.bb.app.constant.ReadStatus;
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
public class MessageDto {
    private Long id;

    private MemberEntity sender;

    private MemberEntity receiver;

    private String title;

    private String content;

    private LocalDateTime registerDate;

    private ReadStatus readStatus;

    private DeleteStatus receiveDeleteStatus;

    private DeleteStatus sendDeleteStatus;
}
