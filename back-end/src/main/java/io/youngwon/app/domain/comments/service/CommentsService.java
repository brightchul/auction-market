package io.youngwon.app.domain.comments.service;


import io.youngwon.app.config.errors.NotFoundException;
import io.youngwon.app.domain.comments.domain.Comments;
import io.youngwon.app.domain.comments.dao.CommentsRepository;
import io.youngwon.app.domain.products.domain.Product;
import io.youngwon.app.domain.products.dao.ProductsRepository;
import io.youngwon.app.domain.comments.dto.CommentsListResponseDto;
import io.youngwon.app.domain.comments.dto.CommentsSaveRequestDto;
import io.youngwon.app.domain.comments.dto.CommentsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentsService {

    private final ProductsRepository productsRepository;
    private final CommentsRepository commentsRepository;

    @Transactional(readOnly = true)
    public List<CommentsListResponseDto> findByProduct(Long id){
        Product products =  productsRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Could not found product for " + id));

        return products.getComments().stream().map(CommentsListResponseDto::new).collect(Collectors.toList());
    }



    @Transactional
    public Long save(Long productId, CommentsSaveRequestDto requestDto){
        Product products =  productsRepository
                .findById(productId)
                .orElseThrow(() -> new NotFoundException("Could not found product for " + productId));

        products.addComments(requestDto.toEntity(products));
        return 0L;
    }

    @Transactional
    public Long update(Long productId, Long commentsid, CommentsUpdateRequestDto requestDto){
        Product products =  productsRepository
                .findById(productId)
                .orElseThrow(() -> new NotFoundException("Could not found product for " + productId));


        Comments comments = commentsRepository.findById(commentsid)
                .orElseThrow(() -> new NotFoundException("Could not found comment for " + commentsid));

//        products.updateComments(requestDto.toEntity(products));


        comments.update(requestDto.getContent());
        return 0L;
    }


    @Transactional
    public Long delete(Long productId, Long commentsid){
        Comments comments = commentsRepository.findById(commentsid)
                .orElseThrow(() -> new NotFoundException("Could not found comment for " + commentsid));

        commentsRepository.deleteById(commentsid);
        return 0L;
    }


}
