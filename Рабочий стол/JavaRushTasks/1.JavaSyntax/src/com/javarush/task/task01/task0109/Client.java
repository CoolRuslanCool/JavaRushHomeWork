package com.javarush.task.task01.task0109;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by ruslan on 17.02.17.
 */
public class Client {
    public static void main(String[] args) {
        Thread.currentThread().isInterrupted();
        int port = 3333;
        String ip = "192.168.0.1";
        InetAddress ipAdress = null;
        try {
            ipAdress = InetAddress.getLocalHost();
            System.out.println("ip adress: " + ipAdress);
        } catch (UnknownHostException e) {
            System.out.println("ip Wrong");
        }

        try (
            Socket socket = new Socket(ipAdress, port);
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))
            ) {
            while (true) {
                System.out.println("client out will bee");
                dataOutputStream.writeUTF(reader.readLine());
                dataOutputStream.flush();
                System.out.println("client in will be");
                System.out.println(dataInputStream.readUTF());
            }
        } catch (Exception e) {
            System.out.println("what wrong");
        }
    }
}
