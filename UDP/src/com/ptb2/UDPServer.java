/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptb2;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author luongtx
 */
public class UDPServer {
    DatagramSocket socket;
    DatagramPacket sendPk, receivePk;
    int port;
    public UDPServer(){
        try{
            port = 8888;
            socket = new DatagramSocket(port);
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    public void listening(){
        while (true) {
            try {
                //nhan du lieu
                System.out.println("Đang lắng nghe");
                byte[] receive_buf = new byte[1024];
                receivePk = new DatagramPacket(receive_buf, receive_buf.length);
                socket.receive(receivePk);
                System.out.println("Đã nhận dữ liệu");
                ByteArrayInputStream bais = new ByteArrayInputStream(receive_buf);
                ObjectInputStream ois = new ObjectInputStream(bais);
                Equation eq = (Equation) ois.readObject();

                //gui ket qua
                byte[] send_buf;
                if (eq.getA() == 0) {
                    send_buf = "error".getBytes();
                } else {
                    send_buf = eq.solution().getBytes();
                }
                sendPk = new DatagramPacket(send_buf, send_buf.length, receivePk.getSocketAddress());
                socket.send(sendPk);

            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
    }
}
