<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Product Details</title>
</head>
<body>
<h1>View Product Details</h1>
<h2> Details submitted as follows: </h2>
<form action="getProducts" method = "post">
<%-- 	Select a Category:
	<select name="pid">
		<c:forEach var="product" items="${listProducts}">
			<option value="${product.id}">${product.name} }</option>
		</c:forEach>
	</select>
	<br/><br/> --%>
	<input type="number" name="pid">
	<input type="submit" value="Submit">	
</form>
</body>
</html>