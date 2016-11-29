<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%--
  Created by IntelliJ IDEA.
  User: FRAMGIA\dang.ngoc.thien
  Date: 15/11/2016
  Time: 13:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My request timesheet</title>
    <jsp:include page="../../template-layout/header.jsp"/>
</head>
<body>
<div class="container">
    <jsp:include page="../../template-layout/navigation.jsp"/>
    <div class="modal-body">
        <div class="row">
            <div class="col-md-6">
                <h3 class="color-scrum">My request timesheet</h3>
            </div>
        </div>
        <c:if test="${not empty listMyRequest}">
            <table class="table-bordered">
                <thead>
                <tr class="row">
                    <th class="col-md-2">Staff name</th>
                    <th class="col-md-1">Type Request</th>
                    <th class="col-md-1">Time From</th>
                    <th class="col-md-1">Time End</th>
                    <th class="col-md-2">Group Lead</th>
                    <th class="col-md-2">Team Lead</th>
                    <th class="col-md-2">Reason</th>
                    <th class="col-md-1">Status</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${listMyRequest}" var="listMyRequest">
                    <tr class="row">
                        <td class="col-md-2"><c:out value="${listMyRequest.name}"/></td>
                        <td class="col-md-1"><c:out value="${listMyRequest.typeRequest}"/></td>
                        <td class="col-md-1"><c:out value="${listMyRequest.timeFrom}"/></td>
                        <td class="col-md-1"><c:out value="${listMyRequest.timeTo}"/></td>
                        <td class="col-md-2"><c:out value="${listMyRequest.groupLead}"/></td>
                        <td class="col-md-2"><c:out value="${listMyRequest.teamLead}"/></td>
                        <td class="col-md-2">
                            <span data-toggle="tooltip" data-placement="bottom" class="ellipsisText" style="width: 200px !important;" title="${listMyRequest.reasonRequest}">
                                <c:out value="${listMyRequest.reasonRequest}"/></span>
                        </td>
                        <td class="col-md-1"><c:out value="${listMyRequest.status}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty listMyRequest}">
            <span>Empty data</span>
        </c:if>
    </div>
</div>
<jsp:include page="../../template-layout/js-template.jsp"/>
<script src="${contextPath}/resources/js/tooltip.js"></script>
<jsp:include page="../../template-layout/footer.jsp"/>
</body>
</html>
