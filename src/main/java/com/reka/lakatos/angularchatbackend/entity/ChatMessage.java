package com.reka.lakatos.angularchatbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
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
