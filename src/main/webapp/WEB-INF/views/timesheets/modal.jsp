<%--
  Created by IntelliJ IDEA.
  User: FRAMGIA\dang.ngoc.thien
  Date: 14/11/2016
  Time: 13:32
  To change this template use File | Settings | File Templates.
--%>
<!-- popup -->
<div id="myModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <div style="float: right; margin-bottom: 10px">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
                <div>
                    <h2 class="modal-title" style="color: #5bc0de">Create request timesheet Employee for : <span id="nameEployee"></span> </h2>
                    <label id="renderData"></label>
                </div>
            </div>
           <form action="<%=request.getContextPath()%>/createRequest" modelAttribute="timeSheetForm"  method="post">
               <div class="modal-body">
                   <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                   <input type="hidden" name="code" id="txtCode"/>
                   <input type="hidden" name="name" id="txtName"/>
                   <div class="form-group">
                       <label>Type</label>
                   </div>
                   <div class="form-group ${status.error ? 'has-error' : ''}">
                       <select id="typeRequest" name="typeRequest" class="form-control" >
                           <option value="IL">In Late</option>
                           <option value="LE">Leave Early</option>
                           <option value="LO">Leave Out</option>
                       </select>
                   </div>
                   <div class="form-group ${status.error ? 'has-error' : ''}">
                       <label>Time From</label>
                       <div class="row">
                           <div class="col-md-4">
                               <span class="form-control" id="dateRequestFrom"></span>
                           </div>
                           <div class="col-md-4">
                               <select  class="form-control" id="fromHours" onchange="onchangeDateFrom()" required>
                                   <option value="">Hours</option>
                                   <% for (int i =0; i <= 24; i++){
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
                               <select  class="form-control" id="fromMinutes" onchange="onchangeDateFrom()" required>
                                   <option value="">Minutes</option>
                                   <% for (int i = 0; i <= 60; i++){
                                       String minutes;
                                       if(i <10)
                                           minutes = "0".concat(String.valueOf(i));
                                       else
                                       minutes = String.valueOf(i);%>
                                   <option value="<%=minutes%>"><%=minutes%></option>
                                   <% } %>
                               </select>
                           </div>
                       </div>
                       <input type="hidden" id="timeFrom" name="timeFrom"/>
                   </div>
                   <div class="form-group ${status.error ? 'has-error' : ''}">
                       <label>Time To</label>
                       <div class="row">
                         <div class="col-md-4">
                             <span class="form-control" id="dateRequestTo"></span>
                         </div>
                         <div class="col-md-4">
                             <select class="form-control" id="toHours" onchange="onchangeDateTo()" required>
                                 <option value="">Hours</option>
                                 <% for (int i =0; i < 24; i++){
                                     String hours;
                                     if(i < 10)
                                         hours = "0".concat(String.valueOf(i));
                                     else
                                     hours = String.valueOf(i);%>
                                 <option value="<%=hours%>"><%=hours%></option>
                                 <% } %>
                             </select>
                         </div>
                          <div class="col-md-4">
                              <select  class="form-control" id="toMinutes" onchange="onchangeDateTo()" required>
                                  <option value="">Minutes</option>
                                  <% for (int i =0; i < 60; i++){
                                      String minutes;
                                      if(i <10)
                                          minutes = "0".concat(String.valueOf(i));
                                      else
                                      minutes = String.valueOf(i);%>
                                  <option value="<%=minutes%>"><%=minutes%></option>
                                  <% } %>
                              </select>
                          </div>
                       </div>
                       <input type="hidden" id="timeTo" name="timeTo"/>
                   </div>
                   <div class="form-group">
                       <label>Reason</label>
                   </div>
                   <div class="form-group ${status.error ? 'has-error' : ''}">
                       <textarea rows="4" id="reasonRequest" name="reasonRequest" class="form-control" placeholder="Reason create request"  minlength="3" maxlength="300" required></textarea>
                       <%--<input type="text" id="reasonRequest" name="reasonRequest" class="form-control" placeholder="Reason create request"--%>
                              <%--minlength="6" maxlength="30" required/>--%>
                   </div>
                   <div class="form-group">
                       <label>Group lead</label>
                   </div>
                   <div class="form-group ${status.error ? 'has-error' : ''}">
                       <input type="text" id="groupLead" name="groupLead" class="form-control" placeholder="Full name group lead"
                              minlength="6" maxlength="30" required/>
                   </div>
                   <div class="form-group">
                       <label>Project management</label>
                   </div>
                   <div class="form-group ${status.error ? 'has-error' : ''}">
                       <input type="text" id="teamLead" name="teamLead" class="form-control" minlength="6" maxlength="30"
                              placeholder="Full name project management" required/>
                   </div>
                   <div>
                       <button type="submit" class="btn btn-lg btn-primary btn-block">Submit</button>
                   </div>
               </div>
           </form>
            <%-- <div class="modal-footer">
                 <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
             </div>--%>
        </div>

    </div>
</div>