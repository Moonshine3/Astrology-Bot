package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomPredictionGenerator {
    private List<String> predictions;
    private Random random;
    private int currentPredictionIndex;

    public RandomPredictionGenerator(String filePath) {
        predictions = new ArrayList<>();
        random = new Random();
        currentPredictionIndex = 0;

        loadPredictionsFromFile(filePath);
    }

    private void loadPredictionsFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                predictions.add(line);
            }
            System.out.println("Прогнози завантажено з файлу: " + filePath);
        } catch (IOException e) {
            System.err.println("Помилка при читанні файлу прогнозів: " + e.getMessage());
        }
    }

    public String getRandomPrediction() {
        if (predictions.isEmpty()) {
            return "Немає доступних прогнозів.";
        }

        String prediction = predictions.get(currentPredictionIndex);
        updateCurrentPredictionIndex();
        return prediction;
    }

    private void updateCurrentPredictionIndex() {
        currentPredictionIndex++;
        if (currentPredictionIndex >= predictions.size()) {
            currentPredictionIndex = 0;
        }
    }
    /* public static void main(String[] args) {
        String filePath = "E:\\curseJava\\predictions.txt";
        RandomPredictionGenerator generator = new RandomPredictionGenerator(filePath);


        System.out.println("Прогнози на сьогодні:");
        for (int i = 0; i < 5; i++) {
            String prediction = generator.getRandomPrediction();
            System.out.println(prediction);
        }

        System.out.println("Прогнози на завтра:");
        for (int i = 0; i < 5; i++) {
            String prediction = generator.getRandomPrediction();
            System.out.println(prediction);
        }


    }
    */

}
