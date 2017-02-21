/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.bookwebapp.model;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author linhdo
 */
public class AuthorService {
    private AuthorDaoStrategy dao;
    

    

    
    public AuthorService(AuthorDaoStrategy dao) {
        this.dao = dao;
    }
    

    
    public AuthorDaoStrategy getDao() {
        return dao;
    }

    public void setDao(AuthorDaoStrategy dao) {
        this.dao = dao;
    }
    
    
    
    public final List<Author> getAuthorList(String tableName,int maxRecords) throws ClassNotFoundException, SQLException{
        //replace with database assess code
        
//        return Arrays.asList(
//                new Author(1,"J.K Rowling",new Date()),
//                new Author(2,"George Orwell", new Date()),
//                new Author(3,"James Patterson", new Date()),
//                new Author(4,"Neil Gaiman", new Date()),
//                new Author(5,"Margaret Atwood", new Date())
//        );
            //table name and number records should be felxible
            // passing in constructor, creating pivate properties and getters and setters
            return dao.getAuthorList(tableName, maxRecords);
    }
    public static void main(String[] args) throws Exception {
        AuthorService as = new AuthorService(new AuthorDao(
                new MySqlDbAccessor(),
                "com.mysql.jdbc.Driver", 
                "jdbc:mysql://localhost:3306/book",
                "root", "admin1"));
        List<Author> authors = as.getAuthorList("author", 50);
        for(Author a : authors) {
            System.out.println(a);
        }
    }
}
