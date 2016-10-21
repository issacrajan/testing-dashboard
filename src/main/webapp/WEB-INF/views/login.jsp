<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="header.jsp" flush="true" />

<script type="text/javascript">
<!--
	function processLogin() {
		document.forms[0].action = "login";
		document.forms[0].method = "post";
		document.forms[0].submit();
	}
//-->
</script>
<body>
	<jsp:include page="navbar_login.jsp" flush="true" />
	<div class="container">
		<c:if test="${not empty msg}">
			<div class="alert alert-${css} alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
				<strong>${msg}</strong>
			</div>
		</c:if>
		<spring:url value="/login" var="loginActionUrl" />
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<strong class="">Login</strong>
					</div>
					<div class="panel-body">
						<form:form modelAttribute="login" class="form-horizontal" role="form" action="${loginActionUrl}">
							<div class="form-group">
								<label for="userId" class="col-sm-3 control-label">User ID</label>
								<div class="col-sm-9">
									<form:input path="userId" class="form-control" required="" type="text" autofocus="y" />
									<form:errors path="userId" class="control-label" />
								</div>
							</div>
							<div class="form-group">
								<label for="userPwd" class="col-sm-3 control-label">Password</label>
								<div class="col-sm-9">
									<form:input path="userPwd" class="form-control" required="" type="password" />
									<form:errors path="userPwd" class="control-label" />
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-3 col-sm-9">
									<div class="checkbox">
										<label class=""> <input class="" type="checkbox" name="RememberMe" value="Y">Remember me </label>
									</div>
								</div>
							</div>
							<div class="form-group last">
								<div class="col-sm-offset-3 col-sm-9">
									<button type="button" onclick="processLogin();" class="btn btn-success btn-sm">Sign in </button>
									<button type="reset" class="btn btn-default btn-sm">Reset</button>
								</div>
							</div>
						</form:form>
					</div>
					<div class="panel-footer">
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>