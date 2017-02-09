<%-- 
    Document   : listAuthors
    Created on : Feb 8, 2017, 10:33:48 PM
    Author     : linhdo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Book Web Application</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link href="main.css" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="row">
                 <div class="header">
                    <h1 class="title">Book Web Application</h1>
                </div>
                <div class="menu">
                    <nav class="menu_list">
                        <ul>
                            <li><a href="index.html">Home</a></li>
                            <li><a href="AuthorController?action=list">View All Authors</a></li>
                        </ul>
                    </nav>
                </div>
            </div>
            <div class="row">
                <h2 class="list">Author List</h2>
                <div>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Author Name</th>
                                <th>Date</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="au" items="${authors}" varStatus="rowCount">
                                    <tr>
                                <c:choose>
                                    <c:when test="${rowCount.count % 2 == 0}">
                                        <tr style="background: #ECF0F1;">
                                    </c:when>
                                    <c:otherwise>
                                        <tr style="background: #AED6F1;">
                                    </c:otherwise>
                                </c:choose>
                                        <td align="center">${au.authorId}</td>
                                        <td align="center">${au.authorName}</td>
                                        <td align="center"><fmt:formatDate pattern="MM/dd/yyyy" value="${au.dateAdded}"></fmt:formatDate></td>
                                   </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="row">
                <div><p class="footnote">bookwebapp-version 1.0</p></div>
            </div>
        </div>
    </body>
</html>
