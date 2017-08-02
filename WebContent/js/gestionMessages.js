$(document).ready(function() {
			checkForMessages();
		});
function notyMessage(message, type) {
	noty({
		text : message,
		layout : 'topCenter',
		theme : 'relax',
		timeout : 8000,// or relax
		type : type,
		closeWith : [ 'click', 'button' ],
		maxVisible : 4
	});
}
function notyLoad(message) {
	noty({
		text : message,
		layout : 'center',
		theme : 'relax',
		timeout : false,
		type : 'information',
		modal : true,
		id : 'notyLoad',
		closeWith : [],
		maxVisible : 1
	});
}
function loading(bool, message) {
	if (bool) {
		notyLoad(message);
	} else {
		$('#notyLoad').remove();
	}
}

function checkForMessages() {
	if ($('#message').text() != '') {
		notyMessage($('#message').text(), $('#messageType').text());
	}
}