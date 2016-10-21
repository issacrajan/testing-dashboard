<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title>MS123</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<spring:url value="/resources/css/bootstrap.css" var="bootstrapCss" />
<spring:url value="/resources/css/ms.css" var="msCss" />

<spring:url value="/resources/js/jquery.js" var="jqueryJs" />
<spring:url value="/resources/js/bootstrap.js" var="bootstrapJs" />

<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${msCss}" rel="stylesheet" />
<script src="${jqueryJs}"></script>

<script src="${bootstrapJs}"></script>

<script type="text/javascript">
	function activateDashBoard(id){
		var tmp = document.getElementById(id);
		if (tmp){
			tmp.className="active";
		}
	}
</script>
</head>