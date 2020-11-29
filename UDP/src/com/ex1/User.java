/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ex1;

import java.io.Serializable;

/**
 *
 * @author luongtx
 */
public class User implements Serializable{
    private String username;
    private String password;
    private int win;
    private int lose;
    private int score;
    private boolean login = false;
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        win = 0;
        lose = 0;
        score = 0;
    }

    public User(String username, String password, int win, int lose, int score) {
        this.username = username;
        this.password = password;
        this.win = win;
        this.lose = lose;
        this.score = score;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setLogin(boolean login){
        this.login = login;
    }
    
    public boolean isLogin(){
        return login;
    }
    
    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getLose() {
        return lose;
    }

    public void setLose(int lose) {
        this.lose = lose;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
