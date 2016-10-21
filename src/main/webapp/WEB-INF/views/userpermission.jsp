<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="header.jsp" flush="true" />
<script type="text/javascript">
<!--

	function saveClick() {
		//document.forms[0].pageAction.value = "SAVE";
		document.forms[0].submit();
	}

//-->
</script>
<body>
	<div class="container">
		<jsp:include page="navbar.jsp" flush="true" />
		<spring:url value="/userpermission" var="userActionUrl" />
		<c:if test="${not empty msg}">
			<div class="alert alert-${css} alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
				<strong>${msg}</strong>
			</div>
		</c:if>
		<form:form class="form-horizontal" role="form" modelAttribute="up" action="${userActionUrl}">
			<form:hidden path="userId" />
			<form:hidden path="userName" />

			<div class="panel panel-default">
				<div class="panel-heading clearfix">
					<i class="icon-calendar"></i>
					<h3 class="panel-title">User Permission User ID/Name: <label> ${up['userId']} </label> <label> ${up['userName']} </label></h3>
					
				</div>
				<div class="panel-body">
					<ul class="nav nav-tabs">
						<li class="active"><a data-toggle="tab" href="#home">Test Suite Execution</a>
						</li>
						<li><a data-toggle="tab" href="#menu1">Use Case Execution</a>
						</li>
					</ul>

					<div class="tab-content">
						<div id="home" class="tab-pane fade in active">
							<h3>Test Suite</h3>
							<table class="table table-hover">
								<thead>
									<tr>
										<th>Select</th>
										<th>Name</th>
									</tr>
								</thead>
								<c:forEach items="${up.tsList}" var="record" varStatus="status">
									<tr>
										<td><form:checkbox path="tsList[${status.index}].selected" value="Y" />
										<td>${record.tsName}<form:hidden path="tsList[${status.index}].tsName" /> <form:hidden path="tsList[${status.index}].id" /></td>
									</tr>
								</c:forEach>
							</table>
						</div>
						<div id="menu1" class="tab-pane fade">
							<h3>Test Case</h3>
							<table class="table table-hover">
								<thead>
									<tr>
										<th>Select</th>
										<th>Name</th>
									</tr>
								</thead>
								<c:forEach items="${up.ucList}" var="record" varStatus="status">
									<tr>
										<td><form:checkbox path="ucList[${status.index}].selected" value="Y" />
										<td>${record.useCaseName}
										<form:hidden path="ucList[${status.index}].useCaseName" />
										<form:hidden path="ucList[${status.index}].id" /></td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
				<div class="panel-footer">
					<div>
						<button type="button" onclick="saveClick();" class="btn btn-primary">Save</button>
					</div>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>