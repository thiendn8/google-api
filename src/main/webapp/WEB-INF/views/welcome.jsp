<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal.username" var="username" />
</sec:authorize>
<sec:authorize access="!isAuthenticated()">
    <sec:authentication property="principal" var="username" />
</sec:authorize>
<%--
  Created by IntelliJ IDEA.
  User: FRAMGIA\dang.ngoc.thien
  Date: 15/11/2016
  Time: 16:35
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Employee Detail</title>
    <jsp:include page="../template-layout/header.jsp"/>
</head>
<body>
<div class="container">
    <jsp:include page="../template-layout/navigation.jsp"/>
    <div>
        <c:if test="${employeeDetail != null}">
            <div class="row wrapTitle">
                <div class="col-md-3 text-left">Employee code</div>
                <div class="col-md-6 text-left"><c:out value="${employeeDetail.staffAndCode.employeeCode}"/></div>
                <div class="col-md-3 text-left">
                    Today's date: <%= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date())%>
                </div>
            </div>
            <div class="row wrapTitle">
                <div class="col-md-3 text-left">Staff name</div>
                <div class="col-md-6 text-left"><c:out value="${employeeDetail.staffAndCode.staffName}"/></div>
                <div class="col-md-3 text-right">
                    <select class="form-control" id="dateChange" onchange="changeDataByDate(this.value)">
                        <c:forEach items="${listMonth}" var="listMonth">
                           <option value="${listMonth}" <c:if test="${indexSelect == listMonth}">selected="selected"</c:if>>${listMonth}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="modal-body">
                <table class="table-bordered" style="border: 0px solid #ddd;">
                    <thead>
                    <tr class="row active">
                        <th class="col-md-4">Date</th>
                        <th class="col-md-3">Time-In</th>
                        <th class="col-md-3">Time-Out</th>
                        <th class="col-md-2">Request</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${employeeDetail.listTimes}" var="listTimes">
                        <tr class="row warning">
                            <c:if test="${ not empty listTimes.entityTimes.timeIn && not empty listTimes.entityTimes.timeout}">
                                <td class="col-md-4">
                                    <div class="row">
                                        <span class="col-md-6 text-left"><c:out value="${listTimes.dateTime}"/></span>
                                        <c:if test="${not empty listRequestApprove || listRequestApprove != null}">
                                            <c:forEach items="${listRequestApprove}" var="listRequestApprove">
                                                <c:set var="currentDate" value="${fn:substringBefore(listRequestApprove.timeTo,' ')}" />
                                                <c:if test="${not empty currentDate}">
                                                    <c:if test="${currentDate == listTimes.dateTime}">
                                                        <c:set var="thien" value="${listRequestApprove.status}"/>
                                                        <span class="col-md-6 text-right label label-danger">${listRequestApprove.typeRequest} &nbsp; ${listRequestApprove.timeOffset}</span>
                                                    </c:if>
                                                </c:if>
                                            </c:forEach>
                                        </c:if>
                                    </div>
                                </td>
                                <td class="col-md-3"><c:out value="${listTimes.entityTimes.timeIn}"/></td>
                                <td class="col-md-3"><c:out value="${listTimes.entityTimes.timeout}"/></td>
                                <td class="col-md-2 text-center">
                                <%--<c:if test="${fn:length(listRequestApprove) > 0}">
                                    <c:forEach items="${listRequestApprove}" var="listRequestApprove">
                                        <c:set var="currentDate" value="${fn:substringBefore(listRequestApprove.timeTo,' ')}" />
                                        <c:if test="${currentDate eq listTimes.dateTime}">
                                            <input type="hidden" id="transdata" value="${employeeDetail.staffAndCode.employeeCode},${employeeDetail.staffAndCode.staffName}"/>
                                            <button class="btn btn-warning magin-button" onclick="requestTime($('#transdata').val(),'${listTimes.dateTime}')" disabled
                                                    data-toggle="modal">Create request</button>
                                        </c:if>
                                        <c:if test="${currentDate ne listTimes.dateTime}">
                                            <input type="hidden" id="transdata" value="${employeeDetail.staffAndCode.employeeCode},${employeeDetail.staffAndCode.staffName}"/>
                                            <button class="btn btn-warning magin-button" onclick="requestTime($('#transdata').val(),'${listTimes.dateTime}')"
                                                    data-toggle="modal">Create request ne</button>
                                        </c:if>
                                    </c:forEach>
                                </c:if>--%>
                                <%--<c:if test="${listRequestApprove == null}">--%>
                                    <input type="hidden" id="transdata" value="${employeeDetail.staffAndCode.employeeCode},${employeeDetail.staffAndCode.staffName}"/>
                                    <button class="btn btn-warning magin-button" onclick="requestTime($('#transdata').val(),'${listTimes.dateTime}')"
                                            data-toggle="modal">Create request</button>
                               <%-- </c:if>--%>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
        <c:if test="${employeeDetail == null}">
            <span>No data</span>
        </c:if>

    </div>
</div>
<!--begin-->
<jsp:include page="timesheets/modal.jsp"/>
<!--end-->
<!-- /container -->
<jsp:include page="../template-layout/js-template.jsp"/>
<script src="${contextPath}/resources/js/sheet-form.js"></script>
<jsp:include page="../template-layout/footer.jsp"/>
</body>
</html>
