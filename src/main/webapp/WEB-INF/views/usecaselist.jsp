<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="header.jsp" flush="true" />

<script>
	function fetchUc() {
		var e = document.getElementById("moduleName");
		var strModuleName = e.options[e.selectedIndex].value;
		if (strModuleName) {
			document.getElementById("pageAction").value = "FETCH";
			document.forms[0].submit();
		} else {
			alert("Please select a Module");
		}
	}
</script>
<body>
	<div class="container">
		<jsp:include page="navbar.jsp" flush="true" />
		<spring:url value="/usecaselist" var="ucUrl" />
		<spring:url value="/usecaseedit/" var="ucEditUrl" />
		<c:if test="${not empty msg}">
			<div class="alert alert-${css} alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
				<strong>${msg}</strong>
			</div>
		</c:if>

		<div class="panel panel-default">

			<div class="panel-heading">

				<form:form class="form-inline" method="post" modelAttribute="usecase" action="${ucUrl}">
					<form:hidden path="pageAction" />
					<div class="form-group">
						<label for="firstname">Select Module</label>
						<form:select path="moduleName">
							<form:option value="" label="--Select--" />
							<form:options items="${allModule}" itemValue="moduleName" itemLabel="moduleDesc" />
						</form:select>
					</div>
					<div class="form-group">
						<button class="btn btn-info" type="button" onclick="fetchUc();">
							Fetch
							<spring:message code="usecase.name" />
						</button>
					</div>
				</form:form>
			</div>

			<div class="panel-body">
				<c:if test="${not empty list}">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>#ID</th>
								<th>Use Case Name</th>
								<th>Remarks</th>
								<th>Modified By</th>
								<th>Modified Date</th>
								<th>Action</th>
							</tr>
						</thead>

						<c:forEach var="u" items="${list}">
							<tr>
								<td><a href="${ucEditUrl}${u.id}">${u.id}</a>
								</td>
								<td>${u.useCaseName}</td>
								<td>${u.remarks}</td>
								<td>${u.modifiedBy}</td>
								<td>${u.modifiedDtFmt}</td>
								<td><spring:url value="/tclist/${u.id}" var="tclistUrl" />
									<button class="btn btn-info" type="button" onclick="location.href='${tclistUrl}'">
										Test Cases <span class="badge">${u.tcCnt}</span>
									</button>
								</td>

							</tr>
						</c:forEach>
					</table>
				</c:if>
				<c:if test="${empty list}">
					<strong>${msg2}</strong>
				</c:if>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		activateDashBoard("tcNavLi");
	</script>
</body>
</html>