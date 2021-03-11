package io.youngwon.app.domain.users;

import io.youngwon.app.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Users extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;


    @Builder
    public Users(String name, String email, Role role){
        this.name = name;
        this.email = email;
        this.role = role;
    }


    public Users update(String name){
        this.name = name;
        return this;
    }
}
