package org.scify.libgdxgame.helpers;


import java.util.Random;

public class Utils {

    private static Random random = new Random();

    public static float randomBetweenNumbers(float min, float max) {
        return random.nextFloat() * (max - min) + min;
    }
}
