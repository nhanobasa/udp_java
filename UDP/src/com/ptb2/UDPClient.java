/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptb2;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author luongtx
 */
public class UDPClient {
    DatagramSocket socket;
    DatagramPacket sendPk, receivePk;
    public UDPClient() {
        try {
            socket = new DatagramSocket();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void send(Equation e){
       try{
           //tạo output stream chứa mảng byte
           ByteArrayOutputStream bout = new ByteArrayOutputStream();
           //tạo ObjectOutputStream để ghi tới một outputstream nhất định
           ObjectOutputStream oos = new ObjectOutputStream(bout);
           //ghi đối tượng vào ObjectOutputStream
           oos.writeObject(e);
           // tạo một mảng byte được cấp phát mới
           byte[] send_buf = bout.toByteArray();
           InetAddress address = InetAddress.getByName("localhost");
           sendPk = new DatagramPacket(send_buf,send_buf.length,address,8888);
           socket.send(sendPk);
       }catch(IOException ex){
           ex.printStackTrace();
       }
    }
    public void receive(){
        try{
            byte[] receive_buf = new byte[1024];
            receivePk = new DatagramPacket(receive_buf,receive_buf.length);
            socket.receive(receivePk);
            String rs = new String(receivePk.getData());
            if(rs.contains("error")) {
                System.out.println("Nhap he so a khac 0");
                send(readInput());
                receive();
            }
            else System.out.println(rs);
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    public Equation readInput(){
        Scanner in = new Scanner(System.in);
        System.out.println("Nhap 3 he so a,b,c:");
        float a,b,c;
        a = in.nextFloat(); b = in.nextFloat(); c = in.nextFloat();
        Equation eq = new Equation(a,b,c);
        return eq;
    }
}
