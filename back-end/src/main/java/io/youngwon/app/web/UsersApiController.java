package io.youngwon.app.web;

import io.youngwon.app.service.UsersService;

import io.youngwon.app.web.dto.users.UserResponseDto;
import io.youngwon.app.web.dto.users.UserSaveRequestDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import static io.youngwon.app.utils.ApiUtils.ApiResult;
import static io.youngwon.app.utils.ApiUtils.success;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/users")
public class UsersApiController {

    private final UsersService usersService;


    @GetMapping(path = "me")
    public ApiResult<UserResponseDto> me() {

        return null;
    }




    @PostMapping
    public ApiResult<Long> update(@RequestBody UserSaveRequestDto requestDto){

        return success(usersService.save(requestDto));
    }

    @PutMapping(path = "me")
    public ApiResult<UserResponseDto> update(){


        return null;
    }


    @DeleteMapping(path = "me")
    public ApiResult<Boolean> delete(){

        return null;
    }

}
