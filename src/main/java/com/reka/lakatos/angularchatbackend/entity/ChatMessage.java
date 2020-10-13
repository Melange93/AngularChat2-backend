package com.reka.lakatos.angularchatbackend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser user;

    private String message;

    private Date timeStamp;

    @ManyToOne(fetch = FetchType.LAZY)
    private ChatRoom chatRoom;
}
