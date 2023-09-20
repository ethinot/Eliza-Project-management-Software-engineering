package fr.univ_lyon1.info.m1.elizagpt.model.utils;

import java.util.Random;

public class RandomUtils {
    private static final Random random = new Random();
    public static <T> T pickArrayRandom(final T[] array) {
        return array[random.nextInt(array.length)];
    }
    public static boolean coinToss() {
        return random.nextBoolean();
    }
}
