/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udp2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Duc
 */
public class Client {

    private static void tachChuoi(Client client) {
        String str = client.receive();
        System.out.println("Client receive:" + str);
        String[] arrs = str.split(";");
        str = arrs[1];
        StringBuilder result1  = new StringBuilder();
        StringBuilder result2 = new StringBuilder();
        for(int i = 0 ; i < str.length(); i++){
            int ascii = (int) str.charAt(i);
            if((48 <= ascii && ascii <= 57)||(65 <= ascii && ascii <= 90 )|| (97 <= ascii && ascii <= 122)){
                result1.append(str.charAt(i));
            }
            else{
                result2.append(str.charAt(i));
            }
        }
        String result = arrs[0] + ";" + result1.toString() + "," + result2.toString();
        client.send(result);
    }
    private DatagramSocket socket;
    private DatagramPacket sendPacket;
    private DatagramPacket receivePacket;
    private byte[] sendData;
    private byte[] receiveData;
    
    public Client(){
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        Client client = new Client();
        client.send(":B16DCAT036;100");
        tachChuoi(client);
        client.close();
    }

    private void send(String str) {
        try {
            sendData = str.getBytes();
            
           sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("localhost"), 8080);
           socket.send(sendPacket);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void close() {
        socket.close();
    }

    private String receive() {
        try {
            receiveData = new byte[1024];
            
            receivePacket = new DatagramPacket( receiveData, receiveData.length);
            socket.receive(receivePacket);
            return new String(receivePacket.getData()).trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
