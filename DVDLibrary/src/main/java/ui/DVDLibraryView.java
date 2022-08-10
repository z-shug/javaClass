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
        int userChoice = userIO.readInt("\tPlease select the action you wish to perform: \n\t\t1.Add a DVD to the Collection \n\t\t2.Delete a DVD from the Collection \n\t\t4.List DVDs in Collection \n\t\t5.Search for DVD by Title");
        return userChoice;
    }
    
    public ArrayList addDVDMenu() {
        userIO.print("Add DVD Menu:");
        ArrayList<String> dvd = new ArrayList<String>();
        dvd.add(userIO.readString("\tPlease Enter DVD Title: "));
        dvd.add(userIO.readString("\tPlease Enter DVD Release Date: "));
        dvd.add(userIO.readString("\tPlease Enter DVD MPAA Rating: "));
        dvd.add(userIO.readString("\tPlease Enter DVD Director's Name: "));
        dvd.add(userIO.readString("\tPlease Enter DVD Release Date: "));  
        dvd.add(userIO.readString("\tPlease Enter Personal Rating or Comment on DVD: "));         
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
    
    public int returnToMenu(String message){
        userIO.print(message);
        int userChoice = userIO.readInt("Press 1 to go to Main Menu \nPress 2 to Exit.");
        return userChoice;
    }
    
    public int editInfoMenu(){
        userIO.print("Edit DVD Info Menu:");
        int userInput = userIO.readInt("Slect what you would like to edit:\n\t1.Title\n\t2.Release Date\n\t3.MPAA Rating\n\t4.Director's Name\n\t5.Studio\n\t6.User Rating or Note");
        return userInput;
        
    }
    
    public int foundDVDMenu(String title) {
        userIO.print(title + " was found!");
        int userInput = userIO.readInt("\tPlease select what you would like to do:\n\t\t1.Display Info\n\t\t2.Edit Info\n\t\t3.Delete DVD\n\t\t4.Return to Main Menu");
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
