/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.bookwebapp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;


/**
 *
 * @author linhdo
 */
public class MySqlDbAccessor implements DbAccessor {
    private Connection conn;
    
    /**
     * 
     * @param tableName
     * @param maxRecord
     * @return
     * @throws SQLException 
     */
    @Override
    public List<Map<String,Object>> findRecordFor(String tableName, int maxRecord) throws SQLException{
        
        String sql = "";
        if(maxRecord > 0){
             sql = "SELECT * FROM " + tableName + " LIMIT " + maxRecord;
        }else{
            sql = "SELECT * FROM " + tableName;
        }
        
        Statement stmt = conn.createStatement();
        ResultSet re = stmt.executeQuery(sql);
       
        List<Map<String,Object>> results = new ArrayList<>();
        
        ResultSetMetaData rsmd = re.getMetaData();
        int colCount = rsmd.getColumnCount();
        //create new map object to retrive the record
        Map<String,Object> record = null;
        //create a loop to loop over single record and using colCount to retrieve the record icluding colName 
        //colNo <= colCount or colNo < colCount + 1
        //using while loop to loop over the record
        while(re.next()){
            // LinkedHashMap to run faster, maintain the order of colName and its value
            record = new LinkedHashMap<>();
        for(int colNo = 1; colNo < (colCount + 1); colNo++){
                String colName = rsmd.getColumnName(colNo);
                //dont know data type ==>  use getObject
                record.put(colName, re.getObject(colNo));
            }
            //take result list and add record
            //keep doing until run out the record
            results.add(record);
        }
        return results;
    }
    //consider creating a custom exception
    @Override
    public void openConnection(String driverClassName,String url, String userName, String pwd) throws ClassNotFoundException, SQLException{
        //need validation
        
        //load driver in memory
        Class.forName(driverClassName);
        conn = DriverManager.getConnection(url,userName, pwd);
    }
    @Override
    public void closeConnection() throws SQLException{
        // check the connection is opened or not, then close connection
        if(conn != null){
            conn.close();
        }
        
    }
    public final int deleteById(String tableName, Object id) throws SQLException{
        String sId = null;
        Integer intId = null;
        int resultDelete = 0;
        //create delete query, using WHERE to delete conditional records.
        String deleteSql = "DELETE FROM " + tableName + " WHERE id =? ";
        // Create a Java PreparedStatement for the SQL DELETE query.
        PreparedStatement pstm = conn.prepareStatement(deleteSql);
        if(id instanceof String){
             sId= id.toString();
             // set the corresponding param for placeholer
             pstm.setString(1, sId);
            // execute the delete statement
            resultDelete = pstm.executeUpdate();
           
        }else if(id instanceof Integer){
            intId = (Integer) id;
            // set the corresponding param
            pstm.setInt(1, intId);
            // execute the delete satement
            resultDelete = pstm.executeUpdate();
        }
        return resultDelete;
    }
    public final int insertRecord(String tableName, List<String> colNames, List colValues) throws SQLException{
        String sql = "INSERT INTO " + tableName + " ";
        //string joiner from java api 8
        StringJoiner sj = new StringJoiner(",","(",")");
        //get colName between "," and "()"
        for(String col : colNames){
            sj.add(col);
        }
        sql += sj.toString();//done insert into tablename
        //clear  method
        sj = new StringJoiner(",","(",")");
        
        sql += " VALUES ";
        for(Object val: colValues){
            //use "?" to placeholder value
            sj.add("?");
        }
        sql += sj.toString();
        PreparedStatement pstm = conn.prepareStatement(sql);
        for(int i = 0; i < colValues.size(); i++){
            //use "?" to placeholder value
            pstm.setObject(i+1, colValues.get(i));
        }
        int recsUpdated = pstm.executeUpdate();
        
        return recsUpdated;
    }
    
    /**
     * This main method to do testing
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        DbAccessor db = new MySqlDbAccessor();
        db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book",
                            "root", "admin1");
        List<Map<String,Object>> records = db.findRecordFor("author", 50);
        db.closeConnection();
        //loop over records
        for(Map<String,Object> record : records){
            System.out.println(record);
        }
    }
}
