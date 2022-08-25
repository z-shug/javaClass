/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zls.classroster.dao;


import zls.classroster.dao.ClassRosterAuditDao;
import zls.classroster.dao.ClassRosterPersistenceException;


/**
 *
 * @author zshug
 */
public class ClassRosterAuditDaoStubImpl implements ClassRosterAuditDao{
    
    @Override
    public void writeAuditEntry(String entry) throws ClassRosterPersistenceException {
        //do nothing . . .
    }
}
