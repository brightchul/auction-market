package io.youngwon.app.web;


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


@RequiredArgsConstructor
@RestController
@RequestMapping("api/products")
public class ProductsApiController {

    private final ProductsService productsService;

    @GetMapping
    public ApiResult<List<ProductsListDto>> findAll() {


        return null;
    }

    @GetMapping("{id}")
    public ApiResult<ProductsDto> findById(@PathVariable Long id) {


        return null;
    }

    @PostMapping
    public ApiResult<ProductsDto> save(@Valid @RequestBody ProductsSaveRequestDto requestDto) {
        return null;
    }

    @PutMapping("{id}")
    public ApiResult<ProductsDto> update(@PathVariable Long id, @RequestBody ProductsUpdateRequestDto requestDto) {
        return null;
    }

    @DeleteMapping("{id}")
    public ApiResult<ProductsDto> delete(@PathVariable Long id) {
        return null;
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
