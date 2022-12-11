package org.example.service;

import org.example.entities.Comment;
import org.example.entities.Like;
import org.example.entities.Poem;
import org.example.entities.User;

import java.util.List;

public interface PoemService extends AbstractService<Poem> {
    void deleteCommentById(Long commentId);

    Comment createComment(Comment comment);

    Like likePoem(Long poemId, User user);

    void deletePoemLike(Long poemId, User user);

    List<Comment> getAllComments(Long poemId);
}
