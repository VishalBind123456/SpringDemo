<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.example.SpringDemo.Model.Student.Student , java.util.*" %>
<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org" >
<head>
	<meta charset="UTF-8">
	<title>Student Grid</title>
	<!-- JQuery only -->
	<script src="https://code.jquery.com/jquery-3.6.1.js" ></script>
	
</head>
<body>
	<div id="root">
	<div id="grid">
	<button type="button" class="add">Add New</button>
	<table>
		<thead>
			<tr>
				<th>#</th>
				<th>Name</th>
				<th>Age</th>
				<th>BirthDate</th>
			</tr>
		</thead>
		<tbody>
			<% List<Student> list = (List<Student>)request.getAttribute("students"); %>
			<% for(int i = 0 ; i < list.size() ; i++){ %>
			<% Student stu = list.get(i); %>
			<tr>
				<td><%= i+1 %></td>
				<td><%= stu.getName() %></td>
				<td><%= stu.getAge() %></td>
				<td><%= stu.getBirthDate() %></td>
				<td><button type="button" value=<%= stu.getStudentId() %> class="address">Address</button></td>
				<td><button type="button" value=<%= stu.getStudentId() %> class="edit">Edit</button></td>
				<td><button type="button" value=<%= stu.getStudentId() %> class="delete">Delete</button></td>
			</tr>
			<% } %>
		</tbody>
	</table>
	<script id="script1">
	jQuery(".add").on({
		click : function(){
			var url = "http://localhost:8081/upsert2";
			jQuery.ajax({
				type : "POST",
				url : url,
				data : {"abc" : "xyz"},
				success : function(data){
					console.log("success");
					console.log(data);
					jQuery("#root").html(data.upsert2);
					
				},
				error : function(err){
					console.log("error");
					console.log(err);
				}
			});
		}
	});
	jQuery(".edit").on({
		click : function(){
			var val = jQuery(this).val();
			var url = "http://localhost:8081/upsert2?studentId="+val;
			jQuery.ajax({
				type : "POST",
				url : url,
				data : {"abc" : "xyz"},
				success : function(data){
					console.log("success");
					console.log(data);
					jQuery("#root").html(data.upsert2);
					
				},
				error : function(err){
					console.log("error");
					console.log(err);
				}
			});
		}
	});
	jQuery(".delete").on({
		click : function(){
			var val = jQuery(this).val();
			var url = "http://localhost:8081/delete?studentId="+val;
			jQuery.ajax({
				type : "POST",
				url : url,
				data : {"abc" : "xyz"},
				success : function(data){
					console.log("success");
					console.log(data);
					jQuery("#root").html(data.grid2);
					
				},
				error : function(err){
					console.log("error");
					console.log(err);
				}
			});
		}
	});
	</script>	
	</div>
	</div>	
</body>

</html>

