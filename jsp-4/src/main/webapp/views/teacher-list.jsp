<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List the Student</title>
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
	
		<h3 class="mt-4 mb-2">Teacher List</h3>
		
		<!-- Search Form -->
		<div class="card">
			<div class="card-body">
				<c:url value="/auth/teacher" var="search"></c:url>
				<form action="${search}" method="get" class="form">
				
					<div class="form-row">
						<div class="col-4">
							<div class="form-group">
								<label for="name">Name</label>
								<input name="name" type="text" placeholder="Search Name" class="form-control" id="name" value="${param.name}" />
							</div>
						</div>
						<div class="col-4">
							<div class="form-group">
								<label for="phone">Email</label>
								<input name="department" type="email" placeholder="Search Email" class="form-control" id="department" value="${param.department.deptName}" />
							</div>
						</div>
						<div class="col-4">
							<div class="form-group">
								<button class="btn btn-outline-primary" type="submit" style="margin-top: 32px">Search</button>
								<button id="uploadButton" type="button" class="btn btn-primary" style="margin-top:32px"><i class="fa fa-upload"></i>Upload</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
		
		<c:choose>
			<c:when test="${empty list  }">
				<div class="alert alert-primary">
					<p>There is no data to display</p>
				</div>
			</c:when>
			<c:otherwise>
		<!-- Result Table -->
		<table class="table mt-3">
			
			<thead>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Department Name</th>
					<th>Position</th>
					<th>Email</th>
					<c:if test="${login.role eq 'OFFICE'}">
						<th></th>
						<th></th>
					</c:if>
				</tr>
			</thead>
			
			<tbody>
				
				<c:forEach items="${list}" var="s">
				
					<tr>
						<td>${s.id}</td>
						<td>${s.name}</td>
						<td>${s.department.deptName}</td>
						<td>${s.position}</td>
						<td>${s.teacherEmail}</td>
						<c:if test="${login.role eq 'OFFICE'}">
							<td>	
								<c:url value="/auth/teacher-edit" var="edit">
									<c:param name="id" value="${s.id}"></c:param>
								</c:url>
								<a href="${edit}">
									<i class="fa fa-pencil"></i>
								</a>
							</td>
							<td>
								<c:url value="/auth/teacher-delete" var="delete">
									<c:param name="id" value="${s.id }"></c:param>
								</c:url>
								<a href="${delete }">
									<i class="fa fa-trash"></i>
								</a>
							</td>
						</c:if>
						
					</tr>
				
				</c:forEach>
			
			</tbody>
		
		</table>
		</c:otherwise>
		</c:choose>
	</div>
		<c:url value="/auth/teacher-upload" var="upload"></c:url>
		<form id="uploadForm" action="${upload }" method="post" enctype="multipart/form-data" class="d-none">
				<input  id="uploadFile" type="file" name="file"  />
		</form>
		
		<script>
		$(()=>{
			$('#uploadButton').click(()=>$('#uploadFile').click())
			$('#uploadFile').change(()=>$('#uploadForm').submit())
		})
	
	</script>
</body>
</html>