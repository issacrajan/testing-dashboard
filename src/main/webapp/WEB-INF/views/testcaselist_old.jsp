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

		<h1>All Users</h1>

		<table class="table table-hover">
			<thead>
				<tr>
					<th>#ID</th>
					<th>Name</th>
					<th>Last Login Date/Time</th>
					<th>Action</th>
				</tr>
			</thead>

			<c:forEach var="user" items="${list}">
				<tr>
					<td>${user.userId}</td>
					<td>${user.userName}</td>
					<td>${user.lastLogingTime}</td>

					<td><spring:url value="/users/${user.userId}" var="userUrl" /> <spring:url
							value="/users/${user.userId}/delete" var="deleteUrl" /> <spring:url value="/users/${user.userId}/update"
							var="updateUrl" />

						<button class="btn btn-info" onclick="location.href='${userUrl}'">Query</button>
						<button class="btn btn-primary" onclick="location.href='${updateUrl}'">Update</button>
						<button class="btn btn-danger" onclick="this.disabled=true;post('${deleteUrl}')">Delete</button>
					</td>
				</tr>
			</c:forEach>
		</table>

	</div>
</body>
</html>