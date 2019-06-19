$(function() { // document ready

  var calendar = $('#calendar').fullCalendar({
    header: {
      left: 'prev,next today',
      center: 'title',
      right: 'agendaWeek,agendaDay,month'
    },
    defaultView: 'month',
    defaultTimedEventDuration: '13:00',
    allDaySlot: false,
    scrollTime: '08:00',
    businessHours: {
      start: '9:00',
      end: '18:00',
    },
    lang: /^en-/.test(navigator.language) ? 'en' : 'zh-cn',
    eventOverlap: function(stillEvent, movingEvent) {
      return true;
    },
    events: [{
      title: 'Dr. Chris',
      start: '2017-09-28T15:00+08:00'
    }, {
      title: 'Dr. Syed',
      start: '2017-10-25T12:00+08:00'
    }],
    editable: true,
    selectable: true,
    selectHelper: true,
    select: function(start, end) {
      var duration = (end - start) /1000;
      if(duration == 1800) {
        // set default duration to 1 hr.
      	end = start.add(30, 'mins');
        return calendar.fullCalendar('select', start, end);
      }
      var title = prompt('Appointment Title:');
      var eventData;
      if (title && title.trim()) {
        eventData = {
          title: title,
          start: start,
          end: end
        };
        calendar.fullCalendar('renderEvent', eventData);
      }
      calendar.fullCalendar('unselect');
    },
    eventRender: function(event, element) {
      var start = moment(event.start).fromNow();
      element.attr('title', start);
    },
    loading: function() {
      
    }
  });

});