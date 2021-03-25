package io.youngwon.app.web.dto.users;

import io.youngwon.app.domain.users.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserSaveRequestDto {

    private String name;
    private String email;


    public Users toEntity() {
        return Users.builder().name(this.name).email(this.email).build();
    }

}
