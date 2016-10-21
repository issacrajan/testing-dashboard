<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="header.jsp" flush="true" />


<body>
	<div class="container">
		
	<spring:url value="/uploadfile" var="actionUrl" />
		<c:if test="${not empty msg}">
			<div class="alert alert-${css} alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
				<strong>${msg}</strong>
			</div>
		</c:if>

		<form:form method="POST" modelAttribute="fileBucket" action="${actionUrl}" enctype="multipart/form-data" class="form-horizontal" role="form">
         
            <div class="row">
                <div class="form-group">
                    <label  for="file">Upload Document</label>
                    <div >
                    	<form:hidden path="id"/>
                        <form:input size="10" type="file" path="file" id="file" class="form-control"/>
                        <div class="has-error">
                            <form:errors path="file" class="help-inline"/>
                        </div>
                    </div>
                </div>
            </div>
     
            <div class="row">
                <div class="form-actions floatRight">
                    <input type="submit" value="Upload" class="btn btn-primary btn-sm">
                </div>
            </div>
        </form:form>
	</div>
</body>
</html>