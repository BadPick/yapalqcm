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
		buttons: [
		          noty.button('Continuer', 'btn btn-success', function () {
		              console.log('button 1 clicked');
		          }, {id: 'button1', 'data-status': 'ok'}),

		          noty.button('Annuler', 'btn btn-error', function () {
		              console.log('button 2 clicked');
		              n.close();
		          })
		        ]
	})
}