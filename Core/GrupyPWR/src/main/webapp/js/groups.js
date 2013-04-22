//sample json dates and groups data
var datesSample = {'dates':
	[
		{'id':'1', 'code':'bla1', 'name':'PN 13.15', 'groups':
			[
				{'id':'1', 'name':'Nie wiem po co to', 'subject':'System zarządzania projektami studenckimi', 'repo':'GitHub...',
					'students':
					[
						{'id':'1', 'firstname':'Michał', 'surname':'Glenc', 'Index':'181031', 'finalmark':'5', 'position':'robol', 'mail':'181031@student.pwr.wroc.pl',
							'marksandpresence':
							[
							 	{'meetingid':'1', 'presenceid':'1', 'present':'1', 'markid':'1', 'mark':'5'},
							 	{'meetingid':'2', 'presenceid':'2', 'present':'1', 'markid':'2', 'mark':'5'},
							]
						},
						{'id':'2', 'firstname':'Przemysław', 'surname':'Wąsala', 'Index':'181031', 'finalmark':'5', 'position':'szefu', 'mail':'181031@student.pwr.wroc.pl',
							'marksandpresence':
							[
							 	{'meetingid':'1', 'presenceid':'3', 'present':'1', 'markid':'3', 'mark':'5'},
							 	{'meetingid':'2', 'presenceid':'4', 'present':'1', 'markid':'4', 'mark':'5'},
							]
						}
					],
					//columns definition
					'meetings':
					[
					 	{'id':'1', 'date':'01.04', 'name':'Wersja alpha', 'weight':'0.5'},
					 	{'id':'2', 'date':'08.04', 'name':'Wersja beta', 'weight':'0.5'}
					],
					'notes':
					[
					 	{'id':'1', 'content':'jakaś przykładowa notatka', 'file':'fefrefefef'},
					 	{'id':'2', 'content':'jakaś przykładowa notatka 1', 'file':'fefrefefef'}
					]
				},
				{'id':'2', 'name':'Nie wiem po co to', 'subject':'Internetowy system zarządzania przychodnią', 'repo':'GitHub...'},
				{'id':'3', 'name':'Nie wiem po co to', 'subject':'Inny smieszny projekt', 'repo':'GitHub...'}
			],
			'notingroup':
			[
			 	{'id':'3', 'firstname':'Michał', 'surname':'Glenc', 'Index':'181031', 'mail':'181031@student.pwr.wroc.pl'}
			]
		}
	]
};

function loadDate(id) {
	console.log("load " + id);
	
	//TO DO: AJAX
}

function unloadDate(id) {
	console.log("unload " + id);
}

function loadGroups() {
	//Rewrite to load only newly selected dates and remove unselected
	//clearing previous
	//$('#groups').html('');
	
	//TO DO: get selected groups by AJAX
	
	/*$.each(datesSample.dates, function() {
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
	});*/
}