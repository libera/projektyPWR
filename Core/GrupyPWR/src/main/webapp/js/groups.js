//sample json dates and groups data
var datesSample = {'dates':
	[
		{'id':'1', 'code':'bla1', 'name':'PN 13.15', 'groups':
			[
				{'id':'1', 'name':'Nie wiem po co to', 'subject':'System zarządzania projektami studenckimi', 'repo':'GitHub...'},
				{'id':'2', 'name':'Nie wiem po co to', 'subject':'Internetowy system zarządzania przychodnią', 'repo':'GitHub...'},
				{'id':'3', 'name':'Nie wiem po co to', 'subject':'Inny smieszny projekt', 'repo':'GitHub...'}
			]
		},
		{'id':'2', 'code':'bla2', 'name':'WT 11.15', 'groups':
			[
				{'id':'6', 'name':'Nie wiem po co to', 'subject':'Inny smieszny projekt', 'repo':'GitHub...'}
			]
		},
		{'id':'3', 'code':'bla3', 'name':'PT 19.55', 'groups':
			[
				{'id':'4', 'name':'Nie wiem po co to', 'subject':'System zarządzania projektami studenckimi', 'repo':'GitHub...'}
			]
		},
	]
};

function loadGroups() {
	//Rewrite to load only newly selected dates and remove unselected
	//clearing previous
	//$('#groups').html('');

	$('input[name="checkedDates"]:checked').each(function() {
		console.log(this.value);
	});
	
	//TO DO: get selected groups by AJAX
	
	$.each(datesSample.dates, function() {
		currDate = this;
	
		console.log(currDate.name);
		
		$.each(currDate.groups, function() {
			console.log('\t' + this.subject);
		});
	});
	
	$('div.group div.details').hide('blind', 200, function() {
		$('div.group').removeClass('active');
	});
	
	$('div.group header').click(function() {
		if($(this).parent().hasClass('active')) {
			$(this).next().hide('blind', 200, function() {
				$('div.group').removeClass('active');
			});
		} else {
			$(this).next().show('blind', 200, function() {
				$('div.group').addClass('active');
			});
		}
	});
}