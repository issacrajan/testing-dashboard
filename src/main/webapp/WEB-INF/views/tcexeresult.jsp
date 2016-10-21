<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="header.jsp" flush="true" />

<script>
	function searchExeRes() {
		document.getElementById("pageAction").value = "FETCH";
		document.forms[0].submit();
	}
</script>
<body>
	<div class="container">
		<jsp:include page="navbar.jsp" flush="true" />
		<spring:url value="/tcexeresult" var="tcExeResultUrl" />

		<c:if test="${not empty msg}">
			<div class="alert alert-${css} alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
				<strong>${msg}</strong>
			</div>
		</c:if>
		<form:form class="form-horizontal" method="post" modelAttribute="r" action="${tcExeResultUrl}">
			<form:hidden path="pageAction" />
			<div class="panel panel-primary">
				<div class="panel-heading">Search - Test Result</div>
				<div class="panel-body">

					<div class="form-group">
						<label for="inputEmail" class="control-label col-xs-2">From Date</label>
						<div class="col-xs-2">
							<form:input path="fromDtStr" class="form-control" placeholder="MM/dd/YYYY"/>
							<form:errors path="fromDtStr" />
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword" class="control-label col-xs-2">Thru Date</label>
						<div class="col-xs-2">
							<form:input path="thruDtStr" class="form-control" placeholder="MM/dd/YYYY"/>
							<form:errors path="thruDtStr" />
						</div>
					</div>

					<div class="form-group">
						<div class="col-xs-offset-2 col-xs-10">
							<button type="submit" class="btn btn-primary">Search</button>
						</div>
					</div>
				</div>
				<c:if test="${ResultNotFound == 'Y'}">
					<div class="panel-footer">NO Records Found</div>
				</c:if>
			</div>
		</form:form>
		<c:if test="${not empty r.list}">
			<div class="panel panel-primary">
				<div class="panel-heading">Test Result</div>
				<div class="panel-body">

					<table class="table table-hover">
						<thead>
							<tr>
								<th>#ID</th>
								<th>Run Date</th>
								<th>Run By</th>
								<th>Detail</th>
								<th>Success Count</th>
								<th>Failure Count</th>
								<th>Action</th>
							</tr>
						</thead>

						<c:forEach var="u" items="${r.list}">
							<tr>
								<td>${u.id}</td>
								<td>${u.runDateFmt}</td>
								<td>${u.runUser}</td>
								<td>${u.runGroupId}</td>
								<td>${u.successCnt}</td>
								<td>${u.failureCnt}</td>

								<td><spring:url value="/tc_execution_dtl?id=${u.id}" var="dtlUrl" />
									<button class="btn btn-info" onclick="location.href='${dtlUrl}'">Details</button>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</c:if>
	</div>
	<script type="text/javascript">
		activateDashBoard("testResLi");
	</script>
</body>
</html>