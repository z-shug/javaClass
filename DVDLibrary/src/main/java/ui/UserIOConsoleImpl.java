/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

/**
 *
 * @author zshug
 */
import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO  {
    

    
    @Override        
    public void print(String message){
        System.out.println(message);
    }

    @Override
    public String readString(String prompt){
        System.out.println(prompt);
        Scanner myScanner = new Scanner(System.in);
        String userInput = myScanner.nextLine();
        return userInput;
        
    }

    @Override
    public int readInt(String prompt){
        System.out.println(prompt);
        Scanner myScanner = new Scanner(System.in);
        String userInput = myScanner.nextLine();
        int number = Integer.parseInt(userInput);
        return number;
    }
    
    
}
