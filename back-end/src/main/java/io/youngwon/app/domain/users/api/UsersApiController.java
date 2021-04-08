package io.youngwon.app.domain.users.api;

import io.youngwon.app.domain.users.service.UsersService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/users")
public class UsersApiController {

    private final UsersService usersService;


}
