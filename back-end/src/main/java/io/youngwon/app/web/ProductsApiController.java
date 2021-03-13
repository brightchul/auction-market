package io.youngwon.app.web;


import io.youngwon.app.config.auth.LoginUser;
import io.youngwon.app.config.auth.dto.SessionUser;
import io.youngwon.app.service.ProductsService;
import io.youngwon.app.web.dto.products.ProductsDto;
import io.youngwon.app.web.dto.products.ProductsListDto;
import io.youngwon.app.web.dto.products.ProductsSaveRequestDto;
import io.youngwon.app.web.dto.products.ProductsUpdateRequestDto;
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
    public ApiResult<List<ProductsListDto>> findAll() {
        return success(productsService.findAll());
    }

    // 페이징 지원 필요
    @GetMapping("categories/{id}")
    public ApiResult<ProductsDto> findByCategories(@PathVariable Long id) {
        return success(productsService.findById(id));
    }


    @GetMapping("{id}")
    public ApiResult<ProductsDto> findById(@PathVariable Long id) {
        return success(productsService.findById(id));
    }

    @PostMapping
    public ApiResult<ProductsDto> save(@Valid @RequestBody ProductsSaveRequestDto requestDto) {
        Long id = productsService.save(1L, requestDto);
        return success(productsService.findById(id));
    }

    @PutMapping("{id}")
    public ApiResult<ProductsDto> update(@PathVariable Long id, @RequestBody ProductsUpdateRequestDto requestDto) {
        productsService.update(id, requestDto);
        return success(productsService.findById(id));
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
