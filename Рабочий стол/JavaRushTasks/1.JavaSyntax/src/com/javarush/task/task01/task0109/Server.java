package com.javarush.task.task01.task0109;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by ruslan on 17.02.17.
 */
public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(3333);
             Socket socket = serverSocket.accept();
             DataInputStream inputStream = new DataInputStream(socket.getInputStream());
             DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream())
        ) {
            System.out.println(socket.getLocalSocketAddress());
            while (true) {
                System.out.println("server in will be:");
                System.out.println(inputStream.readUTF());
                System.out.println("server out will be");
                outputStream.writeUTF("Server");
                outputStream.flush();
            }
        } catch (Exception e) {
            System.out.println("what wrong");
        }
    }
}
