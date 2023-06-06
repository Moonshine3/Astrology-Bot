package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import static org.example.KeyboardUtil.sendMessageWithKeyboard;
import static org.example.TarotDescriptionManager.getRandomTarotCard;
import static org.example.TarotDescriptionManager.getTarotDescription;
import static org.example.ZodiacSignDeterminer.getZodiacSign;
import static org.example.ZodiacSignDeterminer.isDateOfBirthValid;
import static org.example.names.*;


public class SimpleBot extends TelegramLongPollingBot {


    private boolean waitingForDate = false;

    private boolean waitingForConsultation = false;

    private boolean  waitingForZodiacManager = false;

    private  boolean waitingForCompatibilityFirstSign = false;

    private  boolean waitingForSecondSign = false;
    private boolean  waitingForTaro = false;
    private  boolean waitingForMoon = false;
    private  boolean waitingForPredict = false;
    private  boolean waitingForStart = false;
    private  boolean  waitingForPay = false;
    private String activeScenario;
    private String compatibilityFirstSign ;
    private ChatIdStorage chatIdStorage;
    private String response;

    public void runBot() {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(this);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public SimpleBot(String storageFilePath) {
        chatIdStorage = new ChatIdStorage(storageFilePath);
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            if (update.getMessage().getText().equals("/start")) {

                chatIdStorage.addChatId(chatId);

            }
            switch (messageText) {
                case "/start":
                       waitingForStart = true;
                    if(EmailInputProcessor.checkEmailExists(chatId)) {

                         response = "Привіт! Я твій персональний астролог.\nВибери опцію:";

                        sendMessageWithKeyboard(this, chatId, response, KeyboardUtil.getKeyboardMain());
                    }
                    else{
                        String response = "Привіт! Я твій персональний астролог.Нажаль ти ще не підписався ,тож маєш певні обмеження\nВибери опцію:";

                        sendMessageWithKeyboard(this, chatId, response, KeyboardUtil.getKeyboardMain());

                    }
                    waitingForStart = false;
                    break;
                case "Визначення знака зодіака":

                    activeScenario = org.example.names.firstFunc;

                    String dateResponse = "Введи свою дату народження у форматі ДД.ММ.РРРР:";

                    sendMessageWithKeyboard(this, chatId, dateResponse, KeyboardUtil.MultiKeyboard(activeScenario));

                    waitingForDate = true;
                    break;
                case "Сумісність":

                    activeScenario = org.example.names.secondFunc;
                    String compatibilityResponse = "Введи перший знак зодіаку:";
                    sendMessageWithKeyboard(this, chatId, compatibilityResponse, KeyboardUtil.ZodiacKeyboard());
                    waitingForCompatibilityFirstSign = true;
                    break;
                case "Опис знаків зодіаку":
                    activeScenario = org.example.names.thierdFunc;

                    String ZodiacResponse = "Оберіть потрібний знак";

                    sendMessageWithKeyboard(this, chatId, ZodiacResponse, KeyboardUtil.ZodiacKeyboard());
                    waitingForZodiacManager = true;
                    break;

                case "Консультація":
                    if(EmailInputProcessor.checkEmailExists(chatId)) {
                        activeScenario = org.example.names.fourFunc;

                        String consultationResponse = "Оберіть консультанта:";

                        sendMessageWithKeyboard(this, chatId, consultationResponse, KeyboardUtil.ConsultKeyboard());
                        waitingForConsultation = true;
                    }
                    else {
                        sendMessageWithKeyboard(this, chatId, NotActivated, KeyboardUtil.getKeyboardMain());
                        waitingForConsultation = false;
                    }

                    break;

                case "Розклад таро":
                    if(EmailInputProcessor.checkEmailExists(chatId)) {
                        activeScenario = org.example.names.fiveFunc;
                        String TaroResponse = "Перемішуєємо колоду....";
                        sendMessageWithKeyboard(this, chatId, TaroResponse, KeyboardUtil.MultiKeyboard(activeScenario));

                        waitingForTaro = true;
                    }
                    else {
                        sendMessageWithKeyboard(this, chatId, NotActivated, KeyboardUtil.getKeyboardMain());
                        waitingForTaro = false;
                    }

                    break;

                case "Місячний календар":
                    activeScenario = org.example.names.sixFunc;
                    String MoonResponse = "Визначаємо фазу місяця...";
                    sendMessageWithKeyboard(this, chatId, MoonResponse, KeyboardUtil.MultiKeyboard(activeScenario));

                    waitingForMoon = true;
                    break;
                case "Астрологічний прогноз" :
                    activeScenario = org.example.names.sevenFunc;
                    String PredictResponse = "Отримати прогноз";

                    sendMessageWithKeyboard(this, chatId,PredictResponse, KeyboardUtil.MultiKeyboard(activeScenario));

                    waitingForPredict = true;

                    break;
                case "Підписка" :
                    activeScenario = org.example.names.eightFunc;
                    String payResponse = "Введіть свою електронну пошту:";

                    waitingForPay = true;
                    sendMessageWithKeyboard(this, chatId, payResponse, KeyboardUtil.MultiKeyboard(activeScenario));
                    break;

                case "Назад":
                    // Скидаємо всі стани очікування
                    waitingForDate = false;
                    waitingForZodiacManager = false;
                    waitingForConsultation = false;
                    waitingForCompatibilityFirstSign = false;
                    waitingForSecondSign = false;
                    waitingForTaro =false;
                    waitingForMoon = false;
                    waitingForPredict = false;
                    waitingForPay = false;
                    waitingForStart = false;


                    String startResponse = "Вибери опцію:";
                    sendMessageWithKeyboard(this, chatId, startResponse, KeyboardUtil.getKeyboardMain());
                    break;


                default:

                    if (waitingForDate) {
                        // Бот отримав введену дату
                        if (isDateOfBirthValid(messageText)) {
                            // Перевірка на коректність введеної дати
                            String zodiacSign = getZodiacSign(messageText);
                            String zodiacSignResponse = "Твій знак зодіаку: " + zodiacSign;
                            sendMessage(chatId, zodiacSignResponse);
                        } else {
                            // Якщо введена некоректна дата, повідомляємо користувача про помилку
                            String errorResponse = "Некоректний формат дати. Введи свою дату народження у форматі ДД.ММ.РРРР:";
                            sendMessage(chatId, errorResponse);
                        }
                        // Скидаємо стан очікування дати
                        waitingForDate = false;
                        sendMessageWithKeyboard(this, chatId, reply, KeyboardUtil.keyboardMarkup(activeScenario));
                    }
                    else if (waitingForCompatibilityFirstSign) {
                        compatibilityFirstSign = messageText;
                        String compatibilitySecondSignResponse = "Введи другий знак зодіаку:";
                        sendMessageWithKeyboard(this, chatId, compatibilitySecondSignResponse, KeyboardUtil.ZodiacKeyboard());
                        waitingForCompatibilityFirstSign = false;
                        waitingForSecondSign = true;

                    }


                    else if (waitingForSecondSign) {
                        String compatibility = ZodiacCompatibility.calculateCompatibility(compatibilityFirstSign, messageText);
                        sendMessage(chatId, compatibility);
                        waitingForSecondSign = false;
                        sendMessageWithKeyboard(this, chatId, reply, KeyboardUtil.keyboardMarkup(activeScenario));
                    }

                    else if (waitingForZodiacManager) {

                        String zodiacDescription = ZodiacDescriptionManager.getZodiacDescription(messageText);
                        sendMessage(chatId, zodiacDescription);

                        waitingForZodiacManager = false;
                        sendMessageWithKeyboard(this, chatId, reply, KeyboardUtil.keyboardMarkup(activeScenario));
                    }

                    else if (waitingForConsultation) {
                        if(messageText.equals(org.example.names.manConsult)){
                            String consultDescription = org.example.names.manConsultLink;
                            sendMessage(chatId, consultDescription);
                        } else if (messageText.equals(org.example.names.womanConsult))  {
                            String consultDescription = org.example.names.womanConsultLink;
                            sendMessage(chatId, consultDescription);
                        }
                        String thankYouDescription = "Дякую за ваше звернення до персональної консультації.";
                        waitingForConsultation = false;
                        sendMessageWithKeyboard(this, chatId, thankYouDescription, KeyboardUtil.MultiKeyboard(activeScenario));
                    }
                    else if (waitingForTaro) {

                            String randomCard = getRandomTarotCard();
                            String description = getTarotDescription(randomCard);

                            sendMessage(chatId, randomCard + ":  " + description);

                            waitingForTaro = false;
                            sendMessageWithKeyboard(this, chatId, reply, KeyboardUtil.keyboardMarkup(activeScenario));
                    }
                    else if (waitingForMoon) {
                        String lunarPhasePrediction = LunarPhasePredictor.getLunarPhasePrediction();
                        sendMessage(chatId,lunarPhasePrediction);
                        waitingForMoon = false;
                        sendMessageWithKeyboard(this, chatId, reply, KeyboardUtil.keyboardMarkup(activeScenario));
                    }
                    else if (waitingForPay) {

                        EmailInputProcessor.processEmailInput(chatId,messageText);
                        if (EmailInputProcessor.valid) {
                            waitingForPay = false;
                            sendMessageWithKeyboard(this, chatId, EmailInputProcessor.Email, KeyboardUtil.MultiKeyboard(activeScenario));
                        }
                        else{
                            waitingForPay = false;
                            sendMessageWithKeyboard(this, chatId, EmailInputProcessor.Email, KeyboardUtil.keyboardMarkup(activeScenario));
                        }
                    }
                    else if (waitingForPredict) {
                        RandomPredictionGenerator generator = new RandomPredictionGenerator(BotStoragePredictions);


                        String randomPrediction = generator.getRandomPrediction();


                        sendMessage(chatId,randomPrediction);
                        waitingForPredict = false;
                        sendMessageWithKeyboard(this, chatId, reply, KeyboardUtil.keyboardMarkup(activeScenario));
                    }

                    else {
                        response = "Я не розумію вас.Оберіть опцію";

                        waitingForDate = false;
                        waitingForZodiacManager = false;
                        waitingForConsultation = false;
                        waitingForCompatibilityFirstSign = false;
                        waitingForSecondSign = false;
                        waitingForTaro =false;
                        waitingForMoon = false;
                        waitingForPredict = false;
                        waitingForPay = false;
                        waitingForStart = false;


                        sendMessageWithKeyboard(this, chatId, response, KeyboardUtil.getKeyboardMain());
                    }
                break;
            }
        }
    }

    private void sendMessage(long chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return org.example.names.BotName;
    }

    @Override
    public String getBotToken() {
        return org.example.names.BotToken;
    }
}