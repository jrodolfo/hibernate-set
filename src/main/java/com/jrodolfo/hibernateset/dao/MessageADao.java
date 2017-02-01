package com.jrodolfo.hibernateset.dao;

import com.jrodolfo.hibernateset.entity.MessageA;
import com.jrodolfo.hibernateset.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.List;

/**
 * DAO Class to access the database
 * Created by Rod Oliveira (jrodolfo.com) on 2017-01-06
 */
public class MessageADao {

    private final static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public MessageA get(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (MessageA) session.get(MessageA.class, id);
    }

    public List<MessageA> getAll() {
        return HibernateUtil.getSessionFactory().openSession().createCriteria(MessageA.class).list();
    }

    public MessageA create(String text) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        MessageA messageA = new MessageA(text);
        session.save(messageA);
        session.getTransaction().commit();
        logger.debug(messageA.toString());
        session.close();
        return messageA;
    }

    public void save(List<MessageA> messageAList) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        for (MessageA messageA : messageAList) {
            logger.debug(messageA.toString());
            session.save(messageA);
        }
        session.getTransaction().commit();
        session.close();
    }

    public void update(long id, String text) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction txn = session.getTransaction();
        try {
            txn.begin();
            MessageA messageA = (MessageA) session.get(MessageA.class, id);
            logger.debug(messageA.toString());
            messageA.setText(text);
            logger.debug(messageA.toString());
            txn.commit();
        } catch (Exception e) {
            if (txn != null) {
                txn.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void update(MessageA messageA) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction txn = session.getTransaction();
        try {
            txn.begin();
            session.update(messageA);
            txn.commit();
        } catch (Exception e) {
            if (txn != null) {
                txn.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void delete(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction txn = session.getTransaction();
        try {
            txn.begin();
            MessageA messageA = (MessageA) session.get(MessageA.class, id);
            if (messageA != null) {
                logger.debug(messageA.toString());
                session.delete(messageA);
            }
            txn.commit();
        } catch (Exception e) {
            if (txn != null) {
                txn.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void deleteAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction txn = session.getTransaction();
        try {
            txn.begin();
            final List<?> listOfmessageAs = session.createCriteria(MessageA.class).list();
            MessageA messageA;
            for (Object obj : listOfmessageAs) {
                messageA = (MessageA) obj;
                logger.debug(messageA.toString());
                session.delete(obj);
            }
            txn.commit();
        } catch (Exception e) {
            if (txn != null) {
                txn.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
