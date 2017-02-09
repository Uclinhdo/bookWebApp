/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.bookwebapp.model;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author linhdo
 */
public class AuthorService {
    
    public final List<Author> getAuthorList(){
        return Arrays.asList(
                new Author(1,"J.K Rowling",new Date()),
                new Author(2,"George Orwell", new Date()),
                new Author(3,"James Patterson", new Date()),
                new Author(4,"Neil Gaiman", new Date()),
                new Author(5,"Margaret Atwood", new Date())
        );
    }
}
