<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<style>
.bgClr {
	background-color: #F0E68C;
}
</style>
<jsp:include page="header.jsp" flush="true" />
<spring:url value="/updatetc_exe_order/" var="updateOrderUrl" />
<script>
	function goBack() {
		window.history.back();
	}
	function openAttachment(url, id) {
		var winObj = window.open(url + id, "OpenFileTC",
				"menubar=no,width=900,height=700,top=50,left=100");
		winObj.focus();
	}
	function closeModal(){
		var id = document.getElementById("OrderTcId").value;
		removeHighlightRow(id);
		
		$('#myModal').modal('hide');		
	}
	
	function highlightRow(id){
		var obj = document.getElementById("tr_" + id);
		if (obj){
			obj.className="bgClr";
		}	
	}
	
	function removeHighlightRow(id){
		var obj = document.getElementById("tr_" + id);
		if (obj){
			obj.className="";
		}	
	}
	
	function openModal(id, obj) {
		var currOrd = obj.innerHTML;
		document.getElementById("OrderTcId").value = id;
		document.getElementById("currentOrder").value = currOrd;
		document.getElementById("newOrder").value = currOrd;
		
		highlightRow(id);
		$('#myModal').modal('show');
	}
	
	function saveNewOrder(){
		var tcId = document.getElementById("OrderTcId").value;
		var newOrder = document.getElementById("newOrder").value;
		
		if (!isFiniteNumber(newOrder)){
			alert("Please enter valid number");
			document.getElementById("newOrder").focus();
			return;
		}
		
		var divId = "#order_" + tcId;
		 $.ajax({
			type: "POST",
 			url: "${updateOrderUrl}",
 			data: "tcid=" + tcId + "&neworder=" + newOrder,
 			success: function(response){
						$(divId).html(response);
 						
 						},
 			error: function(e){
 					alert('Error: ' + e);
 					}
 		});
		
		 closeModal();
	}
	
	function isFiniteNumber(n) {
		var newVal = parseFloat(n);
		
		if (isNaN(newVal)){
			return false;
		}
		if (newVal <=0){
			return false;
		}
		var strVal = ''+newVal;
		return strVal == n;
	}
</script>
<body>
	<div class="container">
		<jsp:include page="navbar.jsp" flush="true" />
		<spring:url value="/tclist" var="tcUrl" />
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
									<th>Order</th>
									<th>Test Case Name</th>
									<th>Description</th>
									<th>Created By</th>
									<th>Creation Date</th>
									<th></th>
								</tr>
							</thead>
							<c:forEach var="u" items="${list}">
								<tr id="tr_${u.id}">
									<td><a href="${tcUrl}${u.id}" title="view">${u.id}</a>
									</td>
									<td><div id="order_${u.id}" onclick="openModal('${u.id}', this);">${u.executionOrderStr}</div></td>
									<td>${u.tcName}</td>
									<td>${u.tcDesc}</td>
									<td>${u.tcCreatedBy}</td>
									<td>${u.tcCreationDateFmt}</td>
									<td><c:if test="${not empty u.supportFileName}">
											<button type="button" class="btn btn-primary" title="open detailed document"
												onclick="openAttachment('${viewFile}', '${u.id}');">
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

			<!-- Modal -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" onclick="closeModal();" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">Change Order</h4>
						</div>
						<div class="modal-body">
							<label>Enter new execution order:</label> <input type="text" id="newOrder" name="newOrder" size="4" maxlength="4"/> <input
								type="hidden" id="currentOrder" name="currentOrder" /> <input type="hidden" id="OrderTcId" name="OrderTcId" />
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" onclick="closeModal();">Close</button>
							<button type="button" class="btn btn-primary" onclick="saveNewOrder();">Save changes</button>
						</div>
					</div>
				</div>
			</div>

		</form:form>
	</div>
	<script type="text/javascript">
		activateDashBoard("tcNavLi");
	</script>
</body>
</html>