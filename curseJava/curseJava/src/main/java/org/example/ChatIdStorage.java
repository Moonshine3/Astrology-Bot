package org.example;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class ChatIdStorage {
    private Set<Long> chatIds;
    private String storageFilePath;

    public ChatIdStorage(String filePath) {
        chatIds = new HashSet<>();
        storageFilePath = filePath;
        loadChatIdsFromFile();
    }

    public void addChatId(long chatId) {
        chatIds.add(chatId);
        saveChatIdsToFile();
    }

    private void loadChatIdsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(storageFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    long chatId = Long.parseLong(parts[0]);
                    chatIds.add(chatId);
                }
            }
            System.out.println("Дані завантажено з файлу: " + storageFilePath);
        } catch (IOException e) {
            System.err.println("Помилка при читанні файлу збереження: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Помилка при перетворенні числа: " + e.getMessage());
        }
    }

    private void saveChatIdsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(storageFilePath))) {
            for (long chatId : chatIds) {
                writer.write(Long.toString(chatId));
                writer.newLine();
            }
            System.out.println("Дані збережено в файл: " + storageFilePath);
        } catch (IOException e) {
            System.err.println("Помилка при записі до файлу збереження: " + e.getMessage());
        }
    }
}
