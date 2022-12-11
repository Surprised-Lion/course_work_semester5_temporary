package org.example.dao.impl;

import org.example.dao.LikeDao;
import org.example.entities.Author;
import org.example.entities.Like;
import org.example.entities.Poem;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Component
public class LikeDaoImpl extends AbstractDaoImpl<Like> implements LikeDao {
    @Override
    public List<Author> getLikedAuthorsByUserId(Long userId) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Author> query = criteriaBuilder.createQuery(Author.class);

        Root<Like> likesTable = query.from(Like.class);
        Join<Like, Author> authorJoin = likesTable.join("author", JoinType.INNER);

        query.select(authorJoin).where(criteriaBuilder.equal(likesTable.get("user").get("id"), userId));
        TypedQuery<Author> found = getEntityManager().createQuery(query);
        return found.getResultList();
    }

    @Override
    public List<Poem> getLikedPoemsByUserId(Long userId) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Poem> query = criteriaBuilder.createQuery(Poem.class);

        Root<Like> likesTable = query.from(Like.class);
        Join<Like, Poem> poemJoin = likesTable.join("poem", JoinType.INNER);

        query.select(poemJoin).where(criteriaBuilder.equal(likesTable.get("user").get("id"), userId));
        TypedQuery<Poem> found = getEntityManager().createQuery(query);
        return found.getResultList();
    }

    @Override
    public Like getByAuthorAndUserId(Long authorId, Long userId) {
        Class entityClass = Like.class;
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Like> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<Like> rootEntry = criteriaQuery.from(entityClass);

        CriteriaQuery<Like> crit = criteriaQuery.select(rootEntry).where(criteriaBuilder.and(criteriaBuilder.equal(rootEntry.get("author").get("id"), authorId), criteriaBuilder.equal(rootEntry.get("user").get("id"), userId)));
        TypedQuery<Like> found = entityManager.createQuery(crit);
        if (found.getResultList().size() == 0) {
            return null;
        }
        return found.getSingleResult();
    }

    @Override
    public Like getByPoemAndUserId(Long poemId, Long id) {
        Class entityClass = Like.class;
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Like> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<Like> rootEntry = criteriaQuery.from(entityClass);

        CriteriaQuery<Like> crit = criteriaQuery.select(rootEntry).where(criteriaBuilder.and(criteriaBuilder.equal(rootEntry.get("poem").get("id"), poemId), criteriaBuilder.equal(rootEntry.get("user").get("id"), id)));
        TypedQuery<Like> found = entityManager.createQuery(crit);
        if (found.getResultList().size() == 0) {
            return null;
        }
        return found.getSingleResult();
    }
}
