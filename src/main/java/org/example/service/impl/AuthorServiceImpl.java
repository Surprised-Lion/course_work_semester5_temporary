package org.example.service.impl;

import org.example.dao.AuthorDao;
import org.example.dao.LikeDao;
import org.example.dao.PoemDao;
import org.example.entities.Author;
import org.example.entities.Like;
import org.example.entities.Poem;
import org.example.entities.User;
import org.example.exceptions.AlreadyExistsException;
import org.example.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Component
public class AuthorServiceImpl extends AbstractServiceImpl<Author, AuthorDao> implements AuthorService {

    @Autowired
    private LikeDao likeDao;

    @Autowired
    private PoemDao poemDao;

    public AuthorServiceImpl(AuthorDao defaultDao) {
        super(defaultDao);
    }

    @Override
    public List<Poem> getPoemsByAuthorId(Long authorId) {
        return poemDao.getPoemsByAuthorId(authorId);
    }

    @Override
    public Like likeAuthor(Long authorId, User user) {
        Author author = defaultDao.getById(authorId);
        Like like = likeDao.getByAuthorAndUserId(authorId, user.getId());
        if (like != null) {
            throw new AlreadyExistsException("You have already liked this author!");
        }
        like = new Like(user, author, null);
        return likeDao.create(like);
    }

    @Override
    public void deleteAuthorLike(Long authorId, User user) {
        Like like = likeDao.getByAuthorAndUserId(authorId, user.getId());
        if (like == null) {
            throw new EntityNotFoundException("You haven't liked this author!");
        }
        likeDao.deleteById(like.getId());
    }
}
