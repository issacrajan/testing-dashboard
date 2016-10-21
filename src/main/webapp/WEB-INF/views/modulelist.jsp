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

		<div class="panel panel-primary">
			<div class="panel-heading">
				<div class="panel-title">
					<span class="label label-primary">Modules List</span>
				</div>
			</div>

			<div class="panel-body">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Name</th>
							<th>Desc</th>
							<th>Action</th>
						</tr>
					</thead>

					<c:forEach var="module" items="${list}">
						<tr>
							<td>${module.moduleName}</td>
							<td>${module.moduleDesc}</td>
							<td><spring:url value="/moduleedit?id=${module.moduleName}" var="moduleUrl" />
								<button class="btn btn-info" onclick="location.href='${moduleUrl}'">Edit</button>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>