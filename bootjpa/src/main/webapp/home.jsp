<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<legend>Add a new user</legend>
	<form action="addUser">
	Please enter userid : <input type="text" name="userid"><br>
	<br>
	Please enter your name : <input type="text" name="name"><br>
	<br>
	<input type="submit"><br>
	</form>
	
	<br>
	<br>
	<br>
	
	<legend>Search by userid</legend>
	<form action="showUser">
	Enter userid : <input type="text" name="userid"><br>
	<br>
	<br>
	<input type="submit"><br>
	</form>
	<br>
	<br>
	<legend>Delete by userid</legend>
	<form action="deleteUser">
	Enter userid : <input type="text" name="userid"><br>
	<br>
	<br>
	<input type="submit"><br>
	</form>
	
	
</body>
</html>