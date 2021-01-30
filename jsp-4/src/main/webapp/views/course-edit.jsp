<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Course</title>
<jsp:include page="/common/resource.jsp"></jsp:include>
<link rel="stylesheet" href="../resources/csss/bootstrap.min.css" />
<link rel="stylesheet" href="../resources/css/font-awesome.min.css" />
</head>
<body>
	<c:import url="/common/menu.jsp"></c:import>
	<div class="container">
		<h3 class="mt-4 mb-2"> Course</h3>
		<div class="row">
			<div class="col-lg-6">
				<c:url value="/auth/courses-edit" var="save"></c:url>
				<form action="${save }" method="post" class="form">
				<div class="form-group">
				<input type="hidden" name="id" value="${course.id }" />
				</div>
					<div class="form-group">
						<label for="">Course Name</label> <%-- <input  name="name"
							placeholder="Enter Course Name" required="required" value="${course.name }"
							class="form-control" id="" /> --%>
						<textarea name="name" id="description" class="form-control">${course.name }</textarea>
					</div>

					<div class="form-group">
						<label for="">Course Fees</label> <input type="number" name="fees"
							class="form-control" id="" placeholder="Enter Fees" required="required" value="${course.fees }"/>
					</div>
					<div class="form-row">
						<div class="col-lg-6">
							<div class="form-group">
								<label for="">Level</label> <select name="level" id=""
									class="form-control" required="required">
									<c:forEach items="${ level}" var="lev">
									<option ${ course.level eq lev ? 'selected' : '' } value="${ lev}">${lev}</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<div class="col-lg-6">
							<div class="form-group">
								<label for="">Hour</label> <input type="number" name="hour"
									placeholder="Enter Hour" class="form-control" id="" required="required" value="${course.hours }"/>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="">Description</label> <textarea name="description" id="description" class="form-control">${course.description }</textarea>
					</div>
					<div class="form-group">
					<button type="reset" class="btn btn-danger"><i class="fa fa-recycle"></i>Reset</button>
					<button type="submit" class="btn btn-primary"><i class="fa fa-save"></i>Submit</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>