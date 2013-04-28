//sample json dates and groups data

var datesSample = {'dates':
	[
		{'id':'1', 'code':'bla1', 'name':'PN 13.15', 'groups':
			[
				{'id':'1', 'name':'Nie wiem po co to', 'subject':'System zarządzania projektami studenckimi', 'repo':'GitHub...', 'comment':'Bardzo dobra grupa',
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
	$.ajax({
		url: serverURL + 'getgroups',
		type: 'POST',
		data: {dateid: id},
		dataType: 'json',
		success: function(data, textStatus, jqXHR ) {
			datesData = data;
			
			console.log(JSON.stringify(datesData));
			
			$.each(datesData.dates, function() {
				currDate = this;
				
				//adding not in groups
				$.each(currDate.notingroup, function(index, value) {
					currNoGroup = this;
					
					$('#notingroup').append('<tr id="student' + currNoGroup.id + '" class="course-date ' + currDate.id + '"><td class="name"><a href="mailto:' + currNoGroup.mail + '">' + currNoGroup.firstname + ' ' + currNoGroup.surname + '</a><br /><span class="index">(' + currNoGroup.index + ')</span></td></tr>');
					
					$('#notingroup tr#student' + currNoGroup.id).data('studentID', currNoGroup.id);
					$('#notingroup tr#student' + currNoGroup.id).data('studentMail', currNoGroup.mail);
					$('#notingroup tr#student' + currNoGroup.id).data('studentFirstname', currNoGroup.firstname);
					$('#notingroup tr#student' + currNoGroup.id).data('studentSurname', currNoGroup.surname);
					$('#notingroup tr#student' + currNoGroup.id).data('studentIndex', currNoGroup.index);
					
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
						}
					});
				});
				
				//adding in groups
				$('#groups').append('<div class="course-date ' + currDate.id + '"><header class="course-date-header">' + currDate.name +' (' + currDate.code + ')<div class="tools"><a href="#" class="add-group-button">dodaj grupe projektowa w tym terminie</a></div></header></div>');
				
				$.each(currDate.groups, function() {
					addGroup(currDate, this);
				});
				
				//add group button
				$('#groups .course-date .course-date-header .add-group-button').click(function(e) {
					e.preventDefault();
					$('div#lightbox').show();
					
					$('#add-group').show();
					$('#add-group').css('left', $(document).width()/2 - $('#add-group').width()/2);
					$('#add-group').css('top', $(document).height()/2 - $('#add-group').height()/2-50);
					
					$('#add-group-course').html(currDate.name);
					
					$('#add-group-name').val('');
					$('#add-group-subject').val('');
					$('#add-group-repo').val('');
					$('#add-group-comment').val('');
				});
				
				//add group submit
				$('#add-group input#add-group-submit-button').click(function(e) {
					e.preventDefault();
					
					var currGroup = new Object();
					
					currGroup.name = $('#add-group-name').val();
					currGroup.subject = $('#add-group-subject').val();
					currGroup.repo = $('#add-group-repo').val();
					currGroup.comment = $('#add-group-comment').val();
					currGroup.students = new Array();
					currGroup.meetings = new Array();
					currGroup.notes = new Array();
					
					$.ajax({
						url: serverURL + 'addgroup',
						type: 'POST',
						data: {courseid: currDate.id, name: currGroup.name, subject: currGroup.subject, repo: currGroup.repo, comment: currGroup.comment},
						dataType: 'json',
						success: function(data, textStatus, jqXHR ) {
							console.log("Add group: " + data + " " + textStatus);
							
							if(data >= '0') {
								$('div#add-group td.info').show();
								$('div#add-group td.info').html('<span class="success">Grupa została dodana!</span>');
								
								currGroup.id = data;
								
								addGroup(currDate, currGroup);
							} else {
								$('div#add-group td.info').show();
								$('div#add-group td.info').html('<span class="error">Błąd podczas dodawania grupy!</span>');
							}
						},
						error: function(jqXHR, textStatus, errorThrown) {
							console.log(textStatus + ' ' + errorThrown);
						}
					});
				});
			});
		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log(textStatus + ' ' + errorThrown);
		}
	});
}

function addMeeting(group, meeting) {
	currGroup = group;
	currMeeting = meeting;
	
	//adding additional header
	$('<td class="meeting ' + meeting.id + '" id="meeting' + meeting.id + '"><input type="text" class="name" value="'+ meeting.name + '" /><br /><input type="text" maxlength="5" class="date" value="' + meeting.date + '"/><input type="text" maxlength="3" class="weight" value="' + meeting.weight + '"  /></td>').insertBefore($('#groups .course-date .group.' + currGroup.id +  ' .students tr.header td.add-meeting'));

	//adding new meeting to each student
	$.each(currGroup.students, function() {
		currStudent = this;
		
		console.log(JSON.stringify(currStudent));
		
		$('.group.' + currGroup.id + ' .students tr.student.' + currStudent.id).append('<td class="meeting ' + meeting.id + '"><input type="checkbox" id="mark' + meeting.id +'" /><label for="student' + currStudent.id + '_meeting' + meeting.id +'"></label><input type="text" maxlength="3" class="mark" /></td>');
	
		$.each(currStudent.marksandpresence, function() {
			currMarkAndPresence = this;
			
			$('.group.' + currGroup.id + ' .students tr.student.' + currStudent.id + ' td.' + currMarkAndPresence.meetingid + ' .mark').val(currMarkAndPresence.mark);
			if(currMarkAndPresence.present == '1') {
				$('input#student' + currStudent.id + '_meeting' + currMarkAndPresence.meetingid).attr('checked', true);
			}
		});
	});
	
	//setmeeting
	$('td#meeting' + meeting.id + ' input').blur(function() {
		name = $('.meeting.' + meeting.id + ' input.name').val();
		date = $('.meeting.' + meeting.id+ ' input.date').val();
		weight = $('.meeting.' + meeting.id + ' input.weight').val();
		
		$.ajax({
			url: serverURL + 'setmeeting',
			type: 'POST',
			data: {meetingid: meeting.id, name: name, date: date, weight: weight},
			dataType: 'json',
			success: function(data, textStatus, jqXHR ) {
				console.log("Set meeting: " + data + " " + textStatus);
				
				if(data == '1') {
				} else {
					console.log(error);
				}
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(textStatus + ' ' + errorThrown);
			}
		});
	});
}

function addGroup(date, group) {
	currDate = date;
	currGroup = group;
	
	$('#groups .course-date.' + currDate.id).append('<div class="group ' + currGroup.id + '">' +
			'<header><span class="subject">' + currGroup.subject + '</span><div class="tools"><a href="#" class="more">Więcej</a><a href="#" class="edit-group">Edytuj grupe projektowa</a></div></header>' +
			'<div class="details"><table class="students"><tr class="header"><td class="empty"></td><td class="add-meeting"><a href=#">dodaj</a></td></tr></table></div>' +
		'</div>');
		
	//enable user dropping
	$('#groups .group.' + currGroup.id).droppable({
		drop: function(e, ui) {
			$(this).removeClass('ui-state-hover');
			
			//adding to students table
			var studentID = ui.draggable.data('studentID');
			var studentMail = ui.draggable.data('studentMail');
			var studentFirstname = ui.draggable.data('studentFirstname');
			var studentSurname = ui.draggable.data('studentSurname');
			var studentIndex = ui.draggable.data('studentIndex');
			
			$(' #groups .group.' + currGroup.id + ' .students').append('<tr class="student ' + studentID + '" id="student' + studentID + '"><td class="name"><a href="mailto:' + studentMail + '">' + studentFirstname + ' ' + studentSurname + '</a><br /><span class="index">(' + studentIndex + ')</span></td></tr>');
			
			$.each(currGroup.meetings, function() {
				currMeeting = this;
				$('#groups .group.' + currGroup.id + ' .students tr.student.' + studentID).append('<td class="meeting ' + currMeeting.id + '"></td>');
			});
			
			//TO DO: Ajax getting marksandpresence
			
			ui.draggable.remove();
			
			$('#notingroup td').first().addClass('top');
			
			if($('#notingroup td').hasClass('top')) {
				$('#notingroup td').css('borderTop',  '1px solid #e0e0e0');
			}
		},
		over: function(e, ui) {
			$(this).addClass('ui-state-hover');
		},
		out: function(e, ui) {
			$(this).removeClass('ui-state-hover');
		}
	});
	
	$('#groups .group.' + currGroup.id + ' .students tr.header td.add-meeting a').click(function(e) {
		e.preventDefault();
		
		//first we need to connect to database and get new meeting id
		newMeeting = new Object();
		newMeeting.id = 1000;
		newMeeting.name = "";
		newMeeting.date = "";
		newMeeting.weight = "";
		
		//TO DO: getting array of newly created cells {meetingid {studentid, markid, presenceid}}
		
		addMeeting(currGroup, newMeeting);
	});
	
	//adding students header
	$.each(currGroup.students, function() {
		currStudent = this;
		
		$(' #groups .group.' + currGroup.id + ' .students').append('<tr class="student ' + currStudent.id + '" id="student' + currStudent.id + '"><td class="name"><a href="mailto:' + currStudent.mail + '">' + currStudent.firstname + ' ' + currStudent.surname + '</a><br /><span class="index">(' + currStudent.index + ')</span></td></tr>');
	
		//adding meeting to each student
		/*$.each(currGroup.meetings, function() {
			currMeeting = this;
			$('#groups .group.' + currGroup.id + ' .students tr.student.' + currStudent.id).append('<td class="meeting ' + currMeeting.id + '"></td>');
		});*/
		
		
	});
	
	//adding meetings header
	$.each(currGroup.meetings, function() {
		addMeeting(currGroup, this);
	});
	
	$('#groups .group.' + currGroup.id + ' .details').hide();
	
	var group = currGroup;
	$('#groups .group.' + group.id + ' header').click(function() {
		
		if($('.group.' + group.id).hasClass('active')) {
			$(this).next().hide('blind', 200, function() {
				$('#groups .group.' + group.id).removeClass('active');
			});
		} else {
			$(this).next().show('blind', 200, function() {
				$('#groups .group.' + group.id).addClass('active');
			});
		}
	});
	
	$('#groups .group.' + group.id + ' header .tools a.edit-group').click(function() {
		console.log("edit group " + group.id);
		
		e.preventDefault();
		$('div#lightbox').show();
		
		$('#edit-group').show();
		$('#edit-group').css('left', $(document).width()/2 - $('#edit-group').width()/2);
		$('#edit-group').css('top', $(document).height()/2 - $('#edit-group').height()/2-50);
		
		$('#edit-group-course').html(currDate.name);
		
		$('#edit-group-name').val(group.name);
		$('#edit-group-subject').val(group.subject);
		$('#edit-group-repo').val(group.repo);
		$('#edit-group-comment').val(group.comment);
	});
	
	$('#add-group input#edit-group-submit-button').click(function(e) {
		e.preventDefault();
		
		editGroup.id = group.id;
		editGroup.name = $('#edit-group-name').val();
		editGroup.subject = $('#edit-group-subject').val();
		editGroup.repo = $('#edit-group-repo').val();
		editGroup.comment = $('#edit-group-comment').val();
		
		currGroup = editGroup;
		
		$.ajax({
			url: serverURL + 'editgroup',
			type: 'POST',
			data: {groupid: currGroup.id, name: currGroup.name, subject: currGroup.subject, repo: currGroup.repo, comment: currGroup.comment},
			dataType: 'json',
			success: function(data, textStatus, jqXHR ) {
				console.log("Add group: " + data + " " + textStatus);
				
				if(data == '1') {
					$('div#add-group td.info').show();
					$('div#add-group td.info').html('<span class="success">Grupa została zmieniona!</span>');
					
					//zmiana danych grupy na stronie
					$('#groups .group.' + currGroup.id + ' .subject').html(currGroup.subject);
					
				} else {
					$('div#add-group td.info').show();
					$('div#add-group td.info').html('<span class="error">Błąd podczas zmiany grupy!</span>');
				}
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(textStatus + ' ' + errorThrown);
			}
		});
	});
}

function unloadDate(id) {
	console.log("unload date" + id);
	
	//moving from groups and notingroup
	$('#notingroup .course-date.' + id).remove();
	$('#groups .course-date.' + id).remove();
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