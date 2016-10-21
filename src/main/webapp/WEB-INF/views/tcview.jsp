<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="header.jsp" flush="true" />
<script>
	function UploadAttachment(url, id){
		var winObj = window.open(url + id, "UploadTC", "menubar=no,width=500,height=200,top=200,left=200");
		winObj.focus();
	}
	function openAttachment(url, id){
		var winObj = window.open(url + id, "OpenFileTC", "menubar=no,width=900,height=700,top=50,left=100");
		winObj.focus();
	}
</script>
<body>
	<div class="container">
		<jsp:include page="navbar.jsp" flush="true" />
		<spring:url value="/tclist" var="tcUrl" />
		<spring:url value="/uploadfile/" var="uploadFile" />
		<spring:url value="/files/" var="viewFile" />
		<div class="panel panel-default container-fluid">
			<div class="panel-heading row">

				<div class="col-xs-4">
					<h3 class="panel-title">Test Cases</h3>
				</div>
				<div class="col-xs-4 text-center">
					<h3 class="panel-title">${usecase.moduleName}</h3>
				</div>
				<div class="col-xs-4">
					<h3 class="panel-title">${usecase.useCaseName}</h3>
				</div>
			</div>
			<div class="panel-body">
				<dl class="dl-horizontal">
					<dt>Test Case Name:</dt>
					<dd>${tc.tcName}</dd>

					<dt>Test Case Description:</dt>
					<dd>${tc.tcDesc}</dd>

					<dt>Java Class Name:</dt>
					<dd>${tc.tcJvClassName}</dd>

					<dt>Java Method:</dt>
					<dd>${tc.tcJvMethod}</dd>

					<dt>Created By:</dt>
					<dd>${tc.tcCreatedBy}</dd>
				</dl>
			</div>
			<div class="panel-footer">
				<c:if test="${not empty tc.supportFileName}">
					<button type="button" class="btn btn-primary" onclick="openAttachment('${viewFile}', '${tc.id}');">
						<span class="glyphicon glyphicon-paperclip" aria-hidden="true">Open Test Case Document</span>
					</button>
				</c:if>
				<button type="button" class="btn btn-primary  pull-right" onclick="UploadAttachment('${uploadFile}', '${tc.id}');">
					<span class="glyphicon glyphicon-upload" aria-hidden="true">Upload New Test Case Document</span>
				</button>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		activateDashBoard("tcNavLi");
	</script>
</body>
</html>