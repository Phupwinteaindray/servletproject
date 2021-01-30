<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Course List</title>
<link rel="stylesheet" href="../resources/csss/bootstrap.min.css" />
<link rel="stylesheet" href="../resources/css/font-awesome.min.css" />
<link rel="stylesheet" href="../resources/csss/common.css" />
<script type="text/javascript" src="../resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../resources/js/jquery.min.js"></script>
<script type="text/javascript" src="../resources/js/popper.min.js"></script>


</head>
<body>
	<c:import url="/common/menu.jsp"></c:import>
	<div class="container">
		<h3 class="mt-4 mb-2">Course List</h3>
		<c:url value="/auth/courses/courses-upload" var="upload"></c:url>
			<form id="uploadForm" action="${upload }" method="post" enctype="multipart/form-data" class="d-none">
				<input  id="uploadFile" type="file" name="file"  />
			</form>
		<form class="form-inline mt-4 mb-2">
			<div class="form-group">
				<button id="uploadButton" type="button" class="btn btn-danger"><i class="fa fa-upload"></i>Upload</button>
			</div>
		</form> 
		<c:choose>
			<c:when test="${empty list  }">
				<div class="alert alert-primary">
					<p>There is no data to display</p>
				</div>
			</c:when>
			<c:otherwise>
			<table class="table">
			<thead>
				<tr>
					<th>Id</th>
					<th>Course Name</th>
					<th>Level</th>
					<th>Fees</th>
					<th>Hours</th>
					<th>Description</th>
					<th></th>
				</tr>
			</thead>
			<tbody>

				<c:forEach items="${list }" var="lis">
					<tr>
						<td>${lis.id }</td>
						<td>${lis.name }</td>
						<td>${lis.level }</td>
						<td>${lis.fees }</td>
						<td>${lis.hours }Hours</td>
						<td>${lis.description }</td>
						<td><c:url value="/auth/courses-edit" var="edit">
								<c:param name="id" value="${lis.id }"></c:param>
							</c:url> <a href="${edit }" class="btn btn-link"><i
								class="fa fa-pencil">Edit</i> </a></td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
		</c:otherwise>
		</c:choose>
	</div>
	
	<script>
	$(()=>{
		$('#uploadButton').click(()=>$('#uploadFile').click())
		$('#uploadFile').change(()=>$('#uploadForm').submit())
	})
	
	</script>
</body>
</html>