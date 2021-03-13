package io.youngwon.app.service;

import io.youngwon.app.domain.likes.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LikesService {

    private final LikesRepository likesRepository;


    public Long save(Long productId, Long userId){

        return 0L;

    }

    public Long delete(){

        return 0L;
    }

}
