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
    private Long senderId;
    private Long receiverId;
    private String title;
    private String content;
    private LocalDateTime registerDate;
    private ReadStatus readStatus;
    private DeleteStatus receiveDeleteStatus;
    private DeleteStatus sendDeleteStatus;

//    public void setSenderId(Long senderId){
//        this.senderId = senderId;
//    }
//    public void setReceiverId(Long receiverId){
//        this.receiverId = receiverId;
//    }
}
