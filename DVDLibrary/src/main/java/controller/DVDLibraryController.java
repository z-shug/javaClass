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
        HashMap<String, DVD> dvdMap = new HashMap<String,DVD>();
        dvdMap = dao.loadFromFile();
        view.startMessage();
        int menuChoice; 
        menuChoice = view.initialMenu();
        if(menuChoice == 1){
            ArrayList<String> newDVDInfo = new ArrayList<String>();
            newDVDInfo = view.addDVDMenu(); 
            DVD newDVD = dao.addDVD(newDVDInfo);
            dvdMap.put(newDVD.getTitle(), newDVD);
            dao.saveToFile(dvdMap);
        } else if (menuChoice == 4){
            view.listDVDMenu(dvdMap);
        }
        
    }
    
    
}
