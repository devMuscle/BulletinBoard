package com.bb.app.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class MessageEntity {
    @Id
    @Column(name = "message_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private MemberEntity sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private MemberEntity receiver;

    private String title;
    private String content;
    private LocalDateTime registerDate;

}