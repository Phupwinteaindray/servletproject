<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit The Student</title>
<jsp:include page="/common/resource.jsp"></jsp:include>
</head>
<body>
<c:import url="/common/menu.jsp"></c:import>
<div class="container">
	
		<h3 class="mt-4 mb-2"> Student</h3>
		
		<div class="row">
			
			<div class="col-6">
				
				<form action="#" method="post" class="form">
				
					<input type="hidden" name="id" value="${student.id}" />
					
					<div class="form-group">
						<label for="name">Student Name</label>
						<input type="text" name="name" value="${student.name}" class="form-control" id="name"  />
					</div>
					<div class="form-group">
						<label for="rollNumber">Student Roll Number</label>
						<input type="text" name="rollNumber" value="${student.rollNumber }" class="form-control" id="rollNumber" />
					</div>
					<div class="form-group">
						<label for="phone">Phone Number</label>
						<input type="tel" name="phone" value="${student.phone}" class="form-control" id="phone" />
					</div>

					<div class="form-group">
						<label for="email">Student Email</label>
						<input type="email" name="email" value="${student.email}" class="form-control" id="email" />
					</div>
					
					<button class="btn btn-outline-danger">Save Student</button>
				</form>
			
			</div>
		
		</div>
	</div>
</body>
</html>