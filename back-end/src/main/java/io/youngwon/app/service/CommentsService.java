package io.youngwon.app.service;


import io.youngwon.app.domain.comments.CommentsRepository;
import io.youngwon.app.web.dto.categories.CategoriesListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentsService {

    private CommentsRepository commentsRepository;




}
