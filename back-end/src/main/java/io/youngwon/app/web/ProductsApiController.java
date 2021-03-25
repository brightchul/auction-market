package io.youngwon.app.web;


import io.youngwon.app.service.ProductsService;
import io.youngwon.app.web.dto.products.ProductsResponseDto;
import io.youngwon.app.web.dto.products.ProductsListResponseDto;
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
    public ApiResult<List<ProductsListResponseDto>> findAll() {
        return success(productsService.findAll());
    }

    // 페이징 지원 필요
    @GetMapping("categories/{id}")
    public ApiResult<List<ProductsListResponseDto>> findByCategories(@PathVariable Long id) {
        return success(productsService.findByCategories(id));
    }


    @GetMapping("{id}")
    public ApiResult<ProductsResponseDto> findById(@PathVariable Long id) {
        return success(productsService.findById(id));
    }

    /**
     * 상품 등록
     * @param requestDto
     * @return
     */
    @PostMapping
    public ApiResult<ProductsResponseDto> save(@Valid @RequestBody ProductsSaveRequestDto requestDto) {
        Long id = productsService.save(1L, requestDto);
        return success(productsService.findById(id));
    }

    @PutMapping("{id}")
    public ApiResult<ProductsResponseDto> update(@PathVariable Long id, @RequestBody ProductsUpdateRequestDto requestDto) {
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
