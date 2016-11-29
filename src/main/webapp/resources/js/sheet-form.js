/**
 * Created by FRAMGIA\dang.ngoc.thien on 14/11/2016.
 */

function changeDataByDate (value) {
    location.href = "/welcome?sheetName="+value;
}

function requestTime(detailEmployee, date) {
    var arrayData = detailEmployee.split(',');
    var code = arrayData[0];
    var name = arrayData[1];
    $('#nameEployee').text(name);
    $('#dateRequestFrom').text(date);
    $('#dateRequestTo').text(date);
    $('#txtCode').val(code);
    $('#txtName').val(name);
    $('#myModal').modal('show');
}

function onchangeDateFrom() {
    var date = $('#dateRequestFrom').text();
    var valueHours = $( "#fromHours option:selected" ).val();
    var valueMinutes = $( "#fromMinutes option:selected" ).val();
    var timeFrom = date+' '+valueHours+":"+valueMinutes;
    $('#timeFrom').val(timeFrom);

}
function onchangeDateTo() {
    var dateTo = $('#dateRequestTo').text();
    var valueHours = $( "#toHours option:selected" ).val();
    var valueMinutes = $( "#toMinutes option:selected" ).val();
    var timeTo = dateTo +' '+valueHours+":"+valueMinutes;
    $('#timeTo').val(timeTo);

}

function bookingRoomMeeting(currentDate) {
    $('#titleModal').text('Booking meeting room');
    //clean data hidden html
    $('#eventId').val('');
    $('#summary').val('');
    $('#startTime').val('');
    $('#endTime').val('');
    //clean data show html
    $('#roomName').val('');
    $('#descSummary').val('');
    $('#fromHours').val('');
    $('#fromMinutes').val('');
    $('#toHours').val('');
    $('#toMinutes').val('');
    $('#bookingRoom').modal('show');
}

function changeSummary() {
    $('#summary').val($('#roomName').val()+': '+$('#descSummary').val());
}
function onchangeTimeFrom() {
    $('#startTime').val($('#fromHours').val()+':'+$('#fromMinutes').val());
}
function onchangeTimeTo() {
    $('#endTime').val($('#toHours').val()+':'+$('#toMinutes').val());
}
function validateFrom(ev){
    var timeFrom = $('#startTime').val();
    var timeTo = $('#endTime').val();
    var roomName = $('#roomName').val();
    var hourFrom = $('#fromHours').val();
    var hourTo = $('#toHours').val();
    if($('#roomName').val() != ''){
        if(hourFrom != null || hourTo != ''
            || $('#fromMinutes').val() != '' || $('#toMinutes').val() !=''
            && hourFrom < hourTo){
            $.ajax({
                url: "/checkTime",
                type: 'GET',
                cache : false,
                processData: false,
                contentType: false,
                data:'timeFrom='+timeFrom+"&timeTo="+timeTo+"&roomName="+roomName,
                success: function(result){
                    console.log(result.data);
                    if(result == '00'){
                        $('#fromBookingRoom').submit();
                    }else{
                        $('#error').text('Time invalid!!');
                    }
                }});
        }else{
            $('#error').text('Time invalid!!');
        }
    }else{
        $('#error').text('Room name is required');
    }


  ev.preventDefault();
}
//remove booking room
function removeBookingRoom(bookingId) {
    $('#titleModalRemove').text('Remove booking room');
    $('#txtDescRemove').text('Are you remove this booking room ?');
    $('#eventIdRemove').val(bookingId);
    $('#removeBookingRoom').modal('show');
}
// update data calendarevents with room name
function reGetlistCalendarevents(idChangeRoom) {
    location.href = "/listBookingRoom?roomName="+idChangeRoom;
}
//update booking room
function updateBookingRoom(eventId) {
   if(eventId != '' || eventId != null){
       $.ajax({
           url:"/updateBookingRoom",
           type: 'GET',
           cache : false,
           processData: false,
           contentType: false,
           data:'eventId='+eventId,
           success: function(result){
               if(result != '01'){
                   var dataJson = JSON.parse(result);
                   var timeFrom = dataJson.timeFrom.split(':');
                   var timeTo = dataJson.timeTo.split(':');
                   $('#titleModal').text('Update meeting room');
                   //fill data hidden html
                   $('#eventId').val(dataJson.eventId);
                   $('#summary').val(dataJson.roomName+':'+dataJson.description);
                   $('#startTime').val(dataJson.timeFrom);
                   $('#endTime').val(dataJson.timeTo);
                   //fill data show html
                   $('#roomName').val(dataJson.roomName);
                   $('#descSummary').val(dataJson.description);
                   $('#fromHours').val(timeFrom[0]);
                   $('#fromMinutes').val(timeFrom[1]);
                   $('#toHours').val(timeTo[0]);
                   $('#toMinutes').val(timeTo[1]);
                   $('#bookingRoom').modal('show');
               }
           }
       });

   }else {
       alert('fail');
   }
}


