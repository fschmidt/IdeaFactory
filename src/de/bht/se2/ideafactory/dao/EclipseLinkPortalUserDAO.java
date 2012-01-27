package de.bht.se2.ideafactory.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import de.bht.se2.ideafactory.model.PortalUser;
import de.bht.se2.ideafactory.util.PersistenceManager;

public class EclipseLinkPortalUserDao {

    private static EclipseLinkPortalUserDao instance = null;
    EntityManagerFactory emf;
    EntityManager em;

    private EclipseLinkPortalUserDao() {
	emf = PersistenceManager.getInstance().createEntityManagerFactory();
	em = emf.createEntityManager();
    }

    public static EclipseLinkPortalUserDao createInstance() {
	if (instance != null) {
	    instance.closeConnections();
	}
	instance = new EclipseLinkPortalUserDao();
	return instance;
    }

    public boolean create(PortalUser user) {
	EntityManagerFactory emf = PersistenceManager.getInstance()
		.createEntityManagerFactory();
	EntityManager em = emf.createEntityManager();

	EntityTransaction tx = em.getTransaction();

	tx.begin();

	em.persist(user);
	tx.commit();

	return true;
    }

    public boolean update(PortalUser user) {

	try {
	    EntityManagerFactory emf = PersistenceManager.getInstance()
		    .createEntityManagerFactory();
	    EntityManager em = emf.createEntityManager();

	    EntityTransaction tx = em.getTransaction();
	    tx.begin();

	    em.merge(user);

	    tx.commit();

	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}
	return true;
    }

    public boolean delete(PortalUser user) {
	try {

	    EntityManagerFactory emf = PersistenceManager.getInstance()
		    .createEntityManagerFactory();
	    EntityManager em = emf.createEntityManager();

	    EntityTransaction tx = em.getTransaction();
	    tx.begin();

	    em.remove(user);

	    tx.commit();

	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}
	return true;
    }

    public List<PortalUser> getAll() {
	EntityManagerFactory emf = PersistenceManager.getInstance()
		.createEntityManagerFactory();
	EntityManager em = emf.createEntityManager();
	ArrayList<PortalUser> users = new ArrayList<PortalUser>();

	EntityTransaction tx = em.getTransaction();
	tx.begin();

	List<?> fetchedComments = em.createQuery("select a from PortalUser a")
		.getResultList();

	for (Object fetchedIdea : fetchedComments) {
	    if (fetchedIdea instanceof PortalUser) {
		PortalUser user = (PortalUser) fetchedIdea;
		users.add(user);
	    }
	}

	tx.commit();

	return users;
    }

    private void closeConnections() {
	em.close();
	emf.close();
    }
}