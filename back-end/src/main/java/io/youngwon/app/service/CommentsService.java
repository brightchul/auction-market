package io.youngwon.app.service;


import io.youngwon.app.config.errors.NotFoundException;
import io.youngwon.app.domain.comments.Comments;
import io.youngwon.app.domain.comments.CommentsRepository;
import io.youngwon.app.domain.products.Products;
import io.youngwon.app.domain.products.ProductsRepository;
import io.youngwon.app.web.dto.comments.CommentsSaveRequestDto;
import io.youngwon.app.web.dto.comments.CommentsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentsService {

    private final ProductsRepository productsRepository;
    private final CommentsRepository commentsRepository;


    @Transactional
    public void save(Long productId, CommentsSaveRequestDto requestDto){
        Products products =  productsRepository
                .findById(productId)
                .orElseThrow(() -> new NotFoundException("Could not found product for " + productId));

        products.addComments(requestDto.toEntity(products));

    }

    @Transactional
    public void update(Long productId, Long commentsid, CommentsUpdateRequestDto requestDto){

        Comments comments = commentsRepository.findById(commentsid)
                .orElseThrow(() -> new NotFoundException("Could not found comment for " + commentsid));

        comments.update(requestDto.getContent());
    }


    @Transactional
    public void delete(Long productId, Long commentsid){
        Comments comments = commentsRepository.findById(commentsid)
                .orElseThrow(() -> new NotFoundException("Could not found comment for " + commentsid));

        commentsRepository.deleteById(commentsid);
    }


}
