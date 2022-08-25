/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.DVD;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author zshug
 */
public class DVDLibraryDaoFileImpl implements DVDLibraryDao {

    private  final String LIBRARY_FILE;
    public static final String DELIMITER = "::";
    private HashMap<String, DVD> dvdMap = new HashMap<>();

    public DVDLibraryDaoFileImpl(){
        LIBRARY_FILE = "library.txt";
        
    }
    
    public DVDLibraryDaoFileImpl(String libraryTextFile){
        LIBRARY_FILE = libraryTextFile;
        
    }
    
    private DVD unmarshallDVD(String dvdAsText) {
        String[] dvdTokens = dvdAsText.split(DELIMITER);
        String dvdTitle = dvdTokens[0];
        DVD dvdFromFile = new DVD(dvdTitle);
        dvdFromFile.setTitle(dvdTokens[1]);
        dvdFromFile.setReleaseDate(LocalDate.parse(dvdTokens[2]));
        dvdFromFile.setRating(dvdTokens[3]);
        dvdFromFile.setDirectorName(dvdTokens[4]);
        dvdFromFile.setStudio(dvdTokens[5]);       
        dvdFromFile.setUserRating(dvdTokens[6]);        
        return dvdFromFile;
    }

    private void loadLibrary() throws DVDLibraryPersistenceException {
        Scanner scanner;
        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(LIBRARY_FILE)));
        } catch (FileNotFoundException e) {
            throw new DVDLibraryPersistenceException(
                    "-_- Could not load library data into memory.", e);
        }
        String currentLine;
        DVD currentDVD;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentDVD = unmarshallDVD(currentLine);
            dvdMap.put(currentDVD.getTitle(), currentDVD);
        }
        scanner.close();
    }

    private String marshallDVD(DVD aDVD) {
        String dvdAsText = aDVD.getTitle() + DELIMITER;
        dvdAsText += aDVD.getTitle() + DELIMITER;
        dvdAsText += aDVD.getReleaseDate() + DELIMITER;
        dvdAsText += aDVD.getRating() + DELIMITER;
        dvdAsText += aDVD.getDirectorName() + DELIMITER;
        dvdAsText += aDVD.getStudio() + DELIMITER;
        dvdAsText += aDVD.getUserRating();
        return dvdAsText;
    }

    private void writeLibrary() throws DVDLibraryPersistenceException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(LIBRARY_FILE));
        } catch (IOException e) {
            throw new DVDLibraryPersistenceException(
                    "Could not save DVD data.", e);
        }

        String dvdAsText;
        HashMap<String,DVD> dvdList = this.getAllDVDS();
        for (Map.Entry<String, DVD> currentDVD : dvdList.entrySet()) {
            String key = currentDVD.getKey();
            DVD dvd = currentDVD.getValue();
            dvdAsText = marshallDVD(dvd);
            out.println(dvdAsText);
            out.flush();
        }
        out.close();
    }

 
    @Override
    public void addDVD(DVD dvd)throws DVDLibraryPersistenceException {
        loadLibrary();
        dvdMap.put(dvd.getTitle(), dvd);
        writeLibrary();
    }

    @Override
    public void deleteDVD(String title)throws DVDLibraryPersistenceException {
        loadLibrary();
        dvdMap.remove(title);
        writeLibrary();
    }

    @Override
    public boolean search(String title)throws DVDLibraryPersistenceException {
        loadLibrary();
        return dvdMap.containsKey(title);

    }
    
    @Override
    public HashMap<String,DVD> getAllDVDS() throws DVDLibraryPersistenceException{
        loadLibrary();
        return dvdMap;
    }

    @Override
    public DVD listInfo(String title)throws DVDLibraryPersistenceException {
        loadLibrary();
        DVD dvdObject = dvdMap.get(title);
        return dvdObject;
    }

    @Override
    public void editInfo(String title, int number, String newValue)throws DVDLibraryPersistenceException {
        loadLibrary();
        DVD dvdObj = dvdMap.get(title);
        switch (number) {
            case 1:
                dvdObj.setReleaseDate(LocalDate.parse(newValue));
                break;
            case 2:
                dvdObj.setRating(newValue);
                break;
            case 3:
                dvdObj.setDirectorName(newValue);
                break;
            case 4:
                dvdObj.setStudio(newValue);
                break;
            case 5:
                dvdObj.setUserRating(newValue);
                break;
        }
        writeLibrary();
    }

}
