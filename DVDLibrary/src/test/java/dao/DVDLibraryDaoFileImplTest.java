/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.DVD;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author zshug
 */
public class DVDLibraryDaoFileImplTest {
    
    DVDLibraryDao testDao;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    
    public DVDLibraryDaoFileImplTest() {
    }
    


    
    @BeforeEach
    public void setUp() throws Exception {
        String testFile = "testlibrary.txt";
        // Use the FileWriter to quickly blank the file
        new FileWriter(testFile);
        testDao = new DVDLibraryDaoFileImpl(testFile);
    }
    
    @Test
    public void testAddSearchListInfoDVD() throws Exception {
        
        DVD dvd = new DVD();
        dvd.setTitle("Friday");
        dvd.setReleaseDate(LocalDate.parse("11/22/2004",formatter));
        dvd.setRating("PG-13");
        dvd.setDirectorName("someone");
        dvd.setStudio("Somewhere");
        dvd.setUserRating("Take it or leave it");
        
        testDao.addDVD(dvd);
        boolean hasDVD = testDao.search(dvd.getTitle());
        DVD returnedDVD = testDao.listInfo(dvd.getTitle());
        
        assertTrue(hasDVD,"checking dvd is in library");
        assertEquals(dvd.getTitle(),returnedDVD.getTitle(),"checking DVD title.");
        assertEquals(dvd.getReleaseDate(),returnedDVD.getReleaseDate(),"Checking DVD release date");
        assertEquals(dvd.getRating(), returnedDVD.getRating(), "Checking DVD rating");
        assertEquals(dvd.getDirectorName(),returnedDVD.getDirectorName(), "checking DVD director");
        assertEquals(dvd.getStudio(),returnedDVD.getStudio(),"Checking DVD studio");
        assertEquals(dvd.getUserRating(), returnedDVD.getUserRating(),"Checking DVD user rating."); 
    }
    
    @Test
    public void testAddGetAllDVDS() throws Exception {
        DVD dvdFirst = new DVD();
        dvdFirst.setTitle("Friday");
        dvdFirst.setReleaseDate(LocalDate.parse("11/22/2004",formatter));
        dvdFirst.setRating("PG-13");
        dvdFirst.setDirectorName("someone");
        dvdFirst.setStudio("Somewhere");
        dvdFirst.setUserRating("Take it or leave it");
        
        DVD dvdSecond = new DVD();
        dvdSecond.setTitle("Star wars");
        dvdSecond.setReleaseDate(LocalDate.parse("09/22/2000",formatter));
        dvdSecond.setRating("PG-13");
        dvdSecond.setDirectorName("someone");
        dvdSecond.setStudio("Somewhere");
        dvdSecond.setUserRating("May the force be with you");
        
        testDao.addDVD(dvdFirst);
        testDao.addDVD(dvdSecond);
        
        HashMap<String,DVD> allDVDS = testDao.getAllDVDS();
        
        assertNotNull(allDVDS, "The list of dvds must not be null");
        assertEquals(2, allDVDS.size(), "List of dvds should have 2 dvds");
        assertTrue(testDao.getAllDVDS().containsKey(dvdFirst.getTitle()), "the List of dvds should include Friday");
        assertTrue(testDao.getAllDVDS().containsKey(dvdSecond.getTitle()),"the list of dvds should include star wars"); 
    }
    
    @Test
    public void testDeleteDVD() throws Exception{
        DVD dvdFirst = new DVD();
        dvdFirst.setTitle("Friday");
        dvdFirst.setReleaseDate(LocalDate.parse("11/22/2004",formatter));
        dvdFirst.setRating("PG-13");
        dvdFirst.setDirectorName("someone");
        dvdFirst.setStudio("Somewhere");
        dvdFirst.setUserRating("Take it or leave it");
        
        DVD dvdSecond = new DVD();
        dvdSecond.setTitle("Star wars");
        dvdSecond.setReleaseDate(LocalDate.parse("09/22/2000",formatter));
        dvdSecond.setRating("PG-13");
        dvdSecond.setDirectorName("someone");
        dvdSecond.setStudio("Somewhere");
        dvdSecond.setUserRating("May the force be with you");
        
        testDao.addDVD(dvdFirst);
        testDao.addDVD(dvdSecond);
        
        testDao.deleteDVD(dvdFirst.getTitle());
        
        HashMap<String,DVD> allDVDS = testDao.getAllDVDS();
        
        assertNotNull(allDVDS,"All dvds list should be not null");
        assertEquals(1,allDVDS.size(), "aAll dvds should only have 1 dvd");
        
        assertFalse(allDVDS.containsKey(dvdFirst.getTitle()),"All dvds should NOT include Friday");
        assertTrue(allDVDS.containsKey(dvdSecond.getTitle()),"All dvds should include Star Wars");
        
        testDao.deleteDVD(dvdSecond.getTitle());
        
        allDVDS = testDao.getAllDVDS();
        
        assertTrue(allDVDS.isEmpty(),"The retrieved library should be empty");
        
        boolean searchDVD = testDao.search(dvdFirst.getTitle());
        assertFalse(searchDVD,"Friday was removed, should be false");
        
        searchDVD = testDao.search(dvdSecond.getTitle());
        assertFalse(searchDVD,"Star wars was removed, should be false"); 
    }
}
    


    

