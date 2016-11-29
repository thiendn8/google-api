<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: FRAMGIA\dang.ngoc.thien
  Date: 14/11/2016
  Time: 13:32
  To change this template use File | Settings | File Templates.
--%>
<!-- popup -->
<div id="removeBookingRoom" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content" style="height: 30% !important;">
            <div class="modal-header" style="margin-top: 25px">
                <div class="row">
                    <h5 class="modal-title" style="color: #5bc0de" id="titleModalRemove"></h5>
                </div>
                <div class="row">
                    <div class="col-md-12 text-center">
                        <h2 style="color: #2d2d30" id="txtDescRemove"></h2>
                    </div>
                </div>
            </div>
           <form action="<%=request.getContextPath()%>/removeBooking" modelAttribute="eventDtos"  method="POST">
               <div class="modal-body">
                   <div class="row" style="margin-top: 45px;">
                       <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                       <input type="hidden" name="eventId" id="eventIdRemove"/>
                       <div class="col-md-6 text-right">
                           <button type="submit" class="btn btn-lg btn-primary btn-block">OK</button>
                       </div>
                       <div class="col-md-6 text-left">
                           <button type="button" class="btn btn-lg btn-danger btn-block" data-dismiss="modal">Cancel</button>
                       </div>
                   </div>
               </div>
           </form>
        </div>

    </div>
</div>