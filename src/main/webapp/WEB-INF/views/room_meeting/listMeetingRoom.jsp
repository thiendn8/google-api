<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%--
  Created by IntelliJ IDEA.
  User: FRAMGIA\dang.ngoc.thien
  Date: 21/11/2016
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List booking room meeting</title>
    <jsp:include page="../../template-layout/header.jsp"/>
</head>
<body>
<div class="container">
    <jsp:include page="../../template-layout/navigation.jsp"/>

    <div class="modal-body">
        <div class="row">
            <div class="col-md-6">
                <h3 class="color-scrum"> List booking room meeting</h3><br/>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 text-left">
                <h4 class="color-scrum">Booking meeting room on date: <c:out value="${calendarMeeting.currentDate}"/></h4>
            </div>
            <div class="col-md-3 text-left">
                <div class="row form-group">
                    <select class="form-control" id="idChangeRoom" onchange="reGetlistCalendarevents(this.value)">
                        <option value="">Please choice room name</option>
                        <option value=""></option>
                        <c:forEach items="${listRoomname}" var="listRoomname">
                            <option value="${listRoomname}"><c:out value="${listRoomname}"/></option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="col-md-3 text-right">
                <button class="btn btn-warning magin-button"  data-toggle="modal" onclick="bookingRoomMeeting(${calendarMeeting.currentDate})">Booking room</button>
            </div>
        </div>
        <c:if test="${not empty calendarMeeting.listEvent}">
            <table class="table-bordered">
                <thead>
                <tr class="row">
                    <th class="col-md-2">Room Name</th>
                    <th class="col-md-2">Time</th>
                    <th class="col-md-3">Creator</th>
                    <th class="col-md-2">Description</th>
                    <th class="col-md-3"></th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach items="${calendarMeeting.listEvent}" var="listRoom">
                        <tr class="row">
                            <td class="col-md-2"><c:out value="${listRoom.roomName}"/></td>
                            <td class="col-md-2"><c:out value="${listRoom.timeFrom} ~ ${listRoom.timeTo}"/> </td>
                            <td class="col-md-3"><c:out value="${listRoom.creator}"/></td>
                            <td class="col-md-2">
                                <span class="ellipsisText" title="${listRoom.description}"><c:out value="${listRoom.description}"/></span>
                            </td>
                            <td class="col-md-3 text-center">
                                <sec:authorize ifAnyGranted="Approver,Manager">
                                    <button class="btn btn-danger magin-button" data-toggle="modal" onclick="removeBookingRoom('${listRoom.eventId}')">Remove</button>
                                </sec:authorize>
                                <button class="btn btn-info magin-button" data-toggle="modal" onclick="updateBookingRoom('${listRoom.eventId}')">Update</button>
                            </td>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
        </c:if>
        <c:if test="${empty calendarMeeting.listEvent}"> No Data</c:if>
    </div>
</div>

<jsp:include page="modalBookingRoom.jsp"/>
<jsp:include page="modalRemoveRoom.jsp"/>
<jsp:include page="../../template-layout/js-template.jsp"/>
<script src="${contextPath}/resources/js/sheet-form.js"></script>
<script src="${contextPath}/resources/js/tooltip.js"></script>
<jsp:include page="../../template-layout/footer.jsp"/>
</body>
</html>
