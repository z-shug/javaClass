/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DVDLibraryDaoFileImpl;
import dto.DVD;
import java.util.ArrayList;
import java.util.HashMap;
import ui.DVDLibraryView;

/**
 *
 * @author zshug
 */
public class DVDLibraryController {
    private DVDLibraryDaoFileImpl dao;
    private DVDLibraryView view;
    
    public DVDLibraryController(DVDLibraryDaoFileImpl dao,DVDLibraryView view ){
        this.dao = dao;
        this.view = view;
    }
    
    public void start(){
        view.startMessage();
       
        while(true){
            int menuChoice; 
            menuChoice = view.initialMenu();
            switch (menuChoice) {
                case 1: 
                    addDVD();
                    break;
                case 2 :
                    deleteDVD();
                    break;
                case 3 : 
                    listDVD();
                    break;
                case 4 : 
                    searchDVD();
                    break;
                case 5 :
                    System.exit(0);
                    break;
            }       
        }
    }
         
    
    public void addDVD(){       
        DVD newDVDInfo = view.addDVDMenu(); 
        dao.addDVD(newDVDInfo);
        returnToMenu();   
    }
    
    public void deleteDVD(){
        String movieDeletion = view.deleteDVDMenu();
        dao.deleteDVD(movieDeletion);
        returnToMenu();   
    }
    
    public void listDVD(){
        HashMap<String,DVD> hashMap = dao.loadFromFile();
        view.listDVDMenu(hashMap);
        returnToMenu();
    }
    
    
    
    public void searchDVD(){
        String searchDVD = view.searchDVD();
        boolean contains = dao.search(searchDVD);
        if (contains == true){
            foundDVD(searchDVD);
        } else {
             notFoundDVD(searchDVD);
        }
        
    }

    public void foundDVD(String searchDVD){
        int userInput = view.foundDVDMenu(searchDVD);
            switch (userInput){
                case 1:
                    listInfo(searchDVD);
                    break;
                case 2:
                    editInfo(searchDVD);
                    break;
                case 3:
                    dao.deleteDVD(searchDVD);
                    returnToMenu();
                    break;
                case 4:
                    break;                 
            }
    }
    
    public void editInfo(String searchDVD){
        int userChoice = view.editInfoMenu();
        String userEntry = view.editInfoInput(searchDVD);                   
        dao.editInfo(searchDVD, userChoice, userEntry);
        editInfoReturnMenu(searchDVD);
    }
    
    public void listInfo(String searchDVD){
        DVD dvdObject = dao.listInfo(searchDVD);
        view.listDVDInfo(dvdObject);
        listInfoReturnToMenu(searchDVD);
    }
    
    public void notFoundDVD(String searchDVD){
        int userInput = view.notFoundDVDMenu(searchDVD);
             switch (userInput){
                 case 1:
                     addDVD();
                     break;
                 case 2:
                     break;
             }
    }
    
    public void returnToMenu(){
        int userChoice = view.returnToMenu();
        switch (userChoice){
           case 1: 
               break;
           case 2:
               System.exit(0);
               break;
       }
    }
    
    public void listInfoReturnToMenu(String searchDVD){
        int userChoice = view.listInfoReturnToMenu();
        switch (userChoice){
           case 1: 
               break;
           case 2:
               editInfo(searchDVD);
           case 3:
               System.exit(0);
               break;
       }
    }
    public void editInfoReturnMenu( String searchDVD){
        int secondUserChoice = view.editInfoReturnToMenu(searchDVD);
        switch (secondUserChoice){
            case 1:
                
                break;
            case 2:
                editInfo(searchDVD);
                break;
            case 3: 
                System.exit(0);
                break;
        }
    }
         
 }
    
    

