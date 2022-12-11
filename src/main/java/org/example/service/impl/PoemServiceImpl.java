package org.example.service.impl;

import org.example.dao.CommentDao;
import org.example.dao.LikeDao;
import org.example.dao.PoemDao;
import org.example.entities.Comment;
import org.example.entities.Like;
import org.example.entities.Poem;
import org.example.entities.User;
import org.example.exceptions.AlreadyExistsException;
import org.example.service.PoemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PoemServiceImpl extends AbstractServiceImpl<Poem, PoemDao> implements PoemService {
    @Autowired
    private CommentDao commentDao;

    @Autowired
    private LikeDao likeDao;

    public PoemServiceImpl(PoemDao defaultDao) {
        super(defaultDao);
    }

    @Override
    public void deleteCommentById(Long commentId) {
        commentDao.deleteById(commentId);
    }

    @Override
    public Comment createComment(Comment comment) {
        return commentDao.create(comment);
    }

    @Override
    public Like likePoem(Long poemId, User user) {
        Poem poem = defaultDao.getById(poemId);
        Like like = likeDao.getByPoemAndUserId(poemId, user.getId());
        if (like != null) {
            throw new AlreadyExistsException("You already liked this poem!");
        }
        like = new Like(user, null, poem);
        return likeDao.create(like);
    }

    @Override
    public void deletePoemLike(Long poemId, User user) {
        Like like = likeDao.getByPoemAndUserId(poemId, user.getId());
        if (like == null) {
            throw new NullPointerException("You haven't liked this post!");
        }
        likeDao.deleteById(like.getId());
    }

    @Override
    public List<Comment> getAllComments(Long poemId) {
        return commentDao.getAllCommetsFromPoem(poemId);
    }
}
