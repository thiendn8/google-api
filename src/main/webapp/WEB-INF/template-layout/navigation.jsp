<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%--
  Created by IntelliJ IDEA.
  User: FRAMGIA\dang.ngoc.thien
  Date: 11/11/2016
  Time: 13:18
  To change this template use File | Settings | File Templates.
--%>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <div>
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                    <form id="logoutForm" method="POST" action="${contextPath}/logout">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                    <h2 style="color: #2d2d30">Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>
                </c:if>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-1 text-center">
            <a href="/">Home</a>
        </div>
        <div class="col-md-2 text-left">
            <a href="/myRequestTimeSheet">My request</a>
        </div>
        <sec:authorize ifAnyGranted="Approver,Manager">
            <div class="col-md-2 text-left">
                <a href="/listBookingRoom">List booking meeting room</a>
            </div>
        </sec:authorize>
        <sec:authorize ifAnyGranted="Approver,Manager">
        <div class="col-md-2 text-left">
            <a href="/approveTimesheet">Approve request</a>
        </div>
        </sec:authorize>
        <sec:authorize ifAnyGranted="Manager">
        <div class="col-md-2 text-left">
            <a href="/settingService">Service setting </a>
        </div>
        </sec:authorize>
    </div>
</nav>
