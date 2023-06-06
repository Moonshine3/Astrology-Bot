package org.example;
import static org.example.names.BotStorage;
import static org.example.names.BotStoragePredictions;


public class Main {
    public static void main(String[] args) {
        String storageFilePath = BotStorage;

        EmailInputProcessor.loadEmailChatIdsFromFile();

        RandomPredictionGenerator generator = new RandomPredictionGenerator(BotStoragePredictions);
        String randomPrediction = generator.getRandomPrediction();
        System.out.println("Випадковий прогноз: " + randomPrediction);
        String tomorrowPrediction = generator.getRandomPrediction();
        System.out.println("Прогноз на завтра: " + tomorrowPrediction);


        SimpleBot bot = new SimpleBot(storageFilePath);
        bot.runBot();
    }
}

