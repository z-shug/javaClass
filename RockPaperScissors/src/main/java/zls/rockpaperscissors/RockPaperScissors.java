/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zls.rockpaperscissors;

/**
 *
 * @author zshug
 */
import java.util.Random;
import java.util.Scanner;
import java.lang.*;
public class RockPaperScissors {
    
    
    public static void main(String[] args){
        
        
        
        System.out.println("Welcome to Rock Paper Scissors!"); 
        int number = RockPaperScissors.numberGames();
        RockPaperScissors.playGame(number);
        
        
    }
    
    public static void playGame(int numberGames){
        
        int userWins = 0;
        int ties = 0;
        int computerWins = 0;
        
        
        for(int i= numberGames; i > 0 ; i--){
     
            int choice = RockPaperScissors.userChoice();
            int computerChoice = RockPaperScissors.computerChoice() +1;
            System.out.println("the computer chose " + computerChoice);
            if(choice == computerChoice){
                System.out.println("It's a Draw!");
                ties++;
            }  else if (choice == 1 && computerChoice == 2){
                System.out.println("Computer Wins!");
                computerWins++;
            } else if ( choice == 1 && computerChoice == 3){
                System.out.println("You Win!");
                userWins++;
            } else if ( choice == 2 && computerChoice == 1){
                System.out.println("You Win!");
                userWins++;
            } else if ( choice == 2 && computerChoice == 3){
                System.out.println("Computer Wins!");
                computerWins++;
            } else if (choice == 3 && computerChoice == 2) {
                System.out.println("You Win!");
                userWins++;
            } else {
                System.out.println("Computer Wins!");
                computerWins++;
            }
        }
        
        System.out.println("Game over!");
        
        if(userWins > computerWins){
            System.out.println("You Win! User Wins: " + userWins + " Computer Wins: " + computerWins + " Ties: " + ties );
        } else if ( userWins < computerWins){
          System.out.println("Sorry You Loose! User Wins: " + userWins + " Computer Wins: " + computerWins + " Ties: " + ties );
        } else {
            System.out.println("It's a Tie! User Wins: " + userWins + " Computer Wins: " + computerWins + " Ties: " + ties );
        }
        RockPaperScissors.playAgain();
    }
    
    
    public static int numberGames(){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("How many games would you like to play between 1 and 10?");
        String input = sc.nextLine();
        int number = Integer.parseInt(input);
        
        if ( number <= 0 || number > 10){
            System.out.println("Error: Please restart game and enter a valid game number range between 1 and 10.");
            System.exit(0);
        }
        return number;
    }
    
    public static int computerChoice(){
       Random rng = new Random();
       int computerChoice = rng.nextInt(3);
       return computerChoice;
       
    }
    
    public static int userChoice(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose 1, 2, or 3. 1=Rock, 2 =Paper, 3=Scissors.");
        String input = sc.nextLine();
        int choice = Integer.parseInt(input);
        return choice;
    }
    
    public static void playAgain(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Want to play again? Answer 1 for Yes or 2 for No.");
        String input = sc.nextLine();
        int answer = Integer.parseInt(input);
        if(answer == 1){
            int number = RockPaperScissors.numberGames();
            RockPaperScissors.playGame(number);
        } else {
            System.out.println("Thank you for playing! See you again soon!");
            System.exit(0);
        }
    }
    
    
}