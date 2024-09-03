package com.jeido.hospital.repositories;

import com.jeido.hospital.utils.SessionFactorySingleton;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public abstract class BaseRepository<T> {
    protected final SessionFactory sessionFactory;
    protected Session session;
    private final Class<T> entityClass;

    protected BaseRepository(Class<T> entityClass) {
        sessionFactory = SessionFactorySingleton.getSessionFactory();
        this.entityClass = entityClass;
    }

    public T createOrUpdate(T elem) {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.saveOrUpdate(elem);
            session.getTransaction().commit();
            return elem;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }
    }

    public boolean delete(T elem) {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(elem);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return false;
        } finally {
            session.close();
        }
    }

    public T findById(int id) {
        session = sessionFactory.openSession();
        T t = session.get(entityClass, id);
        session.close();
        return t;
    }

    public List<T> findAll() {
        session = sessionFactory.openSession();
        List<T> elem = session.createQuery(String.format("from %s", entityClass.getSimpleName()), entityClass).list();
        session.close();
        return elem;
    }
}
