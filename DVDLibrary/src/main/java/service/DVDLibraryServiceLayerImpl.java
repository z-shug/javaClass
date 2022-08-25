/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.DVDLibraryDao;
import dao.DVDLibraryPersistenceException;
import dto.DVD;
import java.util.HashMap;

/**
 *
 * @author zshug
 */
public class DVDLibraryServiceLayerImpl implements DVDLibraryServiceLayer {

    DVDLibraryDao dao;
    
    public DVDLibraryServiceLayerImpl(DVDLibraryDao dao){
        this.dao = dao;
    }
    
    
    @Override
    public void addDVD(DVD dvd) throws DVDLibraryDuplicateTitleException, DVDLibraryDataValidationException, DVDLibraryPersistenceException {
        if (dao.search(dvd.getTitle()) != false) {
        throw new DVDLibraryDuplicateTitleException(
                "ERROR: Could not create DVD. "
                + dvd.getTitle()
                + " already exists");
        }
        validateDVDData(dvd);
        dao.addDVD(dvd);
    }

    @Override
    public void deleteDVD(String dvdTitle) throws DVDLibraryPersistenceException {
        dao.deleteDVD(dvdTitle);
    }
    
    @Override
    public boolean searchDVD(String dvdTitle) throws DVDLibraryPersistenceException {
        return dao.search(dvdTitle);
    }
    

    @Override
    public DVD listInfo(String title) throws DVDLibraryPersistenceException {
        return dao.listInfo(title);
    }

    @Override
    public void editInfo(String title, int number, String newValue) throws DVDLibraryPersistenceException {
        dao.editInfo(title, number, newValue);
    }
    
    @Override
    public HashMap<String, DVD> getAllDVDS() throws DVDLibraryPersistenceException {
       return dao.getAllDVDS();
    }
    
    private void validateDVDData(DVD dvd) throws
        DVDLibraryDataValidationException {

        if (dvd.getTitle() == null
                || dvd.getTitle().trim().length() == 0
                || dvd.getRating() == null
                || dvd.getRating().trim().length() == 0
                || dvd.getDirectorName()== null
                || dvd.getDirectorName().trim().length() == 0
                || dvd.getReleaseDate() == null
                ||dvd.getReleaseDate().toString().trim().length() == 0
                ||dvd.getStudio() == null
                ||dvd.getStudio().trim().length() == 0        
                ||dvd.getUserRating() == null
                ||dvd.getUserRating().trim().length() == 0){

            throw new DVDLibraryDataValidationException(
                    "ERROR: All fields [Title, Rating, Studio, ReleaseDate, UserRating] are required.");
        }
    }
}
