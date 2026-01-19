package com.teonvioncollins.timestream.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "chat_invite")
public class ChatInvite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fromUser;
    private String toUser;

    @Enumerated(EnumType.STRING)
    private InviteStatus status;

    private LocalDateTime createdAt = LocalDateTime.now();

    public ChatInvite() {}

    public ChatInvite(Long id, String fromUser, String toUser) {
        this.id = id;
        this.fromUser = fromUser;
        this.toUser = toUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public InviteStatus getStatus() {
        return status;
    }

    public void setStatus(InviteStatus status) {
        this.status = status;
    }
}


