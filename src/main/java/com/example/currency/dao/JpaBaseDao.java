package com.example.currency.dao;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;

public abstract class JpaBaseDao<T> implements BaseDao<T> {

    protected static final Logger LOGGER = LoggerFactory.getLogger(JpaBaseDao.class);

    @PersistenceContext
    protected EntityManager em;

    private final Class<T> entityType;

    @SuppressWarnings("unchecked")
    public JpaBaseDao() {
        this.entityType = ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0]);
    }

    @Override
    public T find(Object id) {
        return em.find(entityType, id);
    }

    public void detach(T object) {
        em.detach(object);
    }

    @Override
    public T save(T type) {
        LOGGER.debug(String.format("Creating entity %s...", type));

        // return em.persist(type);
        em.persist(type);

        LOGGER.debug(String.format("Created entity %s...", type.toString()));
        return type;
    }

    @Override
    public T update(T type) {
        return em.merge(type);
    }

    @Override
    public void delete(T type) {
        em.remove(em.contains(type) ? type : em.merge(type));
    }

    @Override
    public void deleteById(Object id) {
        T entity = this.find(id);
        if (entity != null) {
            em.remove(entity);
        }
        // em.remove(em.contains(entity) ? entity : em.merge(entity));
    }

    @Override
    public void flush() {
        em.flush();
    }

    @Override
    public T saveOrUpdate(T type) {
        Session session = em.unwrap(Session.class);
        session.saveOrUpdate(type);
        return type;
    }
}