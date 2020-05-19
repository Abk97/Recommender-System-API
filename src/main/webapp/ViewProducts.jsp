<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page import="java.util.ArrayList"%> 
<%@page import="java.util.UUID"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Product Details</title>
</head>
<body>
<h1>View Product Details</h1>
<h1>Displaying Product List</h1> 
<form action="getProducts" method = "post">
<%--         <table> 
        <%ArrayList<UUID> list =  
            (ArrayList<UUID>)request.getAttribute("listIds"); 
        %> 
        </table>  
        <hr/>  --%>
	<input type="number" name="pid">
	<input type="submit" value="Submit">	
</form>
</body>
</html>