package com.jrodolfo.hibernateset.dao;

import com.jrodolfo.hibernateset.entity.MessageB;
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
public class MessageBDao {

    private final static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public MessageB get(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (MessageB) session.get(MessageB.class, id);
    }

    public List<MessageB> getAll() {
        return HibernateUtil.getSessionFactory().openSession().createCriteria(MessageB.class).list();
    }

    public MessageB create(String text) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        MessageB messageB = new MessageB(text);
        session.save(messageB);
        session.getTransaction().commit();
        logger.debug(messageB.toString());
        session.close();
        return messageB;
    }

    public void save(List<MessageB> messageBList) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        for (MessageB messageB : messageBList) {
            logger.debug(messageB.toString());
            session.save(messageB);
        }
        session.getTransaction().commit();
        session.close();
    }

    public void update(long id, String text) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction txn = session.getTransaction();
        try {
            txn.begin();
            MessageB messageB = (MessageB) session.get(MessageB.class, id);
            logger.debug(messageB.toString());
            messageB.setText(text);
            logger.debug(messageB.toString());
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

    public void update(MessageB messageB) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction txn = session.getTransaction();
        try {
            txn.begin();
            session.update(messageB);
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
            MessageB messageB = (MessageB) session.get(MessageB.class, id);
            if (messageB != null) {
                logger.debug(messageB.toString());
                session.delete(messageB);
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
            final List<?> listOfmessageBs = session.createCriteria(MessageB.class).list();
            MessageB messageB;
            for (Object obj : listOfmessageBs) {
                messageB = (MessageB) obj;
                logger.debug(messageB.toString());
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
