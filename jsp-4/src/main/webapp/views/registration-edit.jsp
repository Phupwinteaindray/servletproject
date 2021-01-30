<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration-Edit</title>
<jsp:include page="/common/resource.jsp"></jsp:include>
</head>
<body>
	<c:import url="/common/menu.jsp"></c:import>
	<div class="container">
		<h3 class="mt-4 mb-2">Create Department</h3>
		<div class="row">
			<div class="col-6">
			<c:url value="/auth/department-edit" var="action"></c:url>
				<form action="${action }" class="form" method="post">
				<div class="form-group">
					<input type="hidden" name="id" value="${student.id }" />
				</div>
					
					<div class="form-group">
					<label for="">Department name</label>
					<input type="text" name="deptName" id="deptName" placeholder="Enter department name"  required="required" value="${student.deptName }" class="form-control"/>
					</div>
					<div class="form-group">
					<label for="">Department Phone</label>
					<input type="tel" name="deptPhone" id="deptPhone" required="required" class="form-control" placeholder="Enter Department Phone" value="${student.deptPhone }" />
					</div>
					<div class="form-group">
					<label for="">Department Mail</label>
					<input type="email" name="officeMail" id="officeMail" class="form-control" placeholder="Enter Department Mail" required="required" value="${student.officeMail }"  />
					</div>
					
					<div class="form-group">
					<button type="reset" class="btn btn-danger">Clear</button>
					<button type="submit" class="btn btn-primary">Create</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>