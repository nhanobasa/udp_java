package com.example.udp.bai1;

import java.io.IOException;
import java.net.*;

public class SClient {
    private DatagramSocket socket;
    private DatagramPacket sendPacket;
    private DatagramPacket receivePacket;
    private byte[] sendData;
    private byte[] receiveData;

    public SClient() {
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void send(String str) {
        sendData = str.getBytes();
        try {
            sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("localhost"), 8080);
            socket.send(sendPacket);
        } catch (UnknownHostException e) {
            e.printStackTrace();
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

    private static int ucln(int a, int b) {
        while (a != b) {
            if (a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
        }
        return a;
    }

    private void close(){
        socket.close();
    }

    public static void main(String[] args) {
        SClient client = new SClient();
        client.send(";B16DCCN284;100");
        String str = client.receive();
        System.out.println(str);
        String[] arrs = str.split(";");
        String[] arrss = arrs[1].split(",");
        int a = Integer.parseInt(arrss[0]);
        int b = Integer.parseInt(arrss[1]);
        int ucln = ucln(a, b);
        int bcnn = (a * b) / ucln;
        int sum = a + b;
        int mul = a * b;
        String result = arrs[0] + ";" + ucln + "," + bcnn + "," + sum + "," + mul;
        client.send(result);
        client.close();
    }
}
