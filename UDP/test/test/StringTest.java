/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author luongtx
 */
public class StringTest {
    public static void main(String[] args) {
        String in = "b16100;1,2";
//        String[] data = a.split("\\W");
//        System.out.println(data[0]);
//        System.out.println(data[1]);
//        System.out.println(data[2]);
        int n = in.length();
        String str1 = "";
        String str2 = "";
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for(int i=0;i<n;i++){
            if(Character.isDigit(in.charAt(i)) || Character.isLetter(in.charAt(i))) 
                sb1.append(in.charAt(i));
            else 
                sb2.append(in.charAt(i));
        }
        System.out.println(sb1);
        System.out.println(sb2);
    }
}
