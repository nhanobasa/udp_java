/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ex1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author luongtx
 */
public class UDPClient {
    DatagramSocket socket;
    DatagramPacket sendPk, receivePk;
    byte[] send_buff, receive_buff;
    public UDPClient(){
        try{
            socket = new DatagramSocket();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void send(String str){
        try{
            send_buff = str.getBytes();
            InetAddress address = InetAddress.getByName("localhost");
            sendPk = new DatagramPacket(send_buff, send_buff.length,address,8888);
            socket.send(sendPk);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void receive(){
        try{
            receive_buff = new byte[1024];
            receivePk = new DatagramPacket(receive_buff, receive_buff.length);
            socket.receive(receivePk);
            System.out.println(new String(receivePk.getData()));
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
