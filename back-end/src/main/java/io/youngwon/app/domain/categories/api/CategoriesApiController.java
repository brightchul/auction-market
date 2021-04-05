package io.youngwon.app.domain.categories.api;


import io.youngwon.app.domain.categories.service.CategoriesService;
import io.youngwon.app.domain.categories.dto.CategoriesListResponseDto;
import io.youngwon.app.domain.categories.dto.CategoriesSaveRequestDto;
import io.youngwon.app.domain.categories.dto.CategoriesUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static io.youngwon.app.utils.ApiUtils.ApiResult;
import static io.youngwon.app.utils.ApiUtils.success;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/categories")
public class CategoriesApiController {

    private final CategoriesService categoriesService;

    @GetMapping
    public ApiResult<List<CategoriesListResponseDto>> findRoots() {
        return success(categoriesService.findRoots());
    }

    @PostMapping
    public ApiResult<CategoriesListResponseDto> post(@Valid @RequestBody CategoriesSaveRequestDto requestDto) {
        categoriesService.save(requestDto);
        return null;
    }

    @PutMapping(path = "{id}")
    public ApiResult<CategoriesListResponseDto> put(@PathVariable Long id, @Valid @RequestBody CategoriesUpdateRequestDto requestDto) {
        categoriesService.update(id, requestDto);
        return null;
    }

    @DeleteMapping(path = "{id}")
    public ApiResult<Boolean> delete(@PathVariable Long id) {
        return null;
    }


}
