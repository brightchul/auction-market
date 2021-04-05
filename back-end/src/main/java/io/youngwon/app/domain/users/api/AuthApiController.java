package io.youngwon.app.domain.users.api;

import io.youngwon.app.domain.users.domain.User;
import io.youngwon.app.domain.users.domain.UsersRepository;
import io.youngwon.app.domain.users.dto.LoginRequestDto;
import io.youngwon.app.domain.users.dto.LoginResponseDto;
import io.youngwon.app.domain.users.webclient.KakaoWebClient;
import io.youngwon.app.security.Jwt;
import io.youngwon.app.security.JwtAuthenticationToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static io.youngwon.app.utils.ApiUtils.ApiResult;
import static io.youngwon.app.utils.ApiUtils.success;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AuthApiController {

    private final Jwt jwt;

    private final UsersRepository usersRepository;

    private final AuthenticationManager authenticationManager;

    @PostMapping("/api/auth/login")
    public ApiResult<LoginResponseDto> loginWithKakao(@RequestBody LoginRequestDto requestDto) {
        // 엑세스 토큰을 받고 JWT를 반환

        log.info("login start");
        String accessToken = new KakaoWebClient.GetAccessToken(requestDto.getCode()).get();
        log.info("accessToken : " + accessToken);

        // 벤더에서 조회한 로그인 정보
        User login = new KakaoWebClient.GetUserInfo(accessToken).get();

        // vendor로 구분
        User user = usersRepository.findByEmail(login.getEmail()).orElse(null);
        if (user == null) {
            user = usersRepository.save(login);
        }


        Authentication authentication = authenticationManager.authenticate(
            new JwtAuthenticationToken(user.getEmail())
        );


        final String token = user.newJwt(
            jwt,
            authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .toArray(String[]::new)
        );

        log.info("login end");
        return success(new LoginResponseDto(token, user));
    }


}
