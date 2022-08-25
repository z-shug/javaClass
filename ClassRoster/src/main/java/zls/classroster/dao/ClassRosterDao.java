/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zls.classroster.dao;

import java.util.List;
import zls.classroster.dto.Student;

/**
 *
 * @author zshug
 */
public interface ClassRosterDao {


    public Student addStudent(String studentId, Student student)throws ClassRosterPersistenceException ;

    public List<Student> getAllStudents()throws ClassRosterPersistenceException;

    public Student getStudent(String studentId)throws ClassRosterPersistenceException;

    public Student removeStudent(String studentId)throws ClassRosterPersistenceException;
}