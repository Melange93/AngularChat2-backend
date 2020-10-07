package com.reka.lakatos.angularchatbackend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue
    private long id;

    @Column(length = 50, unique = true)
    private String userName;

    @Column(unique = true)
    private String email;

    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST)
    private List<ChatMessage> chatMessages = new ArrayList<>();
}
