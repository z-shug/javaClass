/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zls.factorize;
import java.util.Scanner;
import java.lang.System;
/**
 *
 * @author zshug
 */
public class Factorize {
    
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        System.out.println("What number would you like to factor? ");
        String input = sc.nextLine();
        int number = Integer.parseInt(input);
        System.out.println("You chose " + number);
        
        Factorize.listFactors(number);
        Factorize.isPerfect(number);
        Factorize.isPrime(number);
        }
    
    public static void listFactors(int number){
        int factorCount = 1;
        System.out.println("The factors of " +number+" are:");
        System.out.println("1");
        for(int i=2 ; i <= number; i++){
            if(number % i == 0){
                System.out.println(i);
                factorCount++; 
            }
        } 
        System.out.println(number + " has " + factorCount + " factors");
    } 
    
    public static void isPerfect(int number){
        int checkPrime = 1;
        boolean isPrime;
        
        if(number < 6){
            isPrime = false;
        } else {
            for(int i=2 ; i < number; i++){
            if(number % i == 0){
                checkPrime += i; 
                }
            }
        }  
            
        if(checkPrime == number){
            isPrime = true;  
        } else {
            isPrime = false;
         }
        
        if(isPrime){
            System.out.println(number + " is a perfect number.");
        } else {
            System.out.println(number + " is not a perfect number.");
        }
        
    }
    
    public static void isPrime(int number){
        if(number == 2){
            System.out.println(number + " is a prime number");
        }else if (number <2){
            System.out.println(number + " is not a prime number");
        }
        for (int i=2; i < number;i++){
            if(number % i == 0) {
                System.out.println(number + " is not a prime number");
            } else {
                System.out.println(number + " is a prime number");
                }               
            }
         }
        
  }
    

 
    

