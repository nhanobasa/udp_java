/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ex1;

/**
 *
 * @author luongtx
 */
public class ClientRun {
    public static void main(String[] args) {
        UDPClient client = new UDPClient();
        client.send("tran");
        client.receive();
    }
}
