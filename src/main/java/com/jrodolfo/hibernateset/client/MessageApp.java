package com.jrodolfo.hibernateset.client;

import com.jrodolfo.hibernateset.entity.MessageA;
import com.jrodolfo.hibernateset.service.MessageAService;
import com.jrodolfo.hibernateset.entity.MessageB;
import com.jrodolfo.hibernateset.service.MessageBService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

import static com.jrodolfo.hibernateset.util.ExceptionUtil.getNonUniqueObjectException;

/**
 * Main class of this app
 * Created by Rod Oliveira (jrodolfo.com) on 2017-01-05
 */
public class MessageApp {

    private final static MessageAService serviceA = new MessageAService();
    private final static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final static String textOne = "text 1";
    private final static String textTwo = "text 2";
    private final static String textThree = "text 3";

    public static void main(String[] args) {

//        // Basic CRUD (create, retrieve, update, delete) operations:
//
//        // create
//        MessageA messageAOne = serviceA.create(textOne);
//        Long idOne = messageAOne.getId();
//        MessageA messageATwo = serviceA.create(textTwo);
//        Long idTwo = messageATwo.getId();
//
//        // retrieve
//        MessageA messageAThree = serviceA.get(idOne);
//        logger.debug("messageA retrieved: " + messageAThree);
//
//        // update
//        serviceA.update(idTwo, textThree);
//
//        // delete
//        serviceA.delete(idOne);
//        serviceA.deleteAll();

        // Get exception:
        getNonUniqueObjectException();
    }
}
