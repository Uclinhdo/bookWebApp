/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.bookwebapp.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author linhdo
 */
public class AuthorDao implements AuthorDaoStrategy {
    private DbAccessor db;
    private String driverClassName;
    private String url;
    private String userName;
    private String pwd;
    
  
    public AuthorDao(DbAccessor db, String driverClassName, String url, String userName, String pwd) {
        this.db = db;
        this.driverClassName = driverClassName;
        this.url = url;
        this.userName = userName;
        this.pwd = pwd;
    }
    
    @Override
    public List<Author> getAuthorList(String tableName, int maxRecords) throws ClassNotFoundException, SQLException {
        
        //return the list of author
        List<Author> authorList = new ArrayList<>();
        db.openConnection(driverClassName, url, userName, pwd);
        List<Map<String,Object>> rawData = db.findRecordFor(tableName, maxRecords);
        
        for(Map<String,Object> recData: rawData){
            Author author = new Author();
            //Object objAuthorId = recData.get("author_id");
            author.setAuthorId((Integer)recData.get("author_id"));
            Object objName = recData.get("author_name");
            String name = objName != null ? objName.toString() : "";
            author.setAuthorName(name);
            Object objDate = recData.get("date_added");
            Date dateAdded = objDate != null ? (Date)objDate : null;
            author.setDateAdded(dateAdded);
            authorList.add(author);
        }
        db.closeConnection();
        
        return authorList;
    }
    @Override
    public DbAccessor getDb() {
        return db;
    }

    @Override
    public void setDb(DbAccessor db) {
        this.db = db;
    }

    @Override
    public String getDriverClassName() {
        return driverClassName;
    }

    @Override
    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getPwd() {
        return pwd;
    }

    @Override
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    
    
    public static void main(String[] args) throws Exception {
        
        AuthorDaoStrategy dao = new AuthorDao(new MySqlDbAccessor(),
                "com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book",
                "root", "admin1");
        
        List<Author> authors = dao.getAuthorList("author", 50);
        for(Author a : authors) {
            System.out.println(a);
        }
    }
}
