package de.bht.se2.ideafactory.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import de.bht.se2.ideafactory.model.Idea;
import de.bht.se2.ideafactory.util.PersistenceManager;

public class EclipseLinkIdeaDAO {

    EntityManagerFactory emf;
    EntityManager        em;

    public EclipseLinkIdeaDAO() {
        emf = PersistenceManager.getInstance().createEntityManagerFactory();
        em = emf.createEntityManager();
    }

    public boolean createIdea(Idea newIdea) {

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        em.persist(newIdea);
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
        ArrayList<Idea> ideas = new ArrayList<Idea>();

        EntityManagerFactory emf = PersistenceManager.getInstance()
                .createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

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

    public void closeConnections() {
        em.close();
        emf.close();
    }
}
