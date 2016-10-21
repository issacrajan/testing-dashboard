<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="header.jsp" flush="true" />

<body>
	<div class="container">
		<jsp:include page="navbar.jsp" flush="true" />
		<spring:url value="/usecaseedit" var="ucActionUrl" />
		<spring:url value="/usecaselist/${usecase.moduleName}" var="ucListActionUrl" />
		<c:if test="${not empty msg}">
			<div class="alert alert-${css} alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
				<strong>${msg}</strong>
			</div>
		</c:if>
		<div class="">
			<button type="button" class="btn btn-primary pull-right" onclick="location.href='${ucListActionUrl}'" >
				<span class="glyphicon glyphicon-star" aria-hidden="true"></span> Go to List Page
			</button>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading clearfix">
				<i class="icon-calendar"></i>
				<h3 class="panel-title">Update Use Case Info</h3>
			</div>
			<div class="panel-body">

				<form:form class="form-horizontal" role="form" modelAttribute="usecase" action="${ucActionUrl}">
					<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label">Module</label>

						<div class="col-sm-5">
							<form:hidden path="moduleName" />
							<form:hidden path="id" />
							<span> ${usecase['moduleName']} </span>
						</div>
					</div>
					
					<div class="form-group">
						<label for="lastname" class="col-sm-2 control-label">Use Case ID</label>
						<div class="col-sm-5">
							<span> ${usecase['id']} </span>
						</div>
					</div>
					
					<div class="form-group">
						<label for="lastname" class="col-sm-2 control-label">Use Case Name</label>
						<div class="col-sm-5">
							<span> ${usecase['useCaseName']} </span>
						</div>
					</div>

					<div class="form-group">
						<label for="lastname" class="col-sm-2 control-label">Remarks</label>
						<div class="col-sm-5">
							<form:input path="remarks" maxlength="100" class="form-control" />
						</div>
					</div>


					<div class="form-group">
						<label for="lastname" class="col-sm-2 control-label">Modified By</label>
						<div class="col-sm-5">
							<span> ${usecase['modifiedBy']} </span>
						</div>
					</div>
					<div class="form-group">
						<label for="lastname" class="col-sm-2 control-label">Modified Date</label>
						<div class="col-sm-5">
							<span> ${usecase['modifiedDtFmt']} </span>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-5">
							<button type="submit" class="btn btn-default">Update</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		activateDashBoard("tcNavLi");
	</script>
</body>
</html>