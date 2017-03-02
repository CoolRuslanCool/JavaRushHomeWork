package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ruslan on 28.02.17.
 */
public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        int port = ConsoleHelper.readInt();
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            ConsoleHelper.writeMessage("Server is started");
            while (true) {
                Socket socket = serverSocket.accept();
                new Handler(socket).start();
            }
        } catch (IOException e) {
            try {
                serverSocket.close();
            } catch (IOException ignored) {/*NOP*/
            }
            System.out.println(e.getMessage());
        }
    }


    public static void sendBroadcastMessage(Message message) {
        for (Map.Entry<String, Connection> connectionEntry : connectionMap.entrySet()) {
            try {
                connectionEntry.getValue().send(message);
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Not sending message");
            }
        }
    }

    private static class Handler extends Thread {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            String name = null;
            ConsoleHelper.writeMessage("New connection: " + socket.getRemoteSocketAddress());

            try (Connection connection = new Connection(socket)) {
                name = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, name));
                sendListOfUsers(connection, name);
                serverMainLoop(connection, name);
            } catch (IOException | ClassNotFoundException e) {
                ConsoleHelper.writeMessage("Server error");
            } finally {
                if (name != null) {
                    connectionMap.remove(name);
                    sendBroadcastMessage(new Message(MessageType.USER_REMOVED, name));
                }
                ConsoleHelper.writeMessage("Connection closed");
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            Message message;
            while (true) {
                message = connection.receive();
                if (message.getType().equals(MessageType.TEXT)) {
                    sendBroadcastMessage(new Message(MessageType.TEXT, userName.concat(": ").concat(message.getData())));
                } else ConsoleHelper.writeMessage("Message error");
            }
        }

        private void sendListOfUsers(Connection connection, String userName) throws IOException {
            for (Map.Entry<String, Connection> pair : connectionMap.entrySet())
                if (!userName.equals(pair.getKey()))
                    connection.send(new Message(MessageType.USER_ADDED, pair.getKey()));
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            Message message;
            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                message = connection.receive();
                if (message.getType().equals(MessageType.USER_NAME)) {
                    if (message.getData() != null) {
                        if (!message.getData().trim().isEmpty()) {
                            if (!connectionMap.containsKey(message.getData())) {
                                connectionMap.put(message.getData(), connection);
                                connection.send(new Message(MessageType.NAME_ACCEPTED));
                                return message.getData();
                            }
                        }
                    }
                }
            }
        }
    }
}