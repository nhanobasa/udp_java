/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ex1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author luongtx
 */
public class UDPServer {
    DatagramSocket socket;
    DatagramPacket sendPk, receivePk;
    byte[] send_buff, receive_buff;
    int port;
    public UDPServer(){
        try{
            port = 8888;
            socket = new DatagramSocket(8888);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void listening(){
        while(true){
            try{
                receive_buff = new byte[1024];
                receivePk = new DatagramPacket(receive_buff, receive_buff.length);
                socket.receive(receivePk);
                //dao chuoi
                String rev = new String(receivePk.getData());
                String ans = new StringBuilder(rev).reverse().toString();
                //gui chuoi
                send_buff = ans.getBytes();
                sendPk = new DatagramPacket(send_buff, send_buff.length, receivePk.getSocketAddress());
                socket.send(sendPk);
                
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
