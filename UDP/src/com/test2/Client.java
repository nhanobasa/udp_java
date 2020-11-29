/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author luongtx
 */
public class Client {
    DatagramSocket socket;
    private String serverAddress = "localhost";
    private int serverPort = 1109;
    public Client(){
       try{
          socket = new DatagramSocket();
       }catch(Exception ex){
           ex.printStackTrace();
       }
    }
    public void sendSV(Student std){
        ObjectOutputStream oos = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(std);
            byte[] buf = baos.toByteArray();
            InetAddress inet = InetAddress.getByName(serverAddress);
            DatagramPacket sendPk = new DatagramPacket(buf, buf.length, inet, serverPort);
            socket.send(sendPk);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public Student receiveSV(){
        Student std = null;
        try{
            byte[] buf = new byte[1024];
            DatagramPacket receivePk = new DatagramPacket(buf, buf.length);
            socket.receive(receivePk);
            ByteArrayInputStream bais = new ByteArrayInputStream(buf);
            ObjectInputStream ois = new ObjectInputStream(bais);
            std = (Student) ois.readObject();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return std;
    }
    public void close(){
        socket.close();
    }
    public static void main(String[] args) {
        Client clt = new Client();
        Student std = new Student("B16DCAT100");
        clt.sendSV(std);
        std = clt.receiveSV();
        System.out.println(std.getGpa());
        float gpa = std.getGpa();
        if(gpa>=3.7){
            std.setGpaLetter("A");
        }else if(gpa>=3.0){
            std.setGpaLetter("B");
        }else if(gpa>=2.0){
            std.setGpaLetter("C");
        }else if(gpa>=1.0){
            std.setGpaLetter("D");
        }else {
            std.setGpaLetter("F");
        }
        clt.sendSV(std);
        clt.close();
    }
}
