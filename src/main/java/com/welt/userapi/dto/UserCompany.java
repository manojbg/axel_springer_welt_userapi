package com.welt.userapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCompany {
    private String name;
    private String catchPhrase;
    private String bs;

    public UserCompany(){}

    public UserCompany(String name, String catchPhrase, String bs) {
        this.name = name;
        this.catchPhrase = catchPhrase;
        this.bs = bs;
    }
}
