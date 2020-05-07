package com.lizavetakeura.dao;

import com.lizavetakeura.api.dao.GenericDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


public abstract class AbstractDao<T> implements GenericDao<T> {

    private Class clazz;
    @PersistenceContext
    protected EntityManager entityManager;

    public AbstractDao(Class<T> clazz) {
        this.clazz = clazz;
    }

    public void create(T object) {
        entityManager.persist(object);
    }

    public void delete(T object) {
        entityManager.remove(object);
    }

    public T getById(Integer id) {
        T object = (T) entityManager.find(clazz, id);
        return object;

    }

    public List<T> getAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
        Root<T> rootQuery = criteriaQuery.from(clazz);
        criteriaQuery.select(rootQuery);
        TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
        List<T> allitems = query.getResultList();

        return allitems;
    }

    public void update(T object) {
        entityManager.merge(object);
    }
}
