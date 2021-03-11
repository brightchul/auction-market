package io.youngwon.app.service;


import io.youngwon.app.domain.comments.CommentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentsService {

    private CommentsRepository commentsRepository;

}
