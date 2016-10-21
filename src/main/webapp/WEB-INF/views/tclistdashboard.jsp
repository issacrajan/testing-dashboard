<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="header.jsp" flush="true" />
<script>
	function goBack() {
		window.history.back();
	}
	function openAttachment(url, id){
		var winObj = window.open(url + id, "OpenFileTC", "menubar=no,width=900,height=700,top=50,left=100");
		winObj.focus();
	}
</script>
<body>
	<div class="container">
		<jsp:include page="navbar.jsp" flush="true" />
		<spring:url value="/tcview/" var="tcUrl" />
		<spring:url value="/files/" var="viewFile" />
		
		<form:form class="form-horizontal" method="post" modelAttribute="tc" action="${tcUrl}">

			<div class="panel panel-default container-fluid">
				<div class="panel-heading row">

					<div class="col-xs-2">
						<span class="label label-default">Test Case:</span>
					</div>
					<div class="col-xs-3">
						<c:if test="${not empty usecase.moduleName}">
							<label>Module Name: </label>
							<strong>${usecase.moduleName}</strong>
						</c:if>
					</div>
					<div class="col-xs-4">
						<c:if test="${not empty usecase.useCaseName}">
							<label>Usecase Name: </label>
							<strong>${usecase.useCaseName}</strong>
						</c:if>
					</div>
					<div class="col-xs-3">
						<button type="button" class="btn btn-primary pull-right" onclick="goBack();">
							<span class="glyphicon glyphicon-star" aria-hidden="true"></span> Go Back
						</button>
					</div>
				</div>
				<div class="panel-body">
					<c:if test="${not empty list}">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>#ID</th>
									<th>Test Case Name</th>
									<th>Description</th>
									<th>Created By</th>
									<th>Creation Date</th>
									<th></th>
								</tr>
							</thead>
							<c:forEach var="u" items="${list}">
								<tr>
									<td><a href="${tcUrl}${u.id}" title="view">${u.id}</a>
									</td>
									<td>${u.tcName}</td>
									<td>${u.tcDesc}</td>
									<td>${u.tcCreatedBy}</td>
									<td>${u.tcCreationDateFmt}</td>
									<td><c:if test="${not empty u.supportFileName}">
											<button type="button" class="btn btn-primary" title="open detailed document" onclick="openAttachment('${viewFile}', '${u.id}');">
												<span class="glyphicon glyphicon-paperclip" aria-hidden="true"></span>
											</button>
										</c:if></td>
								</tr>
							</c:forEach>
						</table>
					</c:if>
					<c:if test="${empty list}">
						<strong>${msg2}</strong>
					</c:if>
				</div>
			</div>
		</form:form>
	</div>
	<script type="text/javascript">
		activateDashBoard("tcNavLi");
	</script>
</body>
</html>