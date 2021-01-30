<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration-Edit</title>
<jsp:include page="/common/resource.jsp"></jsp:include>
<link rel="stylesheet" href="../resources/csss/bootstrap.min.css" />
<link rel="stylesheet" href="../resources/css/font-awesome.min.css" />
</head>
<body>
	<c:import url="/common/menu.jsp"></c:import>
	<div class="container">
		<h3 class="mt-4 mb-2">Create Teachers</h3>
		<div class="row">
			<div class="col-6">
			<c:url value="/auth/teacher-edit" var="action"></c:url>
				<form action="${action }" class="form" method="post">
				<div class="form-group">
					<input type="hidden" name="id" value="${student.id }" />
				</div>
					<div class="form-group">
						<label for="">Department</label> <select name="department" id=""
							class="form-control">
								
							   <c:forEach items="${courses }" var="c">
								<%-- <option value="${c.getLevel()}" hidden="hidden">${c.getLevel()}</option> --%>
								 <option value="${c.id}">${c.deptName}</option> 
							</c:forEach>   
						</select>

					</div>
					
					<div class="form-group">
					<label for="">Teacher Name</label>
					<input type="text" name="name" id="name" required="required" class="form-control" placeholder="Enter Teacher Name" value="${student.name }"/>
					</div>
					<div class="form-group">
					<label for="">Position</label>
					<input type="text" name="position" id="position" class="form-control" placeholder="Enter Position" required="required" value="${student.position }"  />
					</div>
					<div class="form-group">
					<label for="">Email</label>
					<input type="email" name="email" id="mail" class="form-control" placeholder="Enter Email" required="required" value="${student.teacherEmail }" />
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