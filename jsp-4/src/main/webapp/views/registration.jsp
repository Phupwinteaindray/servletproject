<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration</title>
<jsp:include page="/common/resource.jsp"></jsp:include>
</head>
<body>
	<c:import url="/common/menu.jsp"></c:import>
	<div class="container">
		<h3 class="mt-4 mb-2">Department</h3>
		<!--Search box  -->
		<c:url value="/auth/department-upload" var="upload"></c:url>
			<form id="uploadForm" action="${upload }" method="post" enctype="multipart/form-data" class="d-none">
				<input  id="uploadFile" type="file" name="file"  />
			</form>
		<form class="form-inline mt-4 mb-2">
			<div class="form-group">
				<button id="uploadButton" type="button" class="btn btn-danger"><i class="fa fa-upload"></i>Upload</button>
			</div>
		</form> 
		<!--Result Table  -->
	
		<table class="table mt-3">
			
			<thead>
				<tr>
					<th>ID</th>
					<th>Department Name</th>
					<th>Department Phone</th>
					<th>Department Email</th>
				
				</tr>
			</thead>
			
			<tbody>
				<c:forEach items="${list}" var="r">
				
					<tr>
						<td>${r.id}</td>
						<td>${r.deptName}</td>
						<td>${r.deptPhone}</td>
						<td>${r.officeMail}</td>
						
					</tr>
				</c:forEach>
			</tbody>
		
		</table>
	
	
	</div>
		<script>
	$(()=>{
		$('#uploadButton').click(()=>$('#uploadFile').click())
		$('#uploadFile').change(()=>$('#uploadForm').submit())
	})
	
	</script>
	
</body>
</html>