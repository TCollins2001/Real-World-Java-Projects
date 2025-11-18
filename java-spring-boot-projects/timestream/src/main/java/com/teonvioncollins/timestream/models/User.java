package com.teonvioncollins.timestream.models;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String first_name;
    @Column(nullable = false, length = 50)
    private String last_name;
    @Column(nullable = false, unique = true, length = 100)
    private String email;
    @Column(nullable = false, length = 100)
    private String password;
    @Transient
    private String confirmPassword;
    @Column(nullable = false, unique = true, length = 50)
    private String username;
    private String cover_pic;
    private String profile_pic;

    public User() {}

    public User(String first_name, String last_name, String email, String password, String username, String cover_pic, String profile_pic) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.username = username;
        this.cover_pic = cover_pic;
        this.profile_pic = profile_pic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

    public String getCoverPic() {
        return cover_pic;
    }

    public void setCoverPic(String cover_pic) {
        this.cover_pic = cover_pic;
    }

    public String getProfilePic() {
        return profile_pic;
    }

    public void setProfilePic(String profile_pic) {
        this.profile_pic = profile_pic;
    }
}
