<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.example.SpringDemo.Model.Student.Student , java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
	<form action="http://localhost:8081/save">
	<table>
		<% Student stu = (Student)request.getAttribute("student"); %>
		<tr>
			<td><label>Name</label></td>
			<td><input type="text" name="name" placeholder="enter your name" value=<%= (stu.getName() == null)?"":stu.getName() %> ></td>
		</tr>
		<tr>
			<td><label>Age</label></td>
			<td><input type="number" name="age" placeholder="enter your age" value=<%= (stu.getAge() == null)?"":stu.getAge() %> ></td>
		</tr>
		<tr>
			<td><label>BirthDate</label></td>
			<td><input type="date" name="birthdate" value=<%= (stu.getBirthDate() == null)?"":stu.getBirthDate() %> ></td>
		</tr>	
	</table>
	<button type="submit" class="save">Save</button>
	</form>
	</div>
</body>
</html>