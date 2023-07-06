/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package carManage;

import java.util.Scanner;

/**
 *
 * @author hoaht
 */
public class Inputter {
    private static Scanner sc = new Scanner(System.in);

    public Inputter() {
    }
    
    public static String inputNonBlankStr(String msg){
        String data;
        do{
            System.out.print(msg);
            data = sc.nextLine();
        }while(data.length()==0);
        
        return data.trim();
    }
    
    public static Double inputDouble(String msg){
        double data = -1;
        do{
            try {
                System.out.print(msg);
                data = Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("This value is invalid!");
            }
        }while(data < 0);
        
        return data;
    }
    
    public static String inputStr(String msg){
        System.out.print(msg);
        String data = sc.nextLine();
        return data.trim();
    }
    
    public static String inputStrPattern(String msg, String pattern){
        String data;
        do{
            System.out.print(msg);
            data = sc.nextLine().trim();
        }while(!data.matches(pattern));
        
        return data;
    }
}
