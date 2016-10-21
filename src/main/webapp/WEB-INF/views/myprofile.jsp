<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="header.jsp" flush="true" />

<body>
	<div class="container">
		<jsp:include page="navbar.jsp" flush="true" />

		<c:if test="${not empty msg}">
			<div class="alert alert-${css} alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
				<strong>${msg}</strong>
			</div>
		</c:if>

		<div class="panel panel-default">
			<div class="panel-heading clearfix">
				<i class="icon-calendar"></i>
				<h3 class="panel-title">
					<h4>My Profile</h4>
				</h3>
			</div>
			<div class="panel-body">
				<spring:url value="/myprofile" var="userActionUrl" />
				<form:form class="form-horizontal" role="form" modelAttribute="user" action="${userActionUrl}">
					<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label">User Id</label>
						<form:hidden path="editMode" />
						<div class="col-sm-5">
							<form:hidden path="userId" />
							<span> ${user['userId']} </span>
						</div>
					</div>
					<div class="form-group">
						<label for="lastname" class="col-sm-2 control-label">User Name</label>
						<div class="col-sm-5">
							<form:input path="userName" maxlength="100" class="form-control" id="userName" placeholder="Enter  Name" />
							<form:errors path="userName" />
						</div>
					</div>
					<spring:url value="/changemypwd" var="changePwdUrl" />
					<div class="form-group">
						<label class="col-sm-2 control-label"></label>
						<div class="col-sm-5">
							<button type="button" class="btn btn-info" onclick="location.href='${changePwdUrl}'">Change Password</button>
						</div>
					</div>

					<div class="form-group">
						<label for="lastname" class="col-sm-2 control-label">E-mail</label>
						<div class="col-sm-5">
							<form:input path="userEmail" maxlength="100" class="form-control" />
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-5">
							<button type="submit" class="btn btn-default">Save</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>