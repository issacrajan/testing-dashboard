<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<spring:url value="/dashboard" var="dashboardActionUrl" />
<spring:url value="/mydashboard" var="myDashboardActionUrl" />

<spring:url value="/userlist" var="userListActionUrl" />
<spring:url value="/modulelist" var="moduleListActionUrl" />
<spring:url value="/usecaselist" var="ucListActionUrl" />
<spring:url value="/testcaselist" var="tcListActionUrl" />
<spring:url value="/testsuitelist" var="tsListActionUrl" />


<spring:url value="/tcexeresult" var="tcExeRUrl" />

<spring:url value="/myprofile" var="myprofileUrl" />
<spring:url value="/logout" var="logoutUrl" />

<nav class="navbar navbar-default" role="navigation">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#example-navbar-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span
				class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="#"><spring:message code="project.name"/></a>
	</div>
	<div class="collapse navbar-collapse" id="example-navbar-collapse">
		
		<ul class="nav navbar-nav navbar-right">
			<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Dashboard<b class="caret"></b>
			</a>
				<ul class="dropdown-menu">
					<li id="sysDashLi" ><a href="${dashboardActionUrl}">System</a></li>
					<li id="myDashLi" ><a href="${myDashboardActionUrl}">User</a></li>
				</ul>
			</li>
			<li id="tcNavLi" ><a href="${ucListActionUrl}">Test Case</a>
			</li>
			<li id="tsNavLi" ><a href="${tsListActionUrl}">Test Suite</a>
			</li>
			<li id="testResLi" ><a href="${tcExeRUrl}">Test Result</a>
			</li>
			<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Admin<b class="caret"></b> </a>
				<ul class="dropdown-menu">
					<c:if test="${sessionScope.jvuserrole == 'A'}">
						<li><a href="${userListActionUrl}">Users</a></li>
					</c:if>
					<li><a href="${moduleListActionUrl}">Modules</a></li>
				</ul>
			</li>
			<li ><a href="${myprofileUrl}">Profile</a></li>
			<li ><a href="${logoutUrl}">logout</a></li>
		</ul>

	</div>
</nav>
