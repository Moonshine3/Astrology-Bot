package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ZodiacCompatibility {
    private static final Map<String, String> compatibilityMap = new HashMap<>();

    static {
        String[] zodiacSigns = {"Овен", "Телець", "Близнюки", "Рак","Скорпіон","Діва","Терези","Козеріг","Стрілець","Риби","Лев","Водолій"};

        for (String sign1 : zodiacSigns) {
            for (String sign2 : zodiacSigns) {
                String combination = getCombinationKey(sign1, sign2);
                String compatibility = getRandomCompatibilityPercentage();
                compatibilityMap.put(combination, compatibility);
            }
        }
    }

    public static String calculateCompatibility(String sign1, String sign2) {
        String combination = getCombinationKey(sign1, sign2);
        String compatibility = compatibilityMap.get(combination);

        if (compatibility != null) {
            return compatibility;
        } else {
            return "Немає даних про сумісність для заданої комбінації знаків.";
        }
    }

    private static String getCombinationKey(String sign1, String sign2) {

        return sign1 + sign2;
    }

    private static String getRandomCompatibilityPercentage() {
        Random random = new Random();
        int percentage = random.nextInt(101);
        return "Сумісність: " + percentage + "%";
    }
}
