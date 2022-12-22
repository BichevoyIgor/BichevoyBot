package ru.bichevoy.bot.config;


import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

public abstract class Keyboard {

    public abstract ReplyKeyboardMarkup getKeyboard();

}
