package com.welt.userapi.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserPosts {
    private Long userId;
    private Long id;
    private String title;
    private String body;
}
