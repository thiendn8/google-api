<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>admin setting</title>
    <jsp:include page="../../template-layout/header.jsp"/>
</head>
<body>

<div class="container">
    <jsp:include page="../../template-layout/navigation.jsp"/>
    <div class="row">
        <div class="col-md-6">
            <h3 class="color-scrum">Setting service</h3>
        </div>
    </div>
    <div>
       <form class="form-signin" action="<%=request.getContextPath()%>/processSettingService" modelAttribute="userAndRole"  method="post">
           <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
           <div class="form-group">
               <label style="color: white">Users</label>
           </div>
           <div class="form-group">
               <select class="form-control" id="user_Id" name="user_Id" required>
                   <option value="">Please choice user</option>
                   <c:forEach items="${listUser}" var="listUser">
                       <c:if test="${listUser.username != currentUser}">
                           <option value="${listUser.id}"><c:out value="${listUser.username}"/></option>
                       </c:if>
                   </c:forEach>
               </select>
           </div>
           <div class="form-group">
               <label style="color: white">Role</label>
           </div>
           <div class="form-group">
               <select class="form-control" id="role_Id" name="role_Id" required>
                   <option value="">Please choice role</option>
                   <c:forEach items="${listRole}" var="listRole">
                       <option value="${listRole.id}"><c:out value="${listRole.name}"/></option>
                   </c:forEach>
               </select>
           </div>
           <div class="form-group">
               <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
           </div>
       </form>
    </div>
</div>
<!-- /container -->
<jsp:include page="../../template-layout/js-template.jsp"/>
<jsp:include page="../../template-layout/footer.jsp"/>
</body>
</html>
