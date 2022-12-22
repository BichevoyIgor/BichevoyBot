package ru.bichevoy.bot.config;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.ArrayList;
import java.util.List;

@Component
public class Commands {
   List<BotCommand> listOfCommands;
    {
        listOfCommands = new ArrayList<>();
        listOfCommands.add(new BotCommand("/start", "get a welcome message"));
        listOfCommands.add(new BotCommand("/mydata", "get your data"));
        listOfCommands.add(new BotCommand("/deletemydata", "delete your data"));
        listOfCommands.add(new BotCommand("/help", "info how to use this bot"));
        listOfCommands.add(new BotCommand("/settings", "set your preferences"));
    }

    public List<BotCommand> getListOfCommands() {
        return listOfCommands;
    }
}
