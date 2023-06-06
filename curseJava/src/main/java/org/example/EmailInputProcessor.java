package org.example;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static org.example.names.BotStorage;

public class EmailInputProcessor {
    public static String Email;
    public static boolean valid;
    private static Map<Long, String> emailMap = new HashMap<>();



    public static void processEmailInput(long chatId, String emailAddress) {
        if (isValidEmailAddress(emailAddress)) {
            Email = "Дякуємо за надану єлектронну пошту.Всі обмеження знято!";
            valid = true;
            emailMap.put(chatId, emailAddress);
            saveEmailChatIdsToFile();
            System.out.println("Адреса електронної пошти для чату " + chatId + " збережена.");
        } else {
            valid = false;
            Email = "Введена адреса електронної пошти недійсна. Будь ласка, спробуйте ще раз.";
            System.out.println("Введена адреса електронної пошти недійсна. Будь ласка, спробуйте ще раз.");
        }
    }

    private static boolean isValidEmailAddress(String emailAddress) {
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(emailPattern, emailAddress);
    }

    public static void loadEmailChatIdsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(BotStorage))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    long chatId = Long.parseLong(parts[0]);
                    String emailAddress = parts[1];
                    emailMap.put(chatId, emailAddress);
                }
            }
            System.out.println("Дані електронної пошти завантажено з файлу: " + BotStorage);
        } catch (IOException e) {
            System.err.println("Помилка при читанні файлу збереження: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Помилка при перетворенні числа: " + e.getMessage());
        }
    }

    public static void saveEmailChatIdsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BotStorage))) {
            for (Map.Entry<Long, String> entry : emailMap.entrySet()) {
                long chatId = entry.getKey();
                String emailAddress = entry.getValue();
                writer.write(chatId + ":" + emailAddress);
                writer.newLine();
            }
            System.out.println("Дані збережено в файл: " + BotStorage);
        } catch (IOException e) {
            System.err.println("Помилка при записі до файлу збереження: " + e.getMessage());
        }
    }

    public static boolean checkEmailExists(long chatId) {
        return emailMap.containsKey(chatId);
    }
}