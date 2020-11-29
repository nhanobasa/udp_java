package com.example.udp.bai3;

import UDP.Student;
import com.example.utils.IOFileUtil;

import java.io.IOException;
import java.net.*;

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
            Student student = receive();
            System.out.println("Server receive : " + student.getCode());
            student.setId(12345);
            student.setGpa(3.7f);
            send(student);
            student = receive();
            System.out.println("Server receive :" + student.getGpa() + "-" + student.getGpaLetter());
        }
    }

    public void send(Student student) {
        sendData = IOFileUtil.objectToBytes(student);
        try {
            sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getSocketAddress());
            socket.send(sendPacket);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Student receive() {
        receiveData = new byte[1024];
        receivePacket = new DatagramPacket(receiveData, receiveData.length);
        try {
            socket.receive(receivePacket);
            Student student = IOFileUtil.bytesToObject(receivePacket.getData());
            return student;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        SServer server = new SServer();
        server.listening();
    }
}
