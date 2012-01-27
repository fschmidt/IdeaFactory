package de.bht.se2.ideafactory.dao;

import java.util.List;

import javax.persistence.EntityManager;

import de.bht.se2.ideafactory.util.PersistenceManager;

public abstract class AbstractEclipseLinkDao<T> {
    private Class<T> entityClass;
    
    private EntityManager em = null;

    public AbstractEclipseLinkDao(Class<T> entityClass) {
	this.entityClass = entityClass;
    }

    private EntityManager getEntityManager() {
	if (em == null) {
	    em = PersistenceManager.getInstance().createEntityManagerFactory()
		    .createEntityManager();
	}
	return em;
    }

    public void create(T entity) {
	getEntityManager().persist(entity);
    }

    public void update(T entity) {
	getEntityManager().merge(entity);
    }

    public void remove(T entity) {
	getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
	return getEntityManager().find(entityClass, id);
    }

    public List<T> getAll() {
	javax.persistence.criteria.CriteriaQuery<T> criteriaQuery = getEntityManager()
		.getCriteriaBuilder().createQuery(entityClass);
	criteriaQuery.select(criteriaQuery.from(entityClass));
	List<T> result = getEntityManager().createQuery(criteriaQuery).getResultList();
	return result;
    }
    
}
