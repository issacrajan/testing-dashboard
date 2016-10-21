<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="header.jsp" flush="true" />

<body>
	<div class="container">
		<jsp:include page="navbar.jsp" flush="true" />
		<spring:url value="/testsuitelist" var="tsListUrl" />
		<c:if test="${not empty msg}">
			<div class="alert alert-${css} alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
				<strong>${msg}</strong>
			</div>
		</c:if>

		<div class="">
			<button type="button" class="btn btn-primary pull-right" onclick="location.href='${tsListUrl}'">
				<span class="glyphicon glyphicon-star" aria-hidden="true"></span> Test Suite List
			</button>
		</div>

		<div class="panel panel-default">
			<div class="panel-heading clearfix">
				<i class="icon-calendar"></i>
				<h3 class="panel-title">
					<c:choose>
						<c:when test="${ts['new']}">
							<h4>Add Test Suite</h4>
						</c:when>
						<c:otherwise>
							<h4>Update Test Suite</h4>
						</c:otherwise>
					</c:choose>

				</h3>
			</div>
			<div class="panel-body">
				<spring:url value="/testsuite" var="tsActionUrl" />
				<form:form class="form-horizontal" role="form" modelAttribute="ts" action="${tsActionUrl}">
					<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label">Test Suite Name</label>
						<form:hidden path="editMode" />
						<form:hidden path="id" />
						<div class="col-sm-5">
							<c:choose>
								<c:when test="${ts['new']}">
									<form:input path="tsName" maxlength="100" class="form-control" id="tsName" placeholder="Enter Test Suite Name" />
									<form:errors path="tsName" />
								</c:when>
								<c:otherwise>
									<form:hidden path="tsName" />
									<span> ${ts['tsName']} </span>
								</c:otherwise>
							</c:choose>


						</div>
					</div>
					<div class="form-group">
						<label for="lastname" class="col-sm-2 control-label">Description</label>
						<div class="col-sm-5">
							<form:input path="tsDesc" maxlength="200" class="form-control" id="tsDesc" placeholder="Description" />
							<form:errors path="tsDesc" />
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
	<script type="text/javascript">
		activateDashBoard("tsNavLi");
	</script>
</body>
</html>