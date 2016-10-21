<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mytag" uri="/WEB-INF/testingproj.tld"%>

<style>
.showMe {
	display: inline
}
.hideMe {
	display: none
}
</style>
<jsp:include page="header.jsp" flush="true" />
<spring:url value="/tc_execution_file_view/" var="tcFileOpen" />
<script>
	function searchExeRes() {
		document.getElementById("pageAction").value = "FETCH";
		document.forms[0].submit();
	}
	
	function openAttachment(id){
		function openAttachment(url, id){
			var winObj = window.open(url + id, "OpenExeResultFile", "menubar=no,width=900,height=700,top=50,left=100");
			winObj.focus();
		}
	}
	
	function ShowFile(fileId){
		var imageEle = document.getElementById("myImage")
		imageEle.src = "${tcFileOpen}" + fileId;
		imageEle.className="showMe";
	}
</script>
<body>
	<div class="container">
		
		
		<spring:url value="/tcview/" var="tcUrl" />
		<c:if test="${not empty msg}">
			<div class="alert alert-${css} alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
				<strong>${msg}</strong>
			</div>
		</c:if>
		<form:form class="form-horizontal" method="post" modelAttribute="r" >
			

			<div class="panel panel-info">
				<div class="panel-heading">
					<strong>Result Detail- File</strong>
				</div>
				<div class="panel-body">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>File Name</th>
								<th>Remarks</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="u" items="${r.tcFile}" varStatus="status">
								<tr	>
									<td><a href="#" onclick="ShowFile('${u.id}')">${u.fileName}</a></td>
									<td>${u.remarks}</td>
								</tr>
								
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="panel-footer">
					<img class="hideMe" id="myImage" src=""/>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>