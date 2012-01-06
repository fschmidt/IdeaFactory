package de.bht.se2.ideafactory.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import de.bht.se2.ideafactory.model.Idea;
import de.bht.se2.ideafactory.util.PersistenceManager;

public class EclipseLinkIdeaDAO {

    private static EclipseLinkIdeaDAO instance = null;
    EntityManagerFactory emf;
    EntityManager em;

    private EclipseLinkIdeaDAO() {
	emf = PersistenceManager.getInstance().createEntityManagerFactory();
	em = emf.createEntityManager();
    }

    public static EclipseLinkIdeaDAO createInstance() {
	if (instance != null) {
	    instance.closeConnections();
	}
	instance = new EclipseLinkIdeaDAO();
	return instance;
    }

    public boolean create(Idea idea) {
	EntityManagerFactory emf = PersistenceManager.getInstance()
		.createEntityManagerFactory();
	EntityManager em = emf.createEntityManager();

	EntityTransaction tx = em.getTransaction();

	tx.begin();

	em.persist(idea);
	tx.commit();

	return true;
    }

    public boolean update(Idea idea) {

	try {
	    EntityManagerFactory emf = PersistenceManager.getInstance()
		    .createEntityManagerFactory();
	    EntityManager em = emf.createEntityManager();

	    EntityTransaction tx = em.getTransaction();
	    tx.begin();

	    em.merge(idea);

	    tx.commit();

	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}
	return true;
    }

    public boolean delete(Idea idea) {
	try {

	    EntityManagerFactory emf = PersistenceManager.getInstance()
		    .createEntityManagerFactory();
	    EntityManager em = emf.createEntityManager();

	    EntityTransaction tx = em.getTransaction();
	    tx.begin();

	    em.remove(idea);

	    tx.commit();

	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}
	return true;
    }

    public List<Idea> getAll() {
	EntityManagerFactory emf = PersistenceManager.getInstance()
		.createEntityManagerFactory();
	EntityManager em = emf.createEntityManager();
	ArrayList<Idea> ideas = new ArrayList<Idea>();

	EntityTransaction tx = em.getTransaction();
	tx.begin();

	List<?> fetchedIdeas = em.createQuery("select a from Idea a")
		.getResultList();

	for (Object fetchedIdea : fetchedIdeas) {
	    if (fetchedIdea instanceof Idea) {
		Idea idea = (Idea) fetchedIdea;
		ideas.add(idea);
	    }
	}

	tx.commit();

	return ideas;
    }

    private void closeConnections() {
	em.close();
	emf.close();
    }
}
