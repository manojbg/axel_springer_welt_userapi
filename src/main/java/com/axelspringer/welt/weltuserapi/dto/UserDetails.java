package com.axelspringer.welt.weltuserapi.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDetails {

    private Long id;
    private String name;
    private String email;
    // Add other user details as needed
    private UserPosts[] posts;

    public UserDetails() {
    }

    public UserDetails(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public UserDetails withPosts(UserPosts[] posts) {
        this.posts = posts;
        return this;
    }

}
