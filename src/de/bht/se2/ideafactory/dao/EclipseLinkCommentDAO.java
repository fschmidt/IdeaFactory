package de.bht.se2.ideafactory.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import de.bht.se2.ideafactory.model.Comment;
import de.bht.se2.ideafactory.util.PersistenceManager;

public class EclipseLinkCommentDAO {

    private static EclipseLinkCommentDAO instance = null;
    EntityManagerFactory emf;
    EntityManager em;

    private EclipseLinkCommentDAO() {
	emf = PersistenceManager.getInstance().createEntityManagerFactory();
	em = emf.createEntityManager();
    }

    public static EclipseLinkCommentDAO createInstance() {
	if (instance != null) {
	    instance.closeConnections();
	}
	instance = new EclipseLinkCommentDAO();
	return instance;
    }

    public boolean create(Comment comment) {
	EntityManagerFactory emf = PersistenceManager.getInstance()
		.createEntityManagerFactory();
	EntityManager em = emf.createEntityManager();

	EntityTransaction tx = em.getTransaction();

	tx.begin();

	em.persist(comment);
	tx.commit();

	return true;
    }

    public boolean update(Comment comment) {

	try {
	    EntityManagerFactory emf = PersistenceManager.getInstance()
		    .createEntityManagerFactory();
	    EntityManager em = emf.createEntityManager();

	    EntityTransaction tx = em.getTransaction();
	    tx.begin();

	    em.merge(comment);

	    tx.commit();

	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}
	return true;
    }

    public boolean delete(Comment comment) {
	try {

	    EntityManagerFactory emf = PersistenceManager.getInstance()
		    .createEntityManagerFactory();
	    EntityManager em = emf.createEntityManager();

	    EntityTransaction tx = em.getTransaction();
	    tx.begin();

	    em.remove(comment);

	    tx.commit();

	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}
	return true;
    }

//    public List<Comment> getAll(Idea idea) {
//	EntityManagerFactory emf = PersistenceManager.getInstance()
//		.createEntityManagerFactory();
//	EntityManager em = emf.createEntityManager();
//	ArrayList<Idea> ideas = new ArrayList<Idea>();
//
//	EntityTransaction tx = em.getTransaction();
//	tx.begin();
//
//	List<?> fetchedComments = em.createQuery("select a from Comment a")
//		.getResultList();
//
//	for (Object fetchedIdea : fetchedComments) {
//	    if (fetchedIdea instanceof Idea) {
//		Idea idea = (Idea) fetchedIdea;
//		ideas.add(idea);
//	    }
//	}
//
//	tx.commit();
//
//	return ideas;
//    }

    private void closeConnections() {
	em.close();
	emf.close();
    }
}
