/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author luongtx
 */
public class Client {
    DatagramSocket udpSocket;
    static InetAddress serverAddress;
    static int serverPort;
    public Client(){
        try {
            udpSocket = new DatagramSocket();
            serverAddress = InetAddress.getByName("localhost");
            serverPort = 1107;
        } catch (Exception ex) {

        }
    }
    public void close(){
        udpSocket.close();
    }
    public void sendData(String str){
        try{
            byte[] send_buf = str.getBytes();
            DatagramPacket sendpk = new DatagramPacket(send_buf, send_buf.length, serverAddress, serverPort);
            udpSocket.send(sendpk);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public String receiveData(){
        String str = "";
        try{
            byte[] receive_buf = new byte[1024];
            DatagramPacket receivepk = new DatagramPacket(receive_buf, receive_buf.length);
            udpSocket.receive(receivepk);
            str = new String(receivepk.getData());
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return str;
    }
    public static int ucln(int a, int b){
        while(a!=b){
            if(a>b) a = a-b;
            else b = b-a;
        }
        return a;
    }
    public static void main(String[] args) {
        Client clt = new Client();
        clt.sendData(";B16DCAT100;120");
        String str = clt.receiveData();
        String[] data = str.split("\\W");
        String requestID = data[0];
        int a = Integer.parseInt(data[1]);
        int b = Integer.parseInt(data[2]);
        int gcd = ucln(a, b);
        int lcm = a*b/gcd;
        int sum = a + b;
        int mul = a*b;
        String sendData = requestID +";"+ gcd +";" + lcm + "," + sum + "," + mul;
        clt.sendData(sendData);
        clt.close();
    }
}
