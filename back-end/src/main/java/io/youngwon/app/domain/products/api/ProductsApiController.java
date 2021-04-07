package io.youngwon.app.domain.products.api;


import io.youngwon.app.Constant;
import io.youngwon.app.config.auth.LoginUser;
import io.youngwon.app.domain.products.dto.*;
import io.youngwon.app.domain.products.service.ProductsSearchService;
import io.youngwon.app.domain.users.domain.User;
import io.youngwon.app.domain.products.service.ProductsService;
import io.youngwon.app.security.JwtAuthentication;
import io.youngwon.app.utils.paging.PageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

import static io.youngwon.app.utils.ApiUtils.ApiResult;
import static io.youngwon.app.utils.ApiUtils.success;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/products")
public class ProductsApiController {

    private final ProductsService productsService;

    private final ProductsSearchService productsSearchService;

    @GetMapping
    public ApiResult<Page<ProductsListResponseDto>> findAll(
            @RequestParam(name="type", defaultValue = "ALL", required = false) ProductsStateType type,
            @RequestParam(name="own", required= false) Boolean own,
            @RequestParam(name="title", required = false) String title,
            @RequestParam(name="content", required = false) String content,
            final PageRequest pageable,
            @AuthenticationPrincipal JwtAuthentication authentication) {

        return success(productsSearchService.findAll(
                null,
                title,
                content,
                type,
                own,
                authentication.id,
                pageable.of()));
    }



    // 페이징 지원 필요
    @GetMapping("categories/{id}")
    public ApiResult<List<ProductsListResponseDto>> findByCategories(@PathVariable Long id, @LoginUser User user) {
        return success(productsService.findByCategories(id, user.getId()));
    }


    @GetMapping("{id}")
    public ApiResult<ProductsResponseDto> findById(
            @PathVariable Long id,
            @LoginUser User user) {
        return success(productsService.findById(id, user.getId()));
    }


    /**
     * 상품 등록
     * @param requestDto
     * @return
     */
    @PostMapping
    public ApiResult<ProductsResponseDto> save(
            @Valid @RequestBody ProductsSaveRequestDto requestDto,
            @AuthenticationPrincipal JwtAuthentication authentication) {

        requestDto.setSeller(authentication.id);
        Long id = productsService.save(requestDto);

        return success(productsService.findById(id, authentication.id));
    }


    @PostMapping("dummy")
    public ApiResult<Boolean> saveDummy(
            @AuthenticationPrincipal JwtAuthentication authentication) {

        //판매완료
        for(int i=0;i<20;i++){
            ProductsSaveRequestDto requestDto = ProductsSaveRequestDto.builder()
                    .categories(1L)
                    .title("완료"  +i)
                    .content("상품입니다.  " + i)
                    .startPrice(1000L)
                    .startDateTime("2021-03-01 09:01 PM")
                    .endDateTime("2021-03-10 09:01 PM")
                    .seller(authentication.id)
                    .build();
            productsService.save(requestDto);
        }


        // 판매중
        for(int i=0;i<20;i++){
            ProductsSaveRequestDto requestDto = ProductsSaveRequestDto.builder()
                    .categories(1L)
                    .title("판매중"  +i)
                    .content("상품입니다.  " + i)
                    .startPrice(1000L)
                    .startDateTime("2021-04-01 09:01 PM")
                    .endDateTime("2021-05-01 09:01 PM")
                    .seller(authentication.id)

                    .build();
            productsService.save(requestDto);
        }


        // 대기중
        for(int i=0;i<20;i++){
            ProductsSaveRequestDto requestDto = ProductsSaveRequestDto.builder()
                    .categories(1L)
                    .title("대기중"  +i)
                    .content("상품입니다.  " + i)
                    .startPrice(1000L)
                    .startDateTime("2021-04-26 09:01 PM")
                    .endDateTime("2021-05-01 09:01 PM")
                    .seller(authentication.id)

                    .build();
            productsService.save(requestDto);
        }


        //판매완료
        for(int i=0;i<20;i++){
            ProductsSaveRequestDto requestDto = ProductsSaveRequestDto.builder()
                    .categories(1L)
                    .title("완료"  +i)
                    .content("상품입니다.  " + i)
                    .startPrice(1000L)
                    .startDateTime("2021-03-01 09:01 PM")
                    .endDateTime("2021-03-10 09:01 PM")
                    .seller(3l)
                    .build();
            productsService.save(requestDto);
        }


        // 판매중
        for(int i=0;i<20;i++){
            ProductsSaveRequestDto requestDto = ProductsSaveRequestDto.builder()
                    .categories(1L)
                    .title("판매중"  +i)
                    .content("상품입니다.  " + i)
                    .startPrice(1000L)
                    .startDateTime("2021-04-01 09:01 PM")
                    .endDateTime("2021-05-01 09:01 PM")
                    .seller(3l)
                    .build();
            productsService.save(requestDto);
        }


        // 대기중
        for(int i=0;i<20;i++){
            ProductsSaveRequestDto requestDto = ProductsSaveRequestDto.builder()
                    .categories(1L)
                    .title("대기중"  +i)
                    .content("상품입니다.  " + i)
                    .startPrice(1000L)
                    .startDateTime("2021-04-26 09:01 PM")
                    .endDateTime("2021-05-01 09:01 PM")
                    .seller(3l)

                    .build();
            productsService.save(requestDto);
        }

        return success(true);
    }







    @PutMapping("{id}")
    public ApiResult<ProductsResponseDto> update(
            @PathVariable Long id,
            @RequestBody ProductsUpdateRequestDto requestDto,
            @AuthenticationPrincipal JwtAuthentication authentication) {

        productsService.update(id, requestDto);
        return success(productsService.findById(id, authentication.id));
    }

    @DeleteMapping("{id}")
    public ApiResult<Boolean> delete(@PathVariable Long id) {
        productsService.delete(id);
        return success(true);
    }

    @PatchMapping("{id}/onsale")
    public ApiResult<Boolean> onsale() {
        return null;
    }

    @PatchMapping("{id}/soldout")
    public ApiResult<Boolean> soldout() {
        return null;
    }

}
