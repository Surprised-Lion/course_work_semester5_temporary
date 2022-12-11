package org.example.dao.impl;

import org.example.dao.PoemDao;
import org.example.entities.Poem;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class PoemDaoImpl extends AbstractDaoImpl<Poem> implements PoemDao {
    @Override
    public List<Poem> getPoemsByAuthorId(Long authorId) {
        Class entityClass = Poem.class;
        EntityManager entityManager = getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Poem> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<Poem> rootEntry = criteriaQuery.from(entityClass);

        CriteriaQuery<Poem> crit = criteriaQuery.select(rootEntry)
                .where(criteriaBuilder.equal(
                                rootEntry.get("author").get("id"),
                                authorId
                        )
                );
        TypedQuery<Poem> found = entityManager.createQuery(crit);
        return found.getResultList();
    }
}
