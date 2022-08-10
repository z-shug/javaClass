/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import dto.DVD;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author zshug
 */
public class DVDLibraryView {
    UserIO userIO = new UserIOConsoleImpl();  

    public void startMessage() {
        userIO.print("Welcome to the DVD Library!");
        
    }
    

    
    public int initialMenu() {
        userIO.print("Initial Menu:");
        int userChoice = userIO.readInt("\tPlease select the action you wish to perform: \n\t\t1.Add a DVD to the Collection \n\t\t2.Delete a DVD from the Collection \n\t\t3.List DVDs in Collection \n\t\t4.Search for DVD by Title\n\t\t5.Exit Program");
        return userChoice;
    }
    
    //Return DVD
    public DVD addDVDMenu() {
        userIO.print("Add DVD Menu:");
        DVD dvd = new DVD();
        
        dvd.setTitle(userIO.readString("\tPlease Enter DVD Title: "));
        dvd.setReleaseDate(userIO.readString("\tPlease Enter DVD Release Date: "));
        dvd.setRating(userIO.readString("\tPlease Enter DVD MPAA Rating: "));
        dvd.setDirectorName(userIO.readString("\tPlease Enter DVD Director's Name: "));
        dvd.setReleaseDate(userIO.readString("\tPlease Enter DVD Release Date: "));  
        dvd.setUserRating(userIO.readString("\tPlease Enter Personal Rating or Comment on DVD: "));
        userIO.print("Thank you for adding a DVD!");
        return dvd;
    }
    
    public String deleteDVDMenu() {
        String userInput = userIO.readString("Delete DVD Menu: \n\tPlease enter DVD title to delete:");
        userIO.print(userInput +" was deleted.");
        return userInput;
    }
    
    public String searchDVD(){
        userIO.print("Search For DVD Menu: \n\t What DVD do you want to search for?");
        String dvdName = userIO.readString("\t\tEnter DVD Title:");
        return dvdName;
    }
    
    public void listDVDInfo(DVD dvd){
        userIO.print("The information for " +dvd.getTitle() +" are:" );
        userIO.print("\tRelease Date: " + dvd.getReleaseDate() +"\n\tRating: " + dvd.getRating()+"\n\tDirector Name: "+ dvd.getDirectorName() +"\n\t Studio: " + dvd.getStudio() + "\n\t User Rating or Notes: " + dvd.getUserRating());
    }
    
    public int listInfoReturnToMenu(){
        userIO.print("What would you like to do?");
        int userChoice = userIO.readInt("Press 1 to go to Main Menu \nPress 2 to Edit Info \n Press 3 to Exit");
        return userChoice;
    }
    
    public int returnToMenu(){
        userIO.print("What would you like to do?"); 
        int userChoice = userIO.readInt("Press 1 to go to Main Menu \nPress 2 to Exit");
        return userChoice;
    }
    
    public int editInfoMenu(){
        userIO.print("Edit DVD Info Menu:");
        int userInput = userIO.readInt("Slect what you would like to edit:\n\t1.Release Date\n\t2.MPAA Rating\n\t3.Director's Name\n\t4.Studio\n\t5.User Rating or Note");
        return userInput;        
    }
    
    public String editInfoInput(String name){
       userIO.print("You want to edit the " + name + ":");
       String userInput = userIO.readString("Please enter the new value for"+ name+ ":"); 
       return userInput;
    }
    

        
    public int editInfoReturnToMenu(String dvd){
        userIO.print("The info for "+ dvd + " was succesfully updated!");
        userIO.print("What would you like to do?"); 
        int userChoice = userIO.readInt("Press 1 to go to Main Menu \nPress 2 to Edit Something Else \nPress 3 to Exit");
        return userChoice;
    }
    
    public int foundDVDMenu(String title) {
        userIO.print(title + " was found!");
        int userInput = userIO.readInt("\tPlease select what you would like to do:\n\t\t1.Display Info\n\t\t2.Edit Info\n\t\t3.Delete DVD\n\t\t4.Return to Main Menu");
        return userInput;
    }
    
    public int notFoundDVDMenu(String title){
        userIO.print(title + " was not found!");
        int userInput = userIO.readInt("\tPlease select what you would like to do:\n\t\t1.Add DVD to Library\n\t\t2.Return to Main Menu");
        return userInput;
    }
    
    public void listDVDMenu (HashMap<String,DVD> hashMap){
        userIO.print("The DVDs in the library are:");
        Set<String> keys = hashMap.keySet();
        for (String k : keys) {
            userIO.print(k);
        }
        
    }
    
    
    
    
    
}
