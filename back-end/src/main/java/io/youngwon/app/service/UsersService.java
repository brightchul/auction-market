package io.youngwon.app.service;

import io.youngwon.app.domain.users.Users;
import io.youngwon.app.domain.users.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UsersService {

    private final UsersRepository usersRepository;

    @Transactional
    public Users save(){
        return null;
    }

    @Transactional
    public Users update(){
        return null;
    }

    @Transactional
    public Users delete(){
        return null;
    }

}
