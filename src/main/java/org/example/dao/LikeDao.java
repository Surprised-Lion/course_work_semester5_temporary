package org.example.dao;

import org.example.entities.Author;
import org.example.entities.Like;
import org.example.entities.Poem;

import java.util.List;

public interface LikeDao extends AbstractDao<Like> {
    List<Author> getLikedAuthorsByUserId(Long userId);

    List<Poem> getLikedPoemsByUserId(Long userId);

    Like getByAuthorAndUserId(Long authorId, Long id);

    Like getByPoemAndUserId(Long poemId, Long id);
}
