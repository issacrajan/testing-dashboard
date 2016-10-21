<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
			<button type="button" class="btn btn-primary pull-right" onclick="location.href='testsuite'">
				<span class="glyphicon glyphicon-star" aria-hidden="true"></span> Add Test Suite
			</button>
		</div>

		<div class="panel panel-primary">
			<div class="panel-heading">
				<div class="panel-title">
					<span class="label label-primary">Test Suites</span>
				</div>
			</div>

			<div class="panel-body">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>#ID</th>
							<th>Name</th>
							<th>Desc</th>
							<th>No.Of TCs</th>
							<th>Action</th>
							<th></th>
						</tr>
					</thead>

					<c:forEach var="ts" items="${list}">
						<tr>
							<td>${ts.id}</td>
							<td>${ts.tsName}</td>
							<td>${ts.tsDesc}</td>
							<td>${ts.cnt}</td>

							<td><spring:url value="/testsuite/${ts.id}" var="tsUrl" />
								<button class="btn btn-info" onclick="location.href='${tsUrl}'">Edit</button>
							</td>
							<td><spring:url value="/testsuitetestcaselist?id=${ts.id}" var="ts2Url" />
								<button class="btn btn-info" onclick="location.href='${ts2Url}'">Show TCs</button>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		activateDashBoard("tsNavLi");
	</script>
</body>
</html>