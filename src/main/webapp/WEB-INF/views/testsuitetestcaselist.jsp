<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytag" uri="/WEB-INF/testingproj.tld"%>


<style>
.bgClr {
	background-color: #F0E68C;
}
</style>
<jsp:include page="header.jsp" flush="true" />
<spring:url value="/updatets_tc_exe_order/" var="updateOrderUrl" />
<<script type="text/javascript">
<!--

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
	var tsId = document.getElementById("id").value;
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
			data: "tsid=" + tsId + "&tcid=" + tcId + "&neworder=" + newOrder,
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


//-->
</script>
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
		<spring:url value="/testsuitelist" var="tsListUrl" />
		<spring:url value="/testsuiteaddtestcase?tsid=${ts.id}" var="tsAddTcUrl" />
		<spring:url value="/testsuitetestcaselist?id=${ts.id}" var="tsActionUrl" />
		
		<spring:url value="/tcview/" var="tcviewUrl" />
		<div class="">
			<button type="button" class="btn btn-primary pull-right" onclick="location.href='${tsListUrl}'">
				<span class="glyphicon glyphicon-star" aria-hidden="true"></span> Goto Test Suite List Page
			</button>
		</div>

		<form:form class="form-horizontal" role="form" modelAttribute="ts" action="${tsActionUrl}">
			<form:hidden path="id"/>
			<div class="panel panel-primary">
				<div class="panel-heading">
					<div class="panel-title">
						<span class="label label-primary">Test Cases For Test Suite : </span><label>${ts.tsName}</label>
					</div>
				</div>

				<div class="panel-body">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>#ID</th>
								<th>Exe.Order</th>
								<th>Module Name</th>
								<th>Use Case Name</th>
								<th>TC</th>
							</tr>
						</thead>

						<c:forEach var="ts" items="${tcList.list}">
							<tr id="tr_${ts.tcid}">
								<td><a href="${tcviewUrl}${ts.tcid}" target="_blank">${ts.tcid}</a></td>
								<td><div id="order_${ts.tcid}" onclick="openModal('${ts.tcid}', this);">${ts.executionOrder}</div></td>
								<td>${ts.modulename}</td>
								<td>${ts.usecasename}</td>
								<td>${ts.tcname}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<div class="panel-footer">
					<div class="">
						<button type="button" class="btn btn-primary" onclick="location.href='${tsAddTcUrl}'">
							<span class="glyphicon glyphicon-star" aria-hidden="true"></span> Add Test Case to ${ts.tsName} Suite
						</button>
					</div>
					<div >
						<mytag:Page uri="${tsActionUrl}" start="${tcList.start}" />
					</div>
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
		activateDashBoard("tsNavLi");
	</script>
</body>
</html>