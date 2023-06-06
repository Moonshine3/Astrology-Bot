package org.example;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

import static org.example.names.*;
public class KeyboardUtil {




    public static SendMessage createMessageWithKeyboard(long chatId, String message, ReplyKeyboardMarkup keyboard) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        sendMessage.setReplyMarkup(keyboard);

        return sendMessage;
    }

    public static ReplyKeyboardMarkup getKeyboardMain() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        row1.add(firstFunc);
        row1.add(secondFunc);
        KeyboardRow row2 = new KeyboardRow();
        row2.add(thierdFunc);
        row2.add(sixFunc);
        KeyboardRow row3 = new KeyboardRow();
        row3.add(fiveFunc);
        row3.add(fourFunc);
        KeyboardRow row4 = new KeyboardRow();
        row4.add(sevenFunc);
        row4.add(eightFunc);

        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);
        keyboard.add(row4);
        keyboardMarkup.setKeyboard(keyboard);

        return keyboardMarkup;
    }
    public static ReplyKeyboardMarkup ConsultKeyboard() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        row1.add(manConsult);

        KeyboardRow row2 = new KeyboardRow();
        row2.add(womanConsult);

        KeyboardRow row3 = new KeyboardRow();
        row3.add(back);



        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);


        keyboardMarkup.setKeyboard(keyboard);

        return keyboardMarkup;
    }
    public static ReplyKeyboardMarkup ZodiacKeyboard() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        row1.add("Рак");
        row1.add("Овен");
        row1.add("Телець");
        KeyboardRow row2 = new KeyboardRow();
        row2.add("Стрілець");
        row2.add("Близнюки");
        row2.add("Риби");
        KeyboardRow row3 = new KeyboardRow();
        row3.add("Скорпіон");
        row3.add("Лев");
        row3.add("Водолій");
        KeyboardRow row4 = new KeyboardRow();
        row4.add("Діва");
        row4.add("Терези");
        row4.add("Козеріг");
        KeyboardRow row5 = new KeyboardRow();
        row5.add(back);


        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);
        keyboard.add(row4);
        keyboard.add(row5);



        keyboardMarkup.setKeyboard(keyboard);

        return keyboardMarkup;
    }



    public static ReplyKeyboardMarkup keyboardMarkup(String back) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();

        row1.add(back);
        row1.add(names.back);



        keyboard.add(row1);

        keyboardMarkup.setKeyboard(keyboard);

        return keyboardMarkup;
    }

    public static ReplyKeyboardMarkup MultiKeyboard(String scenario) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        if (scenario == fiveFunc) {
            row1.add("Розкласти карти");
        }
        else if (scenario == sixFunc){
           row1.add("Почати");
        }
        else if (scenario == firstFunc){
            row1.add(back);
        }
        else if (scenario == fourFunc){
            row1.add(back);
        }
        else if (scenario == sevenFunc){
            row1.add("Прогноз");
        }
        else if (scenario == eightFunc){

            row1.add(back);
        }
        else  {
            row1.add("Помилка");
        }

        keyboard.add(row1);

        keyboardMarkup.setKeyboard(keyboard);

        return keyboardMarkup;
    }

    public static void sendMessageWithKeyboard(SimpleBot bot, long chatId, String message,ReplyKeyboardMarkup keyboard) {
        SendMessage sendMessage = createMessageWithKeyboard(chatId, message,keyboard);

        try {
            bot.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}