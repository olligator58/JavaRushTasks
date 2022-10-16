package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;

class InfoCommand implements Command {

    @Override
    public void execute() {
        boolean hasMoney = false;
        for (CurrencyManipulator cm : CurrencyManipulatorFactory.getAllCurrencyManipulators()) {
            if (cm.hasMoney()) {
                ConsoleHelper.writeMessage(String.format("%s - %d", cm.getCurrencyCode(), cm.getTotalAmount()));
                hasMoney = true;
            }
        }
        if (!hasMoney) {
            ConsoleHelper.writeMessage("No money available.");
        }
    }
}
