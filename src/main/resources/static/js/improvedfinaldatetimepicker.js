var defaults = {
	calendarWeeks: true,
	showClear: false,
	showClose: true,
	allowInputToggle: true,
	useCurrent: false,
	ignoreReadonly: true,

	defaultDate: moment(moment().subtract(-2, 'days')).hours(9).minutes(0).seconds(0).milliseconds(0),
	minDate: moment(new Date()).hours(9).minutes(0).seconds(0).milliseconds(0),
	maxDate: "2020-12-31",
		
	toolbarPlacement: 'top',
	
	
	icons: {
		time: 'fa fa-clock-o',
		date: 'fa fa-calendar',
		up: 'fa fa-angle-up',
		down: 'fa fa-angle-down',
		previous: 'fa fa-angle-left',
		next: 'fa fa-angle-right',
		today: 'fa fa-dot-circle-o',
		clear: 'fa fa-trash',
		close: 'fa fa-times'
	}
};

$(function() {
	var optionsDatetime = $.extend({}, defaults, {format:'YYYY-MM-DD hh:mm:ss'});
	var optionsDate = $.extend({}, defaults, {format:'YYYY-MM-DD'});
	var optionsTime = $.extend({}, defaults, 
	{				
		format:'HH:mm:[00]',
		stepping: 30,
		enabledHours: [9, 10, 11, 12, 13, 14, 15, 16, 17]
		
	}
	);
	
	$('.datepicker').datetimepicker(optionsDate);
	
	$('.timepicker').datetimepicker(optionsTime);
	
	$('.datetimepicker').datetimepicker(optionsDatetime);
});