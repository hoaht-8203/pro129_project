package carManage;


import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hoaht
 */
public class Menu {
    public static int int_getChoice(ArrayList options){
        for (int i=0; i < options.size(); i++){
            System.out.println((i + 1) + ". " + options.get(i));
        }
        
        System.out.print("Enter your choice[1-"+options.size()+"]: ");
        Scanner sc = new Scanner(System.in);
        return Integer.parseInt(sc.nextLine());
    }
    
    public static Object ref_getChoice(ArrayList options){
        Scanner sc = new Scanner(System.in);
        
        for (int i=0; i < options.size(); i++){
            System.out.println((i + 1) + ". " + options.get(i));
        }
        
        int choice = -1;
        do{
            try {
                System.out.print("Enter your choice[1-"+options.size()+"]: ");
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid value!");
            }
        }while(choice < 1 || choice > options.size());
        return options.get(choice-1);
    }

    static int int_getChoice(String[] options) {
        System.out.println("====================================");
        for (int i=0; i < options.length; i++){
            System.out.println((i + 1) + ". " + options[i]);
        }
        System.out.println("====================================");
        
        System.out.print("Enter your choice[1-"+options.length+"]: ");
        Scanner sc = new Scanner(System.in);
        
        return Integer.parseInt(sc.nextLine());
    }
}
