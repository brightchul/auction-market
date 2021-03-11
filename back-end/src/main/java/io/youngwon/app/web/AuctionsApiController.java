package io.youngwon.app.web;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static io.youngwon.app.utils.ApiUtils.ApiResult;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/products")
public class AuctionsApiController {

    @GetMapping("{id}/auctions")
    public ApiResult findAll(){
        return null;
    }

    @GetMapping("{id}/auctions/{aid}")
    public ApiResult findById(){
        return null;
    }

    @PostMapping("{id}/auctions")
    public ApiResult bid(){
        return null;
    }

    @DeleteMapping("{id}/auctions")
    public ApiResult delete(){
        return null;
    }


}
