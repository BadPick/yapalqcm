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

function notyDialogConfirm(message,confirmCB,args) {
	noty({
		text : message,
		layout : 'center',
		theme : 'relax',
		timeout : false,
		type : 'information',
		modal : true,
		id : 'confirmDialog',
		closeWith : [],
		maxVisible : 1,
		buttons : [ {
			addClass : 'button success tiny radius no-margin',
			text : 'Continue',
			onClick : function($noty) {
				$noty.close();
				//window[confirmCB].call(false, args); // heres where the magic
														// happens.
			}
		}, {
			addClass : 'button alert tiny radius no-margin',
			text : 'Cancel',
			onClick : function($noty) {
				$noty.close();
				notyMessage('la suppression a été annulée', 'information');
			}
		} ]
	})
}