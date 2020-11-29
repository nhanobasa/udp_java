/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptb2;

/**
 *
 * @author luongtx
 */
public class ServerRUn {
    public static void main(String[] args) {
        UDPServer server = new UDPServer();
        server.listening();
    }
}
