/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zls.classroster.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import zls.classroster.dto.Student;

/**
 *
 * @author zshug
 */
public class ClassRosterDaoFileImpl implements ClassRosterDao {

    private  final String ROSTER_FILE;
    public static final String DELIMITER = "::";
    private Map<String, Student> students = new HashMap<>();
    
    public ClassRosterDaoFileImpl(){
        ROSTER_FILE = "roster.txt";
    }
    
    public ClassRosterDaoFileImpl(String rosterTextFile){
        ROSTER_FILE = rosterTextFile; 
    }
    
    private Student unmarshallStudent(String studentAsText){
        String[] studentTokens = studentAsText.split(DELIMITER);
        String studentId = studentTokens[0];
        Student studentFromFile = new Student(studentId);
        studentFromFile.setFirstName(studentTokens[1]);
        studentFromFile.setLastName(studentTokens[2]);
        studentFromFile.setCohort(studentTokens[3]);
        return studentFromFile;
    }
    
    private void loadRoster() throws ClassRosterPersistenceException {
        Scanner scanner;

        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(ROSTER_FILE)));
        } catch (FileNotFoundException e) {
            throw new ClassRosterPersistenceException(
                    "-_- Could not load roster data into memory.", e);
        }
        String currentLine;
        Student currentStudent;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentStudent = unmarshallStudent(currentLine);
            students.put(currentStudent.getStudentId(), currentStudent);
        }

        scanner.close();
    }
    
    private String marshallStudent(Student aStudent){

        String studentAsText = aStudent.getStudentId() + DELIMITER;
        studentAsText += aStudent.getFirstName() + DELIMITER;
        studentAsText += aStudent.getLastName() + DELIMITER;
        studentAsText += aStudent.getCohort();
        return studentAsText;
    }
    
    private void writeRoster() throws ClassRosterPersistenceException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ROSTER_FILE));
        } catch (IOException e) {
            throw new ClassRosterPersistenceException(
                    "Could not save student data.", e);
        }

        String studentAsText;
        List<Student> studentList = this.getAllStudents();
        for (Student currentStudent : studentList) {
            studentAsText = marshallStudent(currentStudent);            
            out.println(studentAsText);
            out.flush();
        }
        out.close();
    }
    
    
    @Override
    public Student addStudent(String studentId, Student student) throws ClassRosterPersistenceException {
        loadRoster();
        Student newStudent = students.put(studentId, student);
        writeRoster();
        return newStudent;
    }

    @Override
    public List<Student> getAllStudents()throws ClassRosterPersistenceException {
        loadRoster();
        return new ArrayList<Student>(students.values());
    }

    @Override
    public Student getStudent(String studentId)throws ClassRosterPersistenceException {
        return students.get(studentId);
    }

    @Override
    public Student removeStudent(String studentId)throws ClassRosterPersistenceException {
        loadRoster();
        Student removedStudent = students.remove(studentId);
        writeRoster();
        return removedStudent;
    }
    

}