package com.reka.lakatos.angularchatbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
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

    public AppUser() {
        this.roles = Collections.singletonList(Roles.USER);
        this.chatMessages = new ArrayList<>();
    }

    public List<String> getRolesInString() {
        return roles.stream()
                .map(Roles::getValue)
                .collect(Collectors.toList());
    }
}
