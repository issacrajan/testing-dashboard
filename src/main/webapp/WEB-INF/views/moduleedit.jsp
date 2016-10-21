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
				<h3 class="panel-title">Update Module Description</h3>
			</div>
			<div class="panel-body">
				<spring:url value="/moduleedit" var="moduleActionUrl" />
				<form:form class="form-horizontal" role="form" modelAttribute="module" action="${moduleActionUrl}">
					<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label">Module Name</label>
						
						<div class="col-sm-5">
							<form:hidden path="moduleName" />
							<span> ${module['moduleName']} </span>
						</div>
					</div>
					<div class="form-group">
						<label for="lastname" class="col-sm-2 control-label">Module Desc</label>
						<div class="col-sm-5">
							<form:input path="moduleDesc" maxlength="100" class="form-control" />
							<form:errors path="moduleDesc" />
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-5">
							<button type="submit" class="btn btn-default">Update </button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>