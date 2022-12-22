package ru.bichevoy.bot.config;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;


public class KeyBoardAsk extends Keyboard {

    @Override
    public ReplyKeyboardMarkup getKeyboard() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("First ASK");
        row.add("Second ASK");
        keyboardRows.add(row);
        row = new KeyboardRow();
        row.add("третья ASK");
        row.add("четвертая ASK");
        row.add("пятая ASK");
        keyboardRows.add(row);
        keyboardMarkup.setKeyboard(keyboardRows);
        return keyboardMarkup;
    }
}
