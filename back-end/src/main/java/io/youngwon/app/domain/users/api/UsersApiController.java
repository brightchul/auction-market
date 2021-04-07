package io.youngwon.app.domain.users.api;

import io.youngwon.app.config.auth.LoginUser;
import io.youngwon.app.config.auth.dto.SessionUser;
import io.youngwon.app.domain.users.service.UsersService;

import io.youngwon.app.domain.users.dto.UserResponseDto;
import io.youngwon.app.domain.users.dto.UserSaveRequestDto;
import io.youngwon.app.security.JwtAuthentication;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


import static io.youngwon.app.utils.ApiUtils.ApiResult;
import static io.youngwon.app.utils.ApiUtils.success;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/users")
public class UsersApiController {

    private final UsersService usersService;


}
