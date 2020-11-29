/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.review;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author luongtx
 */
public class Client {
    public static void main(String[] args) {
        try{
            DatagramSocket socket = new DatagramSocket();
            String s = "Tran Xuan Luong";
            byte [] buff = s.getBytes();
            InetAddress address = InetAddress.getByName("localhost");
            DatagramPacket packet = new DatagramPacket(buff, buff.length,address,8888);
            socket.send(packet);
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
    }
}
