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
        //check for user input on games
        int number = RockPaperScissors.numberGames();
        //runs the game mechanics
        RockPaperScissors.playGame(number);
        
        
    }
    
    public static void playGame(int numberGames){
        
        //declare game counters
        int userWins = 0;
        int ties = 0;
        int computerWins = 0;
        
        //play game for number of user input
        for(int i= numberGames; i > 0 ; i--){
            
            //ask for user choice
            int choice = RockPaperScissors.userChoice();
            //take random generated computer choice 
            //offset to accomadate for 0 based rng
            int computerChoice = RockPaperScissors.computerChoice() +1;
            System.out.println("the computer chose " + computerChoice);
            
            //determine outcome of rounds and increiment trackers
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
        //determine winner of game
        if(userWins > computerWins){
            System.out.println("You Win! User Wins: " + userWins + " Computer Wins: " + computerWins + " Ties: " + ties );
        } else if ( userWins < computerWins){
          System.out.println("Sorry You Loose! User Wins: " + userWins + " Computer Wins: " + computerWins + " Ties: " + ties );
        } else {
            System.out.println("It's a Tie! User Wins: " + userWins + " Computer Wins: " + computerWins + " Ties: " + ties );
        }
        //check to play again
        RockPaperScissors.playAgain();
    }
    
    
    //method to call for number of games user desires
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
    
    //random generator for computer choice
    public static int computerChoice(){
       Random rng = new Random();
       int computerChoice = rng.nextInt(3);
       return computerChoice;
       
    }
    
    //listens for user choice 
    public static int userChoice(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose 1, 2, or 3. 1=Rock, 2 =Paper, 3=Scissors.");
        String input = sc.nextLine();
        int choice = Integer.parseInt(input);
        return choice;
    }
    
    //checks if user wants to play again or exits program
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