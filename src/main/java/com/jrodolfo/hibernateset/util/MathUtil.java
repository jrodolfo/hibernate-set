package com.jrodolfo.hibernateset.util;

import java.util.Random;

/**
 * Class for math utilities
 * Created by Rod Oliveira (jrodolfo.com) on 2017-01-08
 */
public class MathUtil {

    public static Long getRandomLong(long min, long max) {
        Random random = new Random();
        return min + ((long) (random.nextDouble() * (max - min)));
    }
}
