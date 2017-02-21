/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.bookwebapp.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author linhdo
 */
public interface DbAccessor {

    public abstract void closeConnection() throws SQLException;

    /**
     *
     * @param tableName
     * @param maxRecord
     * @return
     * @throws SQLException
     */
    public abstract List<Map<String, Object>> findRecordFor(String tableName, int maxRecord) throws SQLException;

    //consider creating a custom exception
    public abstract void openConnection(String driverClassName, String url, String userName, String pwd) throws ClassNotFoundException, SQLException;
    
}
