package io.youngwon.app.config.auth.dto;

import io.youngwon.app.domain.users.Users;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;

    public SessionUser(Users user){
        this.name = user.getName();
        this.email = user.getEmail();
    }

}
