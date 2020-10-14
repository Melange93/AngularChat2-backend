package com.reka.lakatos.angularchatbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(length = 50, unique = true)
    private String userName;

    @Column(unique = true)
    private String email;

    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<Roles> roles;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST)
    private List<ChatMessage> chatMessages;

    @JsonIgnore
    @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST)
    private List<ChatRoom> createdRooms;

    @JsonIgnore
    @ManyToMany(mappedBy = "members", fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST)
    private List<ChatRoom> member;

    public AppUser() {
        this.roles = Collections.singletonList(Roles.USER);
        this.chatMessages = new ArrayList<>();
        this.createdRooms = new ArrayList<>();
        this.member = new ArrayList<>();
    }

    public List<String> getRolesInString() {
        return roles.stream()
                .map(Roles::getValue)
                .collect(Collectors.toList());
    }
}
