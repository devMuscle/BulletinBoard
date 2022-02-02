package com.bb.app.entity;

import javax.persistence.*;

@Entity
public class ReceiveMessageEntity {
    @Id
    @Column(name = "message_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private MemberEntity receiver;

    private String senderName;
}
