package io.youngwon.app.domain.comments;

import io.youngwon.app.domain.BaseTimeEntity;
import io.youngwon.app.domain.users.Users;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Comments extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    private Users writer;


}
