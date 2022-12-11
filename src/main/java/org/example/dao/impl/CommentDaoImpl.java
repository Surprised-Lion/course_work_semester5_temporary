package org.example.dao.impl;

import org.example.dao.CommentDao;
import org.example.entities.Comment;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class CommentDaoImpl extends AbstractDaoImpl<Comment> implements CommentDao {
    @Override
    public List<Comment> getAllCommetsFromPoem(Long poemId) {
        Class entityClass = Comment.class;
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Comment> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<Comment> rootEntry = criteriaQuery.from(entityClass);

        CriteriaQuery<Comment> crit = criteriaQuery.select(rootEntry)
                .where(criteriaBuilder.equal(
                                rootEntry.get("poem").get("id"),
                                poemId
                        )
                );
        TypedQuery<Comment> found = entityManager.createQuery(crit);
        return found.getResultList();

    }
}
