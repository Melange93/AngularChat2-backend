package com.reka.lakatos.angularchatbackend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

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
    private Set<AppUser> members;

    @OneToMany
    private List<ChatMessage> chatMessages;

    public ChatRoom(String roomName, AppUser creator) {
        this.chatRoomName = roomName;
        this.creator = creator;
        this.members = new HashSet<>(Collections.singletonList(creator));
        this.chatMessages = new ArrayList<>();
    }

    public ChatRoom() {
    }

    public void addNewMemberToRoom(AppUser newMember) {
        members.add(newMember);
    }
}
