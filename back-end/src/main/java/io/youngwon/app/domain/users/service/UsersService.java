package io.youngwon.app.domain.users.service;

import io.youngwon.app.config.errors.NotFoundException;
import io.youngwon.app.domain.users.domain.User;
import io.youngwon.app.domain.users.domain.UsersRepository;
import io.youngwon.app.domain.users.dto.UserResponseDto;
import io.youngwon.app.domain.users.dto.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UsersService implements UserDetailsService {

    private final UsersRepository usersRepository;



    @Transactional(readOnly = true)
    public UserResponseDto findById(Long id) {
        User user = usersRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Could not found user for " + id));

        return new UserResponseDto(user);
    }


    public User login(){
        User user = usersRepository
                .findById(1L)
                .orElseThrow(() -> new NotFoundException("Could not found user for " ));

        return user;
    }


    @Transactional
    public Long save(UserSaveRequestDto requestDto) {
        return usersRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public User update() {
        return null;
    }

    @Transactional
    public User delete() {
        return null;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = usersRepository.findByEmail(s).orElseThrow(() -> new NotFoundException("Could not found user for email " + s));
//        List<User> users = usersRepository.findAll();
//        User user = users.get(0);

        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                "1",
                AuthorityUtils.createAuthorityList("USER")
        );

    }
}
