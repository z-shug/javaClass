/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.DVD;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author zshug
 */
public interface DVDLibraryDao {
    

    public void addDVD(DVD dvd)throws DVDLibraryPersistenceException;
    
    public void deleteDVD(String title)throws DVDLibraryPersistenceException;
   
    public boolean search(String title)throws DVDLibraryPersistenceException;
    
    public DVD listInfo(String title)throws DVDLibraryPersistenceException;
    
    public void editInfo(String name, int number, String newValue)throws DVDLibraryPersistenceException;
    
    public HashMap<String,DVD> getAllDVDS()throws DVDLibraryPersistenceException ;
    
    
}
