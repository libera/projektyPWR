//sample json dates and groups data
var datesSample = {'dates':
	[
		{'id':'1', 'code':'bla1', 'name':'PN 13.15', 'groups':
			[
				{'id':'1', 'name':'Nie wiem po co to', 'subject':'System zarządzania projektami studenckimi', 'repo':'GitHub...',
					'students':
					[
						{'id':'1', 'firstname':'Michał', 'surname':'Glenc', 'index':'181031', 'finalmark':'5', 'position':'robol', 'mail':'181031@student.pwr.wroc.pl',
							'marksandpresence':
							[
							 	{'meetingid':'1', 'presenceid':'1', 'present':'1', 'markid':'1', 'mark':'5'},
							 	{'meetingid':'2', 'presenceid':'2', 'present':'1', 'markid':'2', 'mark':'5'},
							]
						},
						{'id':'2', 'firstname':'Przemysław', 'surname':'Wąsala', 'index':'181031', 'finalmark':'5', 'position':'szefu', 'mail':'181031@student.pwr.wroc.pl',
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
				}
			],
			'notingroup':
			[
			 	{'id':'3', 'firstname':'Michał', 'surname':'Glenc', 'index':'181031', 'mail':'181031@student.pwr.wroc.pl'},
			 	{'id':'4', 'firstname':'Przemysław', 'surname':'Wąsala', 'index':'181031', 'mail':'181031@student.pwr.wroc.pl'}
			]
		}
	]
};

function loadDate(id) {
	console.log("load date " + id);
	
	var datesData = datesSample;
	//AJAX
	
	$.each(datesData.dates, function() {
		currDate = this;
		
		//adding not in groups
		$.each(currDate.notingroup, function(index, value) {
			currNoGroup = this;
			
			$('#notingroup').append('<tr id="student' + currNoGroup.id + '" class="course-date ' + currDate.id + '"><td class="name"><a href="mailto:' + currNoGroup.mail + '">' + currNoGroup.firstname + ' ' + currNoGroup.surname + '</a><br /><span class="index">(' + currNoGroup.index + ')</span></td></tr>');
			
			$('#notingroup td').first().addClass('top');
			
			//enabling user dragging
			$('#notingroup tr#student' + currNoGroup.id).draggable({
				revert: 'invalid',
				start: function(e, ui) {
					$(this).css('position', 'absolute');
					$(this).children('td').css('border', '1px solid #e0e0e0');
				},
				stop: function(e, ui) {
					$(this).css('position', 'relative');
					$(this).children('td').css('border', 'none');
					
					$(this).children('td').css('borderBottom', '1px solid #e0e0e0');
					
					if($(this).children('td').hasClass('top')) {
						$(this).children('td').css('borderTop',  '1px solid #e0e0e0');
					}
					
					//$('#notingroup td').first().addClass('top');
				}
				});
			
			/*$('#notingroup tr#student' + currNoGroup.id).mousedown(function() {
				
			});
			
			$('#notingroup tr#student' + currNoGroup.id).mouseup(function() {
				
			});*/
		});
		
		//adding in groups
		$('#groups').append('<div class="course-date ' + currDate.id + '"></div>');
		
		$.each(currDate.groups, function() {
			currGroup = this;
			
			$('#groups .course-date.' + currDate.id).append('<div class="group ' + currGroup.id + '">' +
				'<header>' + currGroup.subject + '<div class="tools"><a href="#" class="more">Więcej</a></div></header>' +
				'<div class="details"><table class="students"><tr class="header"><td class="empty"></td></tr></table></div>' +
			'</div>');
			
			//enable user dropping
			$('#groups .group.' + currGroup.id).droppable({
				drop: function(e, ui) {
					ui.draggable.remove();
					
					$('#notingroup td').first().addClass('top');
					
					if($('#notingroup td').hasClass('top')) {
						$('#notingroup td').css('borderTop',  '1px solid #e0e0e0');
					}
				}
			});
			
			//adding meetings header
			$.each(currGroup.meetings, function() {
				currMeeting = this;
				
				$('#groups .group.' + currGroup.id + ' .students tr.header').append('<td class="meeting ' + currMeeting.id + '" id="meeting' + currMeeting.id + '"><input type="text" class="name" value="' + currMeeting.name + '" /><br /><input type="text" maxlength="5" class="date" value="' + currMeeting.date + '" /><input type="text" maxlength="3" class="weight" value="' + currMeeting.weight + '" /></td>');
			});
			
			$('#groups .group.' + currGroup.id + ' .students tr.header').append('<td class="add-meeting"><a href="#">dodaj</a></td>');
			
			$('#groups .group.' + currGroup.id + ' .students tr.header td.add-meeting a').click(function(e) {
				e.preventDefault();
				
				//first we need to connect to database and get new meeting id
				newMeetingID = null;
				
				//TO DO: getting array of newly created cells {meetingid {studentid, markid, presenceid}}
				
				//adding additional header
				$('<td class="meeting ' + newMeetingID + '" id="meeting' + newMeetingID + '"><input type="text" class="name" /><br /><input type="text" maxlength="5" class="date" /><input type="text" maxlength="3" class="weight" /></td>').insertBefore($('#groups .group.' + currGroup.id + ' .students tr.header td.add-meeting'));
			
				//adding new meeting to each student
				$.each(currGroup.students, function() {
					currStudent = this;
					$('.group.' + currGroup.id + ' .students tr.student.' + currStudent.id).append('<td class="meeting ' + newMeetingID + '"><input type="checkbox" id="student' + currStudent.id + '_meeting' + newMeetingID +'" /><label for="student' + currStudent.id + '_meeting' + newMeetingID +'"></label><input type="text" maxlength="3" class="mark" /></td>');
				});
			});
			
			//adding students header
			$.each(currGroup.students, function() {
				currStudent = this;
				
				$(' #groups .group.' + currGroup.id + ' .students').append('<tr class="student ' + currStudent.id + '" id="student' + currStudent.id + '"><td class="name"><a href="mailto:' + currStudent.mail + '">' + currStudent.firstname + ' ' + currStudent.surname + '</a><br /><span class="index">(' + currStudent.index + ')</span></td></tr>');
			
				//adding meeting to each student
				$.each(currGroup.meetings, function() {
					currMeeting = this;
					$('#groups .group.' + currGroup.id + ' .students tr.student.' + currStudent.id).append('<td class="meeting ' + currMeeting.id + '"></td>');
				});
				
				$.each(currStudent.marksandpresence, function() {
					currMarkAndPresence = this;
					
					//TO DO: add mark and presence id
					
					$('#groups .group.' + currGroup.id + ' .students tr.student.' + currStudent.id + ' td.meeting.' + currMarkAndPresence.meetingid).html('<input type="checkbox" id="student' + currStudent.id + '_meeting' + currMarkAndPresence.meetingid +'" /><label for="student' + currStudent.id + '_meeting' + currMarkAndPresence.meetingid +'"></label><input type="text" maxlength="3" class="mark" value="' + currMarkAndPresence.mark + '" />');
					if(currMarkAndPresence.present == '1') {
						$('input#student' + currStudent.id + '_meeting' + currMarkAndPresence.meetingid).attr('checked', true);
					}
				});
			});
			
			$('#groups .group.' + currGroup.id + ' .details').hide();
			
			$('#groups .group.' + currGroup.id + ' header').click(function() {
				if($(this).parent().hasClass('active')) {
					$(this).next().hide('blind', 200, function() {
						$('#groups .group.' + currGroup.id).removeClass('active');
					});
				} else {
					$(this).next().show('blind', 200, function() {
						$('#groups .group.' + currGroup.id).addClass('active');
					});
				}
			});
		});
	});
}

function unloadDate(id) {
	console.log("unload date" + id);
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