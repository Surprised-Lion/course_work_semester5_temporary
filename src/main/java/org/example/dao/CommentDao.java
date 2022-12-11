package org.example.dao;

import org.example.entities.Comment;

import java.util.List;

public interface CommentDao extends AbstractDao<Comment> {
    List<Comment> getAllCommetsFromPoem(Long poemId);
}
