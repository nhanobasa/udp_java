/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptb2;

import java.io.Serializable;

/**
 *
 * @author luongtx
 */
public class Equation implements Serializable{
    float a,b,c;
    public Equation (float a, float b, float c){
        this.a = a;
        this.b = b;
        this.c = c;
    }
    public String solution(){
        String ans = "";
        float delta = b*b - 4*a*c;
        if(delta>0){
            float x1 = (float) (-b+Math.sqrt(delta))/(2*a);
            float x2 = (float) (-b-Math.sqrt(delta))/(2*a);
            ans = String.format("Phuong trinh co 2 nghiem phan biet: \nx1=%f \nx2=%f", x1,x2);
        }else if(delta==0){
            float x = (float) (-b)/(2*a);
            ans = String.format("Phuong trinh co 1 nghiem: x=%f", x);
        }else ans = "Phuong trinh vo nghiem";
        return ans;
    }

    public float getA() {
        return a;
    }

    public float getB() {
        return b;
    }

    public float getC() {
        return c;
    }
}
