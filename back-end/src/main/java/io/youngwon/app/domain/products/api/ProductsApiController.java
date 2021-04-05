package io.youngwon.app.domain.products.api;


import io.youngwon.app.Constant;
import io.youngwon.app.config.auth.LoginUser;
import io.youngwon.app.domain.products.dto.*;
import io.youngwon.app.domain.users.domain.User;
import io.youngwon.app.domain.products.service.ProductsService;
import io.youngwon.app.utils.paging.PageRequest;
import lombok.RequiredArgsConstructor;
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

    @GetMapping
    public ApiResult<List<ProductsListResponseDto>> findAll(
            @RequestParam(name = "type") final ProductsSearchType type,
            @RequestParam(name = "value", required = false) final String value,
            final PageRequest pageable) {
        return success(productsService.findAll(pageable.of(), Constant.USER_ID));
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
            @LoginUser User user) {
        Long id = productsService.save(1L, requestDto);
        return success(productsService.findById(id, user.getId()));
    }

    @PutMapping("{id}")
    public ApiResult<ProductsResponseDto> update(
            @PathVariable Long id,
            @RequestBody ProductsUpdateRequestDto requestDto,
            @LoginUser User user) {
        productsService.update(id, requestDto);
        return success(productsService.findById(id, user.getId()));
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
