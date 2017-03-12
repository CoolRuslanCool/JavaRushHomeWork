package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by ruslan on 02.03.17.
 */
public class BotClient extends Client {

    public static void main(String[] args) {
        new BotClient().run();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected String getUserName() {
        return "date_bot_" + (int) (Math.random() * 100);
    }

    public class BotSocketThread extends SocketThread {

        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            if (message.contains(": ")) {
                String[] mas = message.split(": ");
                Calendar calendar = Calendar.getInstance();
                String info = "Информация для " + mas[0] + ": ";
                switch (mas[1]) {
                    case "дата":
                        sendTextMessage(info + new SimpleDateFormat("d.MM.YYYY").format(calendar.getTime()));
                        break;
                    case "день":
                        sendTextMessage(info + new SimpleDateFormat("d").format(calendar.getTime()));
                        break;
                    case "месяц":
                        sendTextMessage(info + new SimpleDateFormat("MMMM").format(calendar.getTime()));
                        break;
                    case "год":
                        sendTextMessage(info + new SimpleDateFormat("YYYY").format(calendar.getTime()));
                        break;
                    case "время":
                        sendTextMessage(info + new SimpleDateFormat("H:mm:ss").format(calendar.getTime()));
                        break;
                    case "час":
                        sendTextMessage(info + new SimpleDateFormat("H").format(calendar.getTime()));
                        break;
                    case "минуты":
                        sendTextMessage(info + new SimpleDateFormat("m").format(calendar.getTime()));
                        break;
                    case "секунды":
                        sendTextMessage(info + new SimpleDateFormat("s").format(calendar.getTime()));
                        break;
                }
            }
        }
    }
}