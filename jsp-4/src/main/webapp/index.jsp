<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html style="height: 100%">
<head>
<meta charset="UTF-8">
<title>Student Registration</title>
<jsp:include page="/common/resource.jsp"></jsp:include>
</head>
<body style="height: 100%">

	<div class="container">

		<div class="row">
			<div class="col-4 m-auto">
				<div class="card login-form ">
					<div class="card-body">
						<h3>
							<i class="fa fa-sign-in mr-2"></i>Member Login
						</h3>
						<c:if test="${not empty message }">
						<div class="alert alert-warning">${message }</div>
						</c:if>
						<c:url value="/login" var="lgin"></c:url>
						<form action="${lgin }" method="post" class="form">
							<div class="form-group">
								<label for="">Login Id</label> <input type="text"
									class="form-control" name="loginid" id="" required="required"
									placeholder="Enter Login Id" />
							</div>
							<div class="form-group">
								<label for="">Password</label> <input type="password"
									class="form-control" name="pass" required="required"
									placeholder="Enter password" id="" />
							</div>
							<button class="btn btn-primary" type="submit">Member Log
								In</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>