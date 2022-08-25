/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DVDLibraryPersistenceException;
import dto.DVD;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import service.DVDLibraryDataValidationException;
import service.DVDLibraryDuplicateTitleException;
import service.DVDLibraryServiceLayer;
import service.DVDLibraryServiceLayerImpl;
import ui.DVDLibraryView;

/**
 *
 * @author zshug
 */
public class DVDLibraryController {
    private DVDLibraryServiceLayer service;
    private DVDLibraryView view;
    
    public DVDLibraryController(DVDLibraryServiceLayer service ,DVDLibraryView view ){
        this.service = service;
        this.view = view;
    }
    
    public void start() throws DVDLibraryPersistenceException, DVDLibraryDuplicateTitleException, DVDLibraryDataValidationException{
        
        
        try{        
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
                    default:
                        unknownCommand();

                }       
            }
        }catch(DVDLibraryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
         
    
    public void startMessage(){
        view.startMessage();
    }
    
    public void addDVD() throws DVDLibraryPersistenceException, DVDLibraryDuplicateTitleException, DVDLibraryDataValidationException{       
        boolean hasErrors = false;
        do{
          DVD newDVDInfo = view.addDVDMenu(); 
          try{
             service.addDVD(newDVDInfo); 
             hasErrors = false;
          } catch (DVDLibraryDuplicateTitleException | DVDLibraryDataValidationException e){
              hasErrors = true;
              view.displayErrorMessage(e.getMessage());
          }
        }while (hasErrors);   
        returnToMenu();   
    }
    
    public void deleteDVD() throws DVDLibraryPersistenceException{
        String movieDeletion = view.deleteDVDMenu();
        service.deleteDVD(movieDeletion);
        returnToMenu();   
    }
    
    public void listDVD() throws DVDLibraryPersistenceException{   
        HashMap<String,DVD> dvdList = service.getAllDVDS();
        view.listDVDMenu(dvdList);
        returnToMenu();
    }
    
    
    
    public void searchDVD() throws DVDLibraryPersistenceException, DVDLibraryDuplicateTitleException, DVDLibraryDataValidationException{
        String searchDVD = view.searchDVD();
        boolean contains = service.searchDVD(searchDVD);
        if (contains == true){
            foundDVD(searchDVD);
        } else {
             notFoundDVD(searchDVD);
        }
        
    }

    public void foundDVD(String searchDVD) throws DVDLibraryPersistenceException, DVDLibraryDuplicateTitleException, DVDLibraryDataValidationException{
        int userInput = view.foundDVDMenu(searchDVD);
            switch (userInput){
                case 1:
                    listInfo(searchDVD);
                    break;
                case 2:
                    editInfo(searchDVD);
                    break;
                case 3:
                    service.deleteDVD(searchDVD);
                    returnToMenu();
                    break;
                case 4:
                    break;                 
            }
    }
    
    public void editInfo(String searchDVD) throws DVDLibraryPersistenceException, DVDLibraryDuplicateTitleException, DVDLibraryDataValidationException{
        int userChoice = view.editInfoMenu();
        String userEntry = view.editInfoInput(searchDVD);                   
        service.editInfo(searchDVD, userChoice, userEntry);
        editInfoReturnMenu(searchDVD);
    }
    
    public void listInfo(String searchDVD) throws DVDLibraryPersistenceException, DVDLibraryDuplicateTitleException, DVDLibraryDataValidationException{
        DVD dvdObject = service.listInfo(searchDVD);
        view.listDVDInfo(dvdObject);
        listInfoReturnToMenu(searchDVD);
    }
    
    public void notFoundDVD(String searchDVD) throws DVDLibraryPersistenceException, DVDLibraryDuplicateTitleException, DVDLibraryDataValidationException{
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
    
    public void listInfoReturnToMenu(String searchDVD) throws DVDLibraryPersistenceException, DVDLibraryDuplicateTitleException, DVDLibraryDataValidationException{
        int userChoice = view.listInfoReturnToMenu();
        switch (userChoice){
           case 1: 
               start();
               break;
           case 2:
               editInfo(searchDVD);
           case 3:
               System.exit(0);
               break;
       }
    }
    public void editInfoReturnMenu( String searchDVD) throws DVDLibraryPersistenceException, DVDLibraryDuplicateTitleException, DVDLibraryDataValidationException{
        int secondUserChoice = view.editInfoReturnToMenu(searchDVD);
        switch (secondUserChoice){
            case 1:
                start();
                break;
            case 2:
                editInfo(searchDVD);
                break;
            case 3: 
                System.exit(0);
                break;
        }
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
         
 }
    
    

