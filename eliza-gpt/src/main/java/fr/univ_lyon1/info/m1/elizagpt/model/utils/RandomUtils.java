package fr.univ_lyon1.info.m1.elizagpt.model.utils;

import java.util.Random;

/**
 * A class providing basics random methods.
 */
public final class RandomUtils {
    private static final Random MY_RANDOM = new Random();

    private RandomUtils() {
    }

    /**
     * A fonction that return a random element from an array.
     *
     * @param array input array
     * @param <T>   type of the elements in the array
     * @return a random element from the array
     */
    public static <T> T pickArrayRandom(final T[] array) {
        return array[MY_RANDOM.nextInt(array.length)];
    }

    /**
     * Fonction that acts as a coin toss.
     *
     * @return true or false randomly
     */
    public static boolean coinToss() {
        return MY_RANDOM.nextBoolean();
    }

    /**
     * Generates a random integer between 0 (inclusive) and the specified bound (exclusive).
     *
     * @param bound the upper bound (exclusive) for generating the random integer
     * @return a random integer between 0 and bound-1
     */
    public static int nextInt(final int bound) {
        return MY_RANDOM.nextInt(bound);
    }
}
