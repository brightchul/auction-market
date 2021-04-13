package io.youngwon.app.domain.users.domain;

import io.youngwon.app.domain.BaseTimeEntity;
import io.youngwon.app.security.Jwt;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name="users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String kakao;

    private String naver;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User(Long id) {
        this.id = id;
    }

    @Builder
    public User(String name, String email, Role role, String vender, String socialId) {
        this.name = name;
        this.email = email;
        this.role = role;

        if("kakao".equals(vender)){
            this.kakao = socialId;
        }else if("naver".equals(vender)){
            this.naver = socialId;
        }

    }

    public User update(String name) {
        this.name = name;
        return this;
    }


    public String newJwt(Jwt jwt, String[] roles) {
        Jwt.Claims claims = Jwt.Claims.of(id, name, roles);
        return jwt.create(claims);
    }

}
