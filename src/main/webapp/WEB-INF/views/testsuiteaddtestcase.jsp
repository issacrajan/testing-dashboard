<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytag" uri="/WEB-INF/testingproj.tld"%>

<jsp:include page="header.jsp" flush="true" />
<spring:url value="/testsuiteaddtestcase" var="tsActionUrl" />
<spring:url value="/testsuiteaddtestcase?tsid=${ts.tsHdr.id}&ucid=${ts.ucid}" var="tsPagetUrl" />

<script>
	function fetchUC() {
		var tsId = document.getElementById("tsHdr.id").value;
		var e = document.getElementById("moduleName");
		var moduleValue = e.options[e.selectedIndex].value;
		if (moduleValue == "") {
			alert("Please select a Module Name");
			return;
		}
		document.location.href="${tsActionUrl}?tsid=" + tsId + "&m=" + moduleValue;
	}

	function fetchTC() {
		var tsId = document.getElementById("tsHdr.id").value;
		var e = document.getElementById("ucid");
		var ucValue = e.options[e.selectedIndex].value;
		if (ucValue == "") {
			alert("Please select a Use Case");
			return;
		}

		document.location.href="${tsActionUrl}?tsid=" + tsId + "&ucid=" + ucValue;
	}

	function saveClick() {
		document.forms[0].pageAction.value = "SAVE";
		document.forms[0].submit();
	}
	
	function checkAll(obj){
		var checkMe = false;
		if (obj.checked){
			checkMe = true;
		}
		var inputObjs = document.getElementsByTagName("input");
		var len = inputObjs.length;
		for (var i = 0; i < len; i++){
			if (inputObjs[i].type == "checkbox" ){
				chkName = inputObjs[i].name;
				if (chkName.indexOf("selected") >0){
					inputObjs[i].checked = checkMe;
				}
			}
		}
	}
	function validateInput(){
		var inputObjs = document.getElementsByTagName("input");
		var len = inputObjs.length;
		var valid = true;
		var chkName = "";
		var inpName = "";
		var val;
		var txtObj;
		for (var i = 0; i < len; i++){
			if (inputObjs[i].type== "checkbox" ){
				chkName = inputObjs[i].name;
				inpName = chkName.replace("selected", "executionOrder");
				txtObj = document.forms[0].elements[inpName];
				
				if(inputObjs[i].checked){
					val = txtObj.value;
					
					valid = isFiniteNumber( val );
					if (!valid){
						txtObj.style.backgroundColor="red";
						txtObj.title = "invalid number";
						break;
					} else {
						txtObj.style.backgroundColor="";
						txtObj.title = "";
					}
				} else {
					txtObj.value="";
				}
				
			}
		}
		return valid;
	}
	
	function isFiniteNumber(n) {
		var newVal = parseFloat(n);
		if (isNaN(newVal)){
			return false;
		}
		if (newVal < 0){
			return false;
		}
		var strVal = ''+newVal;
		return (strVal == n);
	}
</script>
<body>
	<div class="container">
		<jsp:include page="navbar.jsp" flush="true" />
		<spring:url value="/testsuitelist" var="tsListUrl" />
		<spring:url value="/testsuitetestcaselist?id=${ts.tsHdr.id}" var="tcListActionUrl" />
		<spring:url value="/tcview/" var="tcUrl" />
		
		<c:if test="${not empty msg}">
			<div class="alert alert-${css} alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
				<strong>${msg}</strong>
			</div>
		</c:if>

		<div class="">
			<button type="button" class="btn btn-primary pull-right" onclick="location.href='${tsListUrl}'">
				<span class="glyphicon glyphicon-star" aria-hidden="true"></span> Test Suite List
			</button>
		</div>
		<div class="">
			<button type="button" class="btn btn-primary pull-right" onclick="location.href='${tcListActionUrl}'">
				<span class="glyphicon glyphicon-star" aria-hidden="true"></span> Test Case page
			</button>
		</div>
		<form:form role="form" modelAttribute="ts" action="${tsActionUrl}">
			<div class="panel panel-default">
				<div class="panel-heading clearfix">
					<i class="icon-calendar"></i>
					<h3 class="panel-title">
						Test Cases for Test Suite: <label>${ts.tsHdr.tsName}</label>
					</h3>

					<div class="form-group">
						<label for="firstname" class="control-label">Module Name:</label>

						<form:hidden path="tsHdr.id" />
						<form:hidden path="tsHdr.tsName" />
						<form:hidden path="pageAction" />
						<div>
							<form:select path="moduleName" onchange="fetchUC();">
								<form:option value="" label="--Select--" />
								<form:options items="${allModule}" itemValue="moduleName" itemLabel="moduleDesc" />
							</form:select>
						</div>
					</div>
					<div class="form-group">
						<label for="lastname" class="control-label">Use Case Name:</label>
						<div>
							<form:select path="ucid" onchange="fetchTC();">
								<form:option value="" label="--Select--" />
								<form:options items="${allUC}" itemValue="id" itemLabel="useCaseName" />
							</form:select>
						</div>
					</div>
				</div>

				<c:choose>
					<c:when test="${empty ts.tcList}">
						<div class="panel-body">
							<h3>Please select Module/ Use Case</h3>
						</div>
					</c:when>
					<c:otherwise>
						<div class="panel-body">
							<table class="table table-hover">
								<thead>
									<tr>
										<th><input type="checkbox" name="selectAll" id="selectAll" onclick="checkAll(this);"/></th>
										<th>ID</th>
										<th>Name</th>
										<th>Desc</th>
									</tr>
								</thead>
								<c:forEach items="${ts.tcList}" var="record" varStatus="status">
									<tr>
										<td><form:checkbox path="tcList[${status.index}].selected" value="Y" /></td>
										
										<td><a href="${tcUrl}${record.id}" target="_blank">${record.id}</a> <form:hidden path="tcList[${status.index}].id" /> 
										<form:hidden path="tcList[${status.index}].executionOrder" /></td>
										<td>${record.tcName}<form:hidden path="tcList[${status.index}].tcName" /></td>
										<td>${record.tcDesc}<form:hidden path="tcList[${status.index}].tcDesc" /></td>
									</tr>
								</c:forEach>
							</table>
						</div>

						<div class="panel-footer">
							<div>
							<mytag:Page uri="${tsPagetUrl}" start="${ts.start}" />
								<button type="button" onclick="saveClick();" class="btn btn-primary">Update Changes</button>
							</div>
						</div>
					</c:otherwise>
				</c:choose>

			</div>
		</form:form>
	</div>
	<script type="text/javascript">
		activateDashBoard("tsNavLi");
	</script>
</body>
</html>