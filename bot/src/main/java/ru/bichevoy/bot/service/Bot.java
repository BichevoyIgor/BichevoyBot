package ru.bichevoy.bot.service;

import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.bichevoy.bot.config.*;
import ru.bichevoy.bot.model.User;
import ru.bichevoy.bot.model.UserRepository;

import java.sql.Timestamp;

@Slf4j
@Component
public class Bot extends TelegramLongPollingBot {

    @Autowired
    private UserRepository userRepository;
    final BotConfig config;
    final Commands listOfCommands;
    final String HELP_STRING = " This bot can do next:\n" +
            "/start - bla bla bla \n" +
            "/mydata - bla bla bla \n" +
            "/deletemydata - bla bla bla \n" +
            "/help - bla bla bla \n" +
            "/settings - bla bla bla";

    public Bot(BotConfig config, Commands listOfCommads) {
        this.config = config;
        this.listOfCommands = listOfCommads;
        setCommandsList();
    }

    private void setCommandsList() {
        try {
            this.execute(new SetMyCommands(listOfCommands.getListOfCommands(), new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            log.error("Error setting bot's command list" + e.getMessage());
        }
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getBotToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            String userName = update.getMessage().getChat().getFirstName();

            log.info(userName + ": " + messageText);
            long chatid = update.getMessage().getChatId();

            switch (messageText) {
                case "/start":
                    regUser(update.getMessage());
                    Keyboard keyboard = new KeyBoardAsk();
                    commandReceived(chatid, userName, keyboard);
                    break;
                case "/help":
                    keyboard = new KeyBoardSay();
                    commandReceived(chatid, HELP_STRING, keyboard);
                    break;
                default:
                    sendMessage(chatid, "Command not supported", null);
                    break;
            }
        }
    }

    private void regUser(Message message) {
        if (!userRepository.existsById(message.getChatId())) {
            Long chatId = message.getChatId();
            Chat chat = message.getChat();

            User user = new User();
            user.setChatId(chatId);
            user.setFirstName(chat.getFirstName());
            user.setLastName(chat.getLastName());
            user.setUserName(chat.getUserName());
            user.setRegisteredAt(new Timestamp(System.currentTimeMillis()));
            userRepository.save(user);
            log.info("User saved " + user.getUserName());
        }
    }

    private void commandReceived(long chatId, String name, Keyboard keyboard) {
        String answer = EmojiParser.parseToUnicode("Hi " + name + " какие дела? :blush:");
        sendMessage(chatId, answer, keyboard);

    }

    private void sendMessage(long chatId, String text, Keyboard keyboard) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        if (keyboard!=null){
            sendMessage.setReplyMarkup(keyboard.getKeyboard());
        }
        sendMessage.setText(text);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Error during sending message: " + e.getMessage());
        }
    }

}
