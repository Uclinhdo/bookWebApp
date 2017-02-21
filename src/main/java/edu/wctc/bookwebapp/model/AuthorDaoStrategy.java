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
public interface AuthorDaoStrategy {

    public abstract List<Author> getAuthorList(String tableName, int maxRecords) throws ClassNotFoundException, SQLException;

    public abstract DbAccessor getDb();

    public abstract String getDriverClassName();

    public abstract String getPwd();

    public abstract String getUrl();

    public abstract String getUserName();

    public abstract void setDb(DbAccessor db);

    public abstract void setDriverClassName(String driverClassName);

    public abstract void setPwd(String pwd);

    public abstract void setUrl(String url);

    public abstract void setUserName(String userName);
    
}
