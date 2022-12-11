package org.example.service.impl;

import org.example.dao.AbstractDao;
import org.example.entities.AbstractEntity;
import org.example.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Transactional(rollbackFor = {SQLException.class})
public class AbstractServiceImpl<T extends AbstractEntity, D extends AbstractDao<T>> implements AbstractService<T> {

    @Autowired
    protected D defaultDao;

    public AbstractServiceImpl(D defaultDao) {
        this.defaultDao = defaultDao;
    }

    @Override
    public T getById(Long id) {
        return defaultDao.getById(id);
    }

    @Override
    public List<T> getAll() {
        return defaultDao.getAll();
    }

    @Override
    public void deleteById(Long id) {
        defaultDao.deleteById(id);
    }

    @Override
    public void update(T entity) {
        defaultDao.update(entity);
    }

    @Override
    public T create(T entity) {
        return defaultDao.create(entity);
    }
}
