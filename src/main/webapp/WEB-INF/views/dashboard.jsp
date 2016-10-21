<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="header.jsp" flush="true" />

<body>
	<jsp:include page="navbar.jsp" flush="true" />
	<spring:url value="/tclistdashboard/M" var="mthListUrl" />
	<spring:url value="/tclistdashboard/W" var="weekListUrl" />
	<spring:url value="/tclistdashboard/D" var="dayListUrl" />
	<spring:url value="/tc_execution_dtl?id=" var="executionDtlUrl" />

	<div id="page-wrapper" style="min-height: 161px;">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Dashboard</h1>
			</div>
		</div>

		<div data-example-id="thumbnails-with-custom-content" class="bs-example">
			<div class="row">
				<div class="col-sm-6 col-md-4">
					<div class="thumbnail">

						<div class="caption">
							<h3>Added This Month</h3>
							<h1>${mthCnt}</h1>
							<p>
								<a role="button" class="btn btn-primary" onclick="location.href='${mthListUrl}'">Details</a></a>
							</p>
						</div>
					</div>
				</div>
				<div class="col-sm-6 col-md-4">
					<div class="thumbnail">

						<div class="caption">
							<h3>Added This Week</h3>
							<h1>${weekCnt}</h1>
							<p>
								<a role="button" class="btn btn-primary" onclick="location.href='${weekListUrl}'">Details</a>
							</p>
						</div>
					</div>
				</div>
				<div class="col-sm-6 col-md-4">
					<div class="thumbnail">

						<div class="caption">
							<h3>Added Today</h3>
							<h1>${todayCnt}</h1>
							<p>
								<a role="button" class="btn btn-primary" onclick="location.href='${dayListUrl}'">Detail</a>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>


		<div class="panel panel-primary">
			<div class="panel-heading">Recent Execution Details</div>
			<table class="table">
				<thead>
					<tr>
						<th>#</th>
						<th>Date</th>
						<th>Group</th>
						<th>Success Count</th>
						<th>Failure Count</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="ex" items="${exList}" varStatus="status">
						<tr>
							<th scope="row">${status.index+1}</th>
							<td><a href="${executionDtlUrl}${ex.id}">${ex.runDateAsStr}</a>
							</td>
							<td>${ex.runGroupId}</td>
							<td>${ex.successCnt}</td>
							<td>${ex.failureCnt}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<div class="panel panel-primary">
			<div class="panel-heading">Module-wise Details</div>
			<table class="table">
				<thead>
					<tr>
						<th>#</th>
						<th>Module</th>
						<th>Use Case</th>
						<th>TC Count</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="module" items="${mList}" varStatus="status">
						<tr>
							<th scope="row">${status.index+1}</th>
							<td>${module.moduleName}</td>
							<td>${module.useCaseName}</td>
							<td>${module.tcCnt}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<script>
		activateDashBoard("sysDashLi");
	</script>
</body>
</html>