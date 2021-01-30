<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="container">
		<c:url value="/home" var="home"></c:url>
		<a class="navbar-brand" href="${home }"><i class="fa fa-home "></i>Registration</a>
		<ul class="navbar-nav nav mr-auto">
			<c:if test="${login.role eq 'OFFICE' }">
				<li class="nav-item"><c:url value="/auth/department-edit"
						var="register"></c:url> <a href="${register }" class="nav-link"><i
						class="fa fa-plus"></i>Create Department</a></li>
				<li class="nav-item"><c:url value="/auth/teacher-edit"
						var="register"></c:url> <a href="${register }" class="nav-link"><i
						class="fa fa-plus"></i>Create Teacher</a></li>
			</c:if>

			<li class="nav-item"><c:url value="/auth/students" var="student"></c:url>
				<a href="${student }" class="nav-link"><i class="fa fa-users"></i>Student</a></li>
			<li class="nav-item"><c:url value="/auth/teacher" var="student"></c:url>
				<a href="${student }" class="nav-link"><i class="fa fa-users"></i>Teacher</a></li>

			<c:if test="${ login.role eq 'TEACHER'}">
				<li class="nav-item"><c:url value="/auth/courses" var="courses"></c:url>
					<a href="${courses }" class="nav-link"><i class="fa fa-book"></i>Courses</a></li>
				<li class="nav-item"><c:url value="/auth/courses-edit"
						var="courses"></c:url> <a href="${courses }" class="nav-link"><i
						class="fa fa-plus"></i>Create Course</a></li>
				<li class="nav-item"><c:url value="/auth/student-edit"
						var="student"></c:url> <a href="${student }" class="nav-link"><i
						class="fa fa-plus"></i>Create Student</a></li>
			</c:if>

		</ul>
		<ul class="navbar-nav nav">
			<li class="nav-item"><span class="nav-link"><i
					class="fa fa-user mr-auto"></i>${login.name }</span></li>
			<li class="nav-item"><a href="#" id="logoutmenu"
				class="nav-link"> <i class="fa fa-sign-out"></i> Sign Out
			</a></li>
		</ul>

	</div>
	<c:url value="/logout" var="signout"></c:url>
	<form action="${signout }" id="logoutform" method="post" class="d-none"></form>
	<script type="text/javascript">
	$(()=>{
		$('#logoutmenu').click(()=>$('#logoutform').submit())
		})
	</script>
</nav>