<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: FRAMGIA\dang.ngoc.thien
  Date: 14/11/2016
  Time: 13:32
  To change this template use File | Settings | File Templates.
--%>
<!-- popup -->
<div id="bookingRoom" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <div style="float: right; margin-bottom: 10px">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
                <div>
                    <h2 class="modal-title" style="color: #5bc0de" id="titleModal"></h2>
                </div>
            </div>
           <form action="<%=request.getContextPath()%>/saveOrUpdateBookingRoom" modelAttribute="eventDto"  method="POST" id="fromBookingRoom">
               <div class="modal-body">
                   <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                   <input type="hidden" name="eventId" id="eventId" value=""/>
                   <div class="form-group">
                       <label>Room name </label>
                   </div>
                   <div class="form-group">
                       <input class="form-control" id="roomName" onchange="changeSummary();"  type="text" required placeholder="Please enter room name meeting"/>
                   </div>
                   <div class="form-group">
                       <label>Description Summary</label>
                   </div>
                   <div class="form-group">
                       <input class="form-control" id="descSummary" onchange="changeSummary();"  type="text" required placeholder="Please enter description summary"/>
                   </div>
                   <input type="hidden" id="summary" name="summary"/>
                   <div class="form-group">
                       <label>Time From</label>
                       <div class="row">
                           <div class="col-md-4">
                               <select  class="form-control" id="fromHours" onchange="onchangeTimeFrom()" required>
                                   <option value="">Hours</option>
                                   <% for (int i =0; i < 24; i++){
                                       String hours;
                                       if(i <10)
                                           hours = "0".concat(String.valueOf(i));
                                       else
                                           hours = String.valueOf(i);
                                       %>
                                   <option value="<%=hours%>"><%=hours%></option>
                                   <% } %>
                               </select>
                           </div>
                           <div class="col-md-4">
                               <select  class="form-control" id="fromMinutes" onchange="onchangeTimeFrom()" required>
                                   <option value="">Minutes</option>
                                   <option value="00">00</option>
                                   <option value="30">30</option>
                               </select>
                           </div>
                       </div>
                       <input name="startTime" id="startTime" type="hidden"/>
                   </div>
                   <div class="form-group">
                       <label>Time To</label>
                       <div class="row">
                           <div class="col-md-4">
                               <select  class="form-control" id="toHours" onchange="onchangeTimeTo()" required>
                                   <option value="">Hours</option>
                                   <% for (int i =0; i < 24; i++){
                                       String hours;
                                       if(i <10)
                                           hours = "0".concat(String.valueOf(i));
                                       else
                                           hours = String.valueOf(i);
                                   %>
                                   <option value="<%=hours%>"><%=hours%></option>
                                   <% } %>
                               </select>
                           </div>
                           <div class="col-md-4">
                               <select  class="form-control" id="toMinutes" onchange="onchangeTimeTo()" required>
                                   <option value="">Minutes</option>
                                   <option value="00">00</option>
                                   <option value="30">30</option>
                               </select>
                           </div>
                       </div>
                       <input type="hidden" id="endTime" name="endTime"/>
                   </div>
                   <div class="form-group">
                       <label>Location</label>
                       <input class="form-control" type="text" name="address" id="idLocation" placeholder="Enter location"/>
                   </div>
                   <div class="form-group">
                       <label>Note</label>
                       <input class="form-control" type="text" name="description" placeholder="Enter note"/>
                   </div>
                   <div class="form-group">
                       <label>List mail inform</label>
                       <textarea class="form-control" name="mailInfrom" placeholder="xx@gmail.com, yy@gmail.com, ..."></textarea>
                   </div>
                   <div>
                       <button onclick="validateFrom(event)" class="btn btn-lg btn-primary btn-block">Submit</button>
                   </div>
               </div>
           </form>
             <div class="modal-footer">
                 <span id="error"  class="has-error"></span>
             </div>
        </div>

    </div>
</div>