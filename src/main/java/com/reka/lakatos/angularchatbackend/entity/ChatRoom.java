package com.reka.lakatos.angularchatbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Setter
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String chatRoomName;

    @ManyToOne
    private AppUser creator;

    @ManyToMany
    private List<AppUser> members;

    @OneToMany
    private List<ChatMessage> chatMessages;

    public ChatRoom(String roomName, AppUser creator) {
        this.chatRoomName = roomName;
        this.creator = creator;
        this.members = Collections.singletonList(creator);
        this.chatMessages = new ArrayList<>();
    }

    public ChatRoom() {
    }

    public void addNewMemberToRoom(AppUser newMember) {
        members.add(newMember);
    }
}
