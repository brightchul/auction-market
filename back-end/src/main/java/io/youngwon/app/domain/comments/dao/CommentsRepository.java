package io.youngwon.app.domain.comments.dao;

import io.youngwon.app.domain.comments.domain.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
}
