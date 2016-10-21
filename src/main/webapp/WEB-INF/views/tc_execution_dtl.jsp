<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mytag" uri="/WEB-INF/testingproj.tld"%>

<jsp:include page="header.jsp" flush="true" />
<style>
.colr-yellow { 
	background-color: #ffa;
}
.colr-red { 
	background-color: #DC143C;
	font-size: 20px;
}
</style>
<script>
	function searchExeRes() {
		document.getElementById("pageAction").value = "FETCH";
		document.forms[0].submit();
	}

	function openAttachment(url, id) {
		var winObj = window.open(url + id, "OpenExeResultFile",
				"menubar=no,width=900,height=700,top=50,left=100");
		winObj.focus();

	}
</script>
<body>
	<div class="container">
		<jsp:include page="navbar.jsp" flush="true" />
		<spring:url value="/tc_execution_dtl?id=${r.tcRunHdrId}" var="tcExeResultUrl" />
		<spring:url value="/tcview/" var="tcUrl" />
		<spring:url value="/tc_execution_dtl_file?id=" var="viewFileUrl" />
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
				<div class="panel-heading">
					<strong>Summary</strong>
				</div>
				<div class="panel-body">

					<div>
						<label class="col-xs-2">Run Date:</label> <label class="colr-yellow col-xs-2 ">${r.tcRunHdr.runDateFmt}</label>
					</div>
					<div>
						<label class="col-xs-2">Run User:</label> <label class="colr-yellow col-xs-2">${r.tcRunHdr.runUser}</label>
					</div>
					<div>
						<label class="col-xs-2">Success/Failure:</label> 
						<label class="col-xs-2">
						<span class="">${r.tcRunHdr.successCnt} / </span>
						<span class="colr-red">${r.tcRunHdr.failureCnt}</span> 
						</label>
					</div>
					<div>
						<label class="col-xs-2">Run Group:</label> <label class="col-xs-10">${r.tcRunHdr.runGroupId}</label>
					</div>
				</div>
			</div>

			<div class="panel panel-info">
				<div class="panel-heading">
					<strong>Result Detail</strong>
				</div>
				<div class="panel-body">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>TC ID</th>
								<th>Test Case</th>
								<th>Status</th>
								<th>Screen Shot?</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach var="u" items="${r.listDtl}" varStatus="status">
								<tr
									<c:if test="${u.msgFound}"> class="clickable" data-toggle="collapse" data-target="#row${u.testResultDetId}" </c:if>>
									<td><c:if test="${u.msgFound}">
											<i class="glyphicon glyphicon-plus"></i>
										</c:if> <a href="${tcUrl}${u.tcId}" target="_blank">${u.tcId}</a>
									</td>
									<td>${u.tcName}</td>
									<td>${u.tcCompStatus}</td>
									<td><c:if test="${u['screenShotFlag']}">

											<button type="button" class="btn btn-primary"
												onclick="openAttachment('${viewFileUrl}', '${u.testResultDetId}');">
												<span class="glyphicon glyphicon-paperclip" aria-hidden="true"></span>
											</button>

										</c:if></td>
								</tr>
								<c:forEach var="msg" items="${r.listDtl[status.index].msgList}">
									<tr class="collapse" id="row${r.listDtl[status.index].testResultDetId}">
										<td colspan="4">${msg}</td>

									</tr>
								</c:forEach>
							</c:forEach>
						</tbody>
					</table>

				</div>
				<div class="panel-footer">
					<mytag:Page uri="${tcExeResultUrl}" start="${r.start}" />
				</div>
			</div>
		</form:form>
	</div>
	<script type="text/javascript">
		activateDashBoard("testResLi");
	</script>
</body>
</html>