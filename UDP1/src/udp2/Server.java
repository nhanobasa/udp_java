/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udp2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 *
 * @author Duc
 */
public class Server {
    private DatagramSocket socket;
    private DatagramPacket sendPacket;
    private DatagramPacket receivePacket;
    private byte[] sendData;
    private byte[] receiveData;
    
    public Server() {
        try {
            System.out.println("Open server ...");
            socket = new DatagramSocket(8080);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void listening() {
        System.out.println("Listening ...");
        while (true) {
            String str = receive();
            System.out.println("Server receive : " + str);
            str = "requestId;6523ashAsgf72wfbdgsafHJD%6d37&^hh^^%^S=hsjh259&^943039&^%&^";
            send(str);
            str = receive();
            System.out.println("Server receive :" + str);
        }
    }

    public void send(String str) {
        sendData = str.getBytes();
        sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getSocketAddress());
        try {
            socket.send(sendPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String receive() {
        receiveData = new byte[1024];
        receivePacket = new DatagramPacket(receiveData, receiveData.length);
        try {
            socket.receive(receivePacket);
            return new String(receivePacket.getData()).trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.listening();
    }
}
