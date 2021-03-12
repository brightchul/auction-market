package io.youngwon.app.config.auth;

import io.youngwon.app.config.auth.dto.OAuthAttributes;
import io.youngwon.app.config.auth.dto.SessionUser;
import io.youngwon.app.domain.users.Users;
import io.youngwon.app.domain.users.UsersRepository;
import lombok.RequiredArgsConstructor;

import io.youngwon.app.domain.users.Role;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UsersRepository usersRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {

        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(oAuth2UserRequest);

        // 서비스 구분
        String registrationId = oAuth2UserRequest.getClientRegistration().getRegistrationId();

        // 로그인 소비스에서 제공하는 키값
        String userNameAttributeName = oAuth2UserRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        Users user = saveOrUpdate(attributes);
//        httpSession.setAttribute("user", new SessionUser(user));;

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(Role.USER.getKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }


    private Users saveOrUpdate(OAuthAttributes attributes) {
        Users user = usersRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName()))
                .orElse(attributes.toEntity());

        return usersRepository.save(user);
    }

}
