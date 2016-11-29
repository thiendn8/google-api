<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%--
  Created by IntelliJ IDEA.
  User: FRAMGIA\dang.ngoc.thien
  Date: 15/11/2016
  Time: 10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Request success</title>
    <jsp:include page="../template-layout/header.jsp"/>
</head>
<body>
<div class="row">
    <div class="col-lg-12 text-center">
        <sec:authorize access="isAuthenticated()">
            <sec:authentication property="principal.username" var="username" />
            <h3><b><c:out value="${username}"/></b>  have process success an the request to the system </h3> <br/>
            <h3><a href="/">Home</a></h3>
        </sec:authorize>

    </div>

</div>
<!-- /container -->
<jsp:include page="../template-layout/js-template.jsp"/>
</body>
<jsp:include page="../template-layout/footer.jsp"/>
</html>
