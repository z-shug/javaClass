/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.DVDLibraryPersistenceException;
import dto.DVD;
import java.util.HashMap;

/**
 *
 * @author zshug
 */
public interface DVDLibraryServiceLayer {
    
    void addDVD(DVD dvd) throws
            DVDLibraryDuplicateTitleException,
            DVDLibraryDataValidationException,
            DVDLibraryPersistenceException;
    
    void deleteDVD(String dvdTitle) throws
            DVDLibraryPersistenceException;
 
    boolean searchDVD(String dvdTitle) throws
            DVDLibraryPersistenceException;
    
    DVD listInfo(String title) throws
            DVDLibraryPersistenceException;
    
    void editInfo(String title, int number, String newValue) throws
            DVDLibraryPersistenceException;
    
    HashMap<String,DVD> getAllDVDS() throws
            DVDLibraryPersistenceException;
 
    
    
    
 

}
