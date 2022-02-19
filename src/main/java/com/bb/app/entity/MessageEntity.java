package com.bb.app.entity;

import com.bb.app.constant.DeleteStatus;
import com.bb.app.constant.ReadStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private MemberEntity sender;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "receiver_id")
    private MemberEntity receiver;

    private String title;
    private String content;
    private LocalDateTime registerDate;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("UNREAD")
    private ReadStatus readStatus;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("UNDELETE")
    private DeleteStatus receiveDeleteStatus;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("UNDELETE")
    private DeleteStatus sendDeleteStatus;

}