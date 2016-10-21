<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="header.jsp" flush="true" />

<script type="text/javascript">
<!--
	function startSync() {
		document.forms[0].submit();
	}
//-->
</script>
<body>
	<jsp:include page="navbar.jsp" flush="true" />
	<div class="container">
		<form:form method="post" modelAttribute="synctcForm" >
			<div class="jumbotron">
				<h1 class="display-3">Synchronize Test Case</h1>
				<p class="lead">Click to start Synchronizing Test cases - Ensure that all are checked-in test cases.</p>
				<p>
					<a role="button" href="javascript:startSync();" class="btn btn-lg btn-success">Start Now</a>
				</p>
			</div>
		</form:form>
	</div>
</body>
</html>