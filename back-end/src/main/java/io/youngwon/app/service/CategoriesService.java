package io.youngwon.app.service;

import io.youngwon.app.domain.categories.Categories;
import io.youngwon.app.domain.categories.CategoriesRepository;
import io.youngwon.app.web.dto.categories.CategoriesListDto;
import io.youngwon.app.web.dto.categories.CategoriesSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoriesService {

    private final CategoriesRepository categoriesRepository;

    @Transactional
    public Long save(CategoriesSaveRequestDto requestDto){
        return categoriesRepository.save(requestDto.toEntity()).getId();
    }


    @Transactional(readOnly = true)
    public List<CategoriesListDto> findRoots() {
        return categoriesRepository.findRoots()
                .stream()
                .map(CategoriesListDto::new)
                .collect(Collectors.toList());
    }


}
