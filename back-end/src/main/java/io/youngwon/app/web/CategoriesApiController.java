package io.youngwon.app.web;


import io.youngwon.app.service.CategoriesService;
import io.youngwon.app.web.dto.categories.CategoriesListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static io.youngwon.app.utils.ApiUtils.ApiResult;
import static io.youngwon.app.utils.ApiUtils.success;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/categories")
public class CategoriesApiController {

    private final CategoriesService categoriesService;

    @GetMapping
    public ApiResult<List<CategoriesListDto>> findRoots(){
        return success(categoriesService.findRoots());
    }



}
