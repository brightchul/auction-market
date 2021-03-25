package io.youngwon.app.service;

import io.youngwon.app.domain.users.Users;
import io.youngwon.app.domain.users.UsersRepository;
import io.youngwon.app.web.dto.users.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UsersService {

    private final UsersRepository usersRepository;

    @Transactional
    public Long save(UserSaveRequestDto requestDto){
        return usersRepository.save(requestDto.toEntity()).getId();
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
