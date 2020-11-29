/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.review;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author luongtx
 */
public class Server {
    public static void main(String[] args) {
        try{
            DatagramSocket socket = new DatagramSocket(8888);
            byte [] buff = new byte[1024];
//            System.out.println(buff.length);
            DatagramPacket packet = new DatagramPacket(buff, buff.length);
            socket.receive(packet);
            String receiveStr = new String(packet.getData());
//            int a = new Integer(receiveStr);
            System.out.println(receiveStr);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
    }
}
