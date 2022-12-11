package org.example.service;

import org.example.entities.Author;
import org.example.entities.Like;
import org.example.entities.Poem;
import org.example.entities.User;

import java.util.List;

public interface AuthorService extends AbstractService<Author> {
    List<Poem> getPoemsByAuthorId(Long authorId);

    Like likeAuthor(Long authorId, User user);

    void deleteAuthorLike(Long authorId, User user);
}
