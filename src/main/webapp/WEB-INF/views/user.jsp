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
		<div class="">
			<button type="button" class="btn btn-primary pull-right" onclick="location.href='userlist'">
				<span class="glyphicon glyphicon-star" aria-hidden="true"></span> Goto User List Page
			</button>
		</div>
		
		<div class="panel panel-default">
			<div class="panel-heading clearfix">
				<i class="icon-calendar"></i>
				<h3 class="panel-title">
					<c:choose>
						<c:when test="${user['new']}">
							<h4>Add User</h4>
						</c:when>
						<c:otherwise>
							<h4>Update User</h4>
						</c:otherwise>
					</c:choose>

				</h3>
			</div>
			<div class="panel-body">
				<spring:url value="/user" var="userActionUrl" />
				<form:form class="form-horizontal" role="form" modelAttribute="user" action="${userActionUrl}">
					<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label">User Id</label>
						<form:hidden path="editMode" />
						<div class="col-sm-5">
							<c:choose>
								<c:when test="${user['new']}">
									<form:input path="userId" maxlength="20" class="form-control" id="userId" placeholder="Enter User ID" />
									<form:errors path="userId" />
								</c:when>
								<c:otherwise>
									<form:hidden path="userId" />
									<span> ${user['userId']} </span>
								</c:otherwise>
							</c:choose>


						</div>
					</div>
					<div class="form-group">
						<label for="lastname" class="col-sm-2 control-label">User Name</label>
						<div class="col-sm-5">
							<form:input path="userName" maxlength="100" class="form-control" id="userName" placeholder="Enter  Name" />
							<form:errors path="userName" />
						</div>
					</div>
					<div class="form-group">
						<label for="userStatus" class="col-sm-2 control-label">Status</label>
						<div class="col-sm-5">
							<form:select path="userStatus">
								<form:option value="A" label="Active" />
								<form:option value="D" label="Disabled" />
							</form:select>
						</div>
					</div>
					<div class="form-group">
						<label for="userRole" class="col-sm-2 control-label">User Role</label>
						<div class="col-sm-5">
							<form:select path="userRole">
								<form:option value="U" label="User" />
								<form:option value="A" label="Admin" />
							</form:select>
						</div>
					</div>
					<c:choose>
						<c:when test="${user['new']}">
							<div class="form-group">
								<label for="lastname" class="col-sm-2 control-label">Password</label>
								<div class="col-sm-5">
									<form:password path="userPwd" maxlength="20" class="form-control" />
									<form:errors path="userPwd" />
								</div>
							</div>
							<div class="form-group">
								<label for="lastname" class="col-sm-2 control-label">Confirm Password</label>
								<div class="col-sm-5">
									<form:password path="userPwdConfirm" maxlength="20" class="form-control" />
									<form:errors path="userPwdConfirm" />
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<spring:url value="/changepwd?id=${user.userId}" var="changePwdUrl" />
							<spring:url value="/userpermission?id=${user.userId}" var="userPermUrl" />
							<div class="form-group">
								<label class="col-sm-2 control-label"></label>
								<div class="col-sm-5">
									<button type="button" class="btn btn-info" onclick="location.href='${changePwdUrl}'">Change Password</button>
								</div>
								<div class="col-sm-5">
									<button type="button" class="btn btn-info" onclick="location.href='${userPermUrl}'">Set Permission</button>
								</div>
							</div>
						</c:otherwise>
					</c:choose>

					<div class="form-group">
						<label for="lastname" class="col-sm-2 control-label">E-mail</label>
						<div class="col-sm-5">
							<form:input path="userEmail" maxlength="100" class="form-control" />
							<form:errors path="userEmail" />
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