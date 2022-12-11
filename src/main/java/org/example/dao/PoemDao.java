package org.example.dao;

import org.example.entities.Poem;

import java.util.List;

public interface PoemDao extends AbstractDao<Poem> {
    List<Poem> getPoemsByAuthorId(Long authorId);
}
