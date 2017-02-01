package com.jrodolfo.hibernateset.util;

import com.jrodolfo.hibernateset.entity.MessageA;
import com.jrodolfo.hibernateset.entity.MessageB;
import com.jrodolfo.hibernateset.service.MessageAService;
import com.jrodolfo.hibernateset.service.MessageBService;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Session;
import org.hibernate.metadata.ClassMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.HashSet;
import java.util.Set;


/**
 * Class to create some Hibernate common exceptions
 * Created by Rod Oliveira (jrodolfo.com) on 2017-01-10
 */
public class ExceptionUtil {

    private final static MessageAService serviceA = new MessageAService();
    private final static MessageBService serviceB = new MessageBService();
    private final static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final static String textOne = "text 1";
    private final static String textTwo = "text 2";

    public static void getNonUniqueObjectException() {

        // Testing MessageA
        try {
            createNonUniqueObjectExceptionA();
        } catch (NonUniqueObjectException e) {
            logger.debug("\n\n\t****** Got NonUniqueObjectException for MessageA ******\n\n");
            e.printStackTrace();
        }

        // Testing MessageB
        try {
            createNonUniqueObjectExceptionB();
        } catch (NonUniqueObjectException e) {
            logger.debug("\n\n\t****** Got NonUniqueObjectException for MessageB ******\n\n");
            e.printStackTrace();
        }
    }

    /**
     * We cannot have two instances referring to the same database column in one hibernate session.
     * When that happens, the exception org.hibernate.NonUniqueObjectException is thrown.
     * This method create a scenario to show this happening. Look to cases 1 and 6.
     *
     * @throws NonUniqueObjectException
     */
    private static void createNonUniqueObjectExceptionA() throws NonUniqueObjectException {
        logger.debug("\n\n\t====== Running for MessageA ======\n");
        final MessageA messageOne;
        final MessageA messageTwo;
        Set<MessageA> setOfMessages = new HashSet();

        messageOne = serviceA.create(textOne);
        setOfMessages.add(messageOne);
        if (isTransient(messageOne)) {
            logger.debug("\n\n\tmessageOne is transient\n");
        } else {
            logger.debug("\n\n\tmessageOne is NOT transient\n");
        }

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        messageTwo = (MessageA) session.get(MessageA.class, messageOne.getId());

        if (isTransient(messageTwo)) {
            logger.debug("\n\n\tmessageTwo is transient\n");
        } else {
            logger.debug("\n\n\tmessageTwo is NOT transient\n");
        }

        setOfMessages.add(messageTwo);
        messageOne.compare(messageTwo);
        messageTwo.setText(textTwo);
        messageOne.compare(messageTwo);
        for (MessageA message : setOfMessages) {
            session.update(message); // Throws exception!
        }
        session.getTransaction().commit();
        session.close();
    }

    /**
     * We cannot have two instances referring to the same database column in one hibernate session.
     * When that happens, the exception org.hibernate.NonUniqueObjectException is thrown.
     * This method create a scenario to show this happening. Look to cases 1 and 6.
     *
     * @throws NonUniqueObjectException
     */
    private static void createNonUniqueObjectExceptionB() throws NonUniqueObjectException {
        logger.debug("\n\n\t====== Running for MessageB ======\n");
        final MessageB messageOne;
        final MessageB messageTwo;
        Set<MessageB> setOfMessages = new HashSet();

        messageOne = serviceB.create(textOne);
        setOfMessages.add(messageOne);

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        messageTwo = (MessageB) session.get(MessageB.class, messageOne.getId());

        if (isTransient(messageTwo)) {
            logger.debug("\n\n\tmessageTwo is transient\n");
        } else {
            logger.debug("\n\n\tmessageTwo is NOT transient\n");
        }

        setOfMessages.add(messageTwo);
        messageOne.compare(messageTwo);
        messageTwo.setText(textTwo);
        messageOne.compare(messageTwo);
        for (MessageB message : setOfMessages) {
            session.update(message); // Throws exception!
        }
        session.getTransaction().commit();
        session.close();
    }

    private static boolean isTransient(Object o) {
        ClassMetadata metadata = HibernateUtil.getSessionFactory().getClassMetadata(o.getClass());
        return (metadata.getIdentifier(o) == null);
    }

}
