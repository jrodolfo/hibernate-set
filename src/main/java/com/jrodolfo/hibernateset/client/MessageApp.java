package com.jrodolfo.hibernateset.client;

import static com.jrodolfo.hibernateset.util.ExceptionUtil.getNonUniqueObjectException;

/**
 * Main class of this app
 * Created by Rod Oliveira (jrodolfo.com) on 2017-01-05
 */
public class MessageApp {

    public static void main(String[] args) {
        // Get exception:
        getNonUniqueObjectException();
    }
}
