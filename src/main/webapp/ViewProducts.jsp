<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Details</title>
</head>
<body>
<h1>View Product Details</h1>
<h2> Choose Product to View (copy-paste product id): </h2>
<h4> ${table} </h4>
<br><br>
<form action="showProducts" method = "post">
	<input type="text" name="pid">
	<input type="submit" value="Submit">	
</form>
</body>
</html>