package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

class LoginCommand implements Command {
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        ConsoleHelper.writeMessage(res.getString("specify.data"));
        String cardNumber;
        while ((cardNumber = ConsoleHelper.readString()).length() != 12 || !cardNumber.matches("\\d{12}") || !validCreditCards.containsKey(cardNumber)) {
            ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), cardNumber));
        }
        String pin;
        while ((pin = ConsoleHelper.readString()).length() != 4 || !pin.matches("\\d{4}") || !validCreditCards.getString(cardNumber).equals(pin)) {
            ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
        }
        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), cardNumber));
    }
}
