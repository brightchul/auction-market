package io.youngwon.app.domain.likes.api;


import io.youngwon.app.domain.likes.service.LikesService;
import io.youngwon.app.security.JwtAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static io.youngwon.app.utils.ApiUtils.ApiResult;
import static io.youngwon.app.utils.ApiUtils.success;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/products")
public class LikesApiController {

    private final LikesService likesService;

    @PatchMapping("{id}/like")
    public ApiResult<Integer> like(@PathVariable Long id,
                                   @AuthenticationPrincipal JwtAuthentication authentication) {
        return success(likesService.like(id, authentication.id));
    }

    @PatchMapping("{id}/unlike")
    public ApiResult<Integer> unlike(@PathVariable Long id,
                                     @AuthenticationPrincipal JwtAuthentication authentication) {
        return success(likesService.unlike(id, authentication.id));
    }
}
