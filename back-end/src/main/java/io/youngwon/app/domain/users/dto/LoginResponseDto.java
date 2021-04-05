package io.youngwon.app.domain.users.dto;

import io.youngwon.app.domain.users.domain.User;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class LoginResponseDto {


    private final String token;

    private final UserResponseDto user;

    public LoginResponseDto(String token, User user) {
        this.token = token;
        this.user = new UserResponseDto(user);
    }

    public String getToken() {
        return token;
    }

    public UserResponseDto getUser() {
        return user;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("token", token)
                .append("user", user)
                .toString();
    }

}
