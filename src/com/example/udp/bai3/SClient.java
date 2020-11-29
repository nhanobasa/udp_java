package com.example.udp.bai3;

import UDP.Student;
import com.example.utils.IOFileUtil;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

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

    public void send(Student student) {
        sendData = IOFileUtil.objectToBytes(student);
        try {
            sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("localhost"), 8080);
            socket.send(sendPacket);
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

    private static String getGpaLetter(float gpa) {
        if (3.7 <= gpa && gpa <= 4) {
            return "A";
        }
        if (3.0 <= gpa && gpa <= 3.7) {
            return "B";
        }
        if (2.0 <= gpa && gpa <= 3.0) {
            return "C";
        }
        if (1.0 <= gpa && gpa <= 2.0) {
            return "D";
        }
        if (0 <= gpa && gpa <= 1.0) {
            return "F";
        }
        return null;
    }

    public void close() {
        socket.close();
    }

    public static void main(String[] args) {
        SClient client = new SClient();
        Student student = new Student("B16DCCN284");
        client.send(student);
        student = client.receive();
        System.out.println("Client receive :" + student.getGpa());
        student.setGpaLetter(getGpaLetter(student.getGpa()));
        client.send(student);
        client.close();
    }
}
