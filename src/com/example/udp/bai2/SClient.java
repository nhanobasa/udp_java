package com.example.udp.bai2;

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

    private static void tachChuoi(SClient client) {
        String str = client.receive();
        System.out.println("Client receive :" + str);
        String[] arrs = str.split(";");
        str = arrs[1];
        StringBuilder result1 = new StringBuilder();
        StringBuilder result2 = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            int ascii = (int) str.charAt(i);
            if ((48 <= ascii && ascii <= 57) || (65 <= ascii && ascii <= 90) || (97 <= ascii && ascii <= 122)) {
                result1.append(str.charAt(i));
            } else {
                result2.append(str.charAt(i));
            }
        }

        String result = arrs[0] + ";" + result1.toString() + "," + result2.toString();
        client.send(result);
    }

    public void close(){
        socket.close();
    }

    public static void main(String[] args) {
        SClient client = new SClient();
        client.send(";B16DCCN284;100");
        tachChuoi(client);
        client.close();
    }
}
