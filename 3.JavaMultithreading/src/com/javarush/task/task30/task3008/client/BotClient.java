package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BotClient extends Client {

    public class BotSocketThread extends SocketThread {

        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            if (message != null && message.contains(":")) {
                String[] words = message.split(":");
                String name = words[0].trim();
                String text = words[1].trim();
                SimpleDateFormat formatter = null;
                switch (text.toLowerCase()) {
                    case "дата":
                        formatter = new SimpleDateFormat("d.MM.YYYY");
                        break;
                    case "день":
                        formatter = new SimpleDateFormat("d");
                        break;
                    case "месяц":
                        formatter = new SimpleDateFormat("MMMM");
                        break;
                    case "год":
                        formatter = new SimpleDateFormat("YYYY");
                        break;
                    case "время":
                        formatter = new SimpleDateFormat("H:mm:ss");
                        break;
                    case "час":
                        formatter = new SimpleDateFormat("H");
                        break;
                    case "минуты":
                        formatter = new SimpleDateFormat("m");
                        break;
                    case "секунды":
                        formatter = new SimpleDateFormat("s");
                        break;
                }
                if (formatter != null) {
                    sendTextMessage(String.format("Информация для %s: %s", name, formatter.format(Calendar.getInstance().getTime())));
                }
            }
        }
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        return String.format("date_bot_%s", (int) (Math.random() * 100));
    }

    public static void main(String[] args) {
        BotClient botClient = new BotClient();
        botClient.run();
    }
}
