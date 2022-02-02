package com.bb.app.entity;

import javax.persistence.*;

@Entity
public class SendMessageEntity {
    @Id
    @Column(name = "message_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private MemberEntity sender;

    private String receiverName;
}
