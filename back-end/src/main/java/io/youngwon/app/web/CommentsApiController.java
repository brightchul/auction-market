package io.youngwon.app.web;

import io.youngwon.app.service.CommentsService;
import io.youngwon.app.web.dto.comments.CommentsListDto;
import io.youngwon.app.web.dto.comments.CommentsSaveRequestDto;
import io.youngwon.app.web.dto.comments.CommentsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static io.youngwon.app.utils.ApiUtils.ApiResult;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/products")
public class CommentsApiController {

    private final CommentsService commentsService;

    @GetMapping("{id}/comments")
    public ApiResult<List<CommentsListDto>> findAll(){
        return null;
    }

    @PostMapping("{id}/comments")
    public ApiResult save(@PathVariable Long id, CommentsSaveRequestDto requestDto){
        return null;
    }

    @PutMapping("{id}/comments/{cid}")
    public ApiResult update(@PathVariable Long id, @PathVariable Long cid, CommentsUpdateRequestDto requestDto){
        return null;
    }

    @DeleteMapping("{id}/comments/{cid}")
    public ApiResult delete(@PathVariable Long id, @PathVariable Long cid){
        return null;
    }

}
