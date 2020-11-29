package com.example.udp.bai1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class SServer {

    private DatagramSocket socket;
    private DatagramPacket sendPacket;
    private DatagramPacket receivePacket;
    private byte[] sendData;
    private byte[] receiveData;

    public SServer() {
        try {
            System.out.println("Open server ...");
            socket = new DatagramSocket(8080);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void listening() {
        System.out.println("Listening ...");
        while (true) {
            String str = receive();
            System.out.println("Server receive : " + str);
            int a = (int) (Math.random() * 100);
            int b = (int) (Math.random() * 100);
            str = "requestId;" + a + "," + b;
            send(str);
            str = receive();
            System.out.println(str);
        }
    }

    public void send(String str) {
        sendData = str.getBytes();
        sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getSocketAddress());
        try {
            socket.send(sendPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String receive() {
        receiveData = new byte[1024];
        receivePacket = new DatagramPacket(receiveData, receiveData.length);
        try {
            socket.receive(receivePacket);
            return new String(receivePacket.getData()).trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void close() {
        socket.close();
    }

    public static void main(String[] args) {
        SServer server = new SServer();
        server.listening();
    }
}
