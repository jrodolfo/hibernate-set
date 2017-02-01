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
    private final static String textThree = "text 3";

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
        final MessageA messageAOne;
        final MessageA messageATwo;
        Set<MessageA> setOfMessagesA = new HashSet();

        messageAOne = serviceA.create(textOne);
        setOfMessagesA.add(messageAOne);
        if (isTransient(messageAOne)) {
            logger.debug("\n\n\tmessageAOne is transient\n");
        } else {
            logger.debug("\n\n\tmessageAOne is NOT transient\n");
        }

        Session sessionA = HibernateUtil.getSessionFactory().openSession();
        sessionA.beginTransaction();
        messageATwo = (MessageA) sessionA.get(MessageA.class, messageAOne.getId());

        if (isTransient(messageATwo)) {
            logger.debug("\n\n\tmessageATwo is transient\n");
        } else {
            logger.debug("\n\n\tmessageATwo is NOT transient\n");
        }

        setOfMessagesA.add(messageATwo);
        messageAOne.compare(messageATwo);
        messageATwo.setText(textTwo);
        messageAOne.compare(messageATwo);
        for (MessageA messageA : setOfMessagesA) {
            sessionA.update(messageA); // Throws exception!
        }
        sessionA.getTransaction().commit();
        sessionA.close();
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
        final MessageB messageBOne;
        final MessageB messageBTwo;
        Set<MessageB> setOfMessagesB = new HashSet();

        messageBOne = serviceB.create(textOne);
        setOfMessagesB.add(messageBOne);

        Session sessionB = HibernateUtil.getSessionFactory().openSession();
        sessionB.beginTransaction();
        messageBTwo = (MessageB) sessionB.get(MessageB.class, messageBOne.getId());
        setOfMessagesB.add(messageBTwo);
        messageBOne.compare(messageBTwo);
        messageBTwo.setText(textTwo);
        messageBOne.compare(messageBTwo);
        for (MessageB messageB : setOfMessagesB) {
            sessionB.update(messageB); // Does not throws exception!
        }
        sessionB.getTransaction().commit();
        sessionB.close();
    }

    private static boolean isTransient(Object o) {
        ClassMetadata metadata = HibernateUtil.getSessionFactory().getClassMetadata(o.getClass());
        return (metadata.getIdentifier(o) == null);
    }

}
