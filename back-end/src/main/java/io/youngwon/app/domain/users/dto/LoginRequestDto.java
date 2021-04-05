package io.youngwon.app.domain.users.dto;

import lombok.Getter;

@Getter
public class LoginRequestDto {

    private String vendor;
    private String code;
}
