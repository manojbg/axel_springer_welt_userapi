package com.welt.userapi.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserInformation {

    private Long id;
    private String name;
    private String email;
    private String username;
    private String phone;
    private String website;
    private UserAddress address;
    private UserCompany company;
    // Add other user details as needed
    private UserPosts[] posts;

    public UserInformation() {
    }

    public UserInformation(Long id, String name, String email, String username, String phone, String website, UserAddress address, UserCompany company) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.phone = phone;
        this.website = website;
        this.address = address;
        this.company = company;
    }

    public UserInformation withPosts(UserPosts[] posts) {
        this.posts = posts;
        return this;
    }

}
