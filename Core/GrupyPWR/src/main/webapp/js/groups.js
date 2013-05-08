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
					
					$('#notingroup').append('<tr id="student' + currNoGroup.id + '" class="student course-date ' + currDate.id + '"><td class="name"><a href="mailto:' + currNoGroup.mail + '">' + currNoGroup.firstname + ' ' + currNoGroup.surname + '</a><br /><span class="index">(' + currNoGroup.index + ')</span></td></tr>');
					
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
							$(this).hide();
						},
						stop: function(e, ui) {
							$(this).show();
						},
						appendTo: 'body',
						helper: 'clone'
					});
				});
				
				//adding in groups
				$('#groups').append('<div class="course-date ' + currDate.id + '"><header class="course-date-header">' + currDate.name +' (' + currDate.code + ')<div class="tools"><a href="#" class="add-group-button">dodaj grupe projektowa w tym terminie</a></div></header></div>');
				
				$.each(currDate.groups, function() {
					addGroup(currDate.id, this);
				});
				
				var date = currDate;
				
				//add group button
				$('#groups .course-date.' + date.id + ' .course-date-header .add-group-button').click(function(e) {
					e.preventDefault();
					$('div#lightbox').show();
					
					$('#add-group').show();
					$('#add-group').css('left', $(document).width()/2 - $('#add-group').width()/2);
					$('#add-group').css('top', $(document).height()/2 - $('#add-group').height()/2-50);
					
					$('#add-group-course').html(currDate.name);
					
					$('#add-group .info').html('');
					$('#add-group .info').hide('');
					
					$('#add-group-name').val('');
					$('#add-group-subject').val('');
					$('#add-group-repo').val('');
					$('#add-group-comment').val('');
					
					$('#add-group input#add-group-courseid').val(date.id);
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

	$('#meeting' + meeting.id + ' input.name').focus(function() {
		if($(this).val() == "nazwa spotkania") {
			$(this).val('');
		}
	});
	$('#meeting' + meeting.id + ' input.name').blur(function() {
		if($(this).val() == "") {
			$(this).val('nazwa spotkania');
		}
	});
	
	$('#meeting' + meeting.id + ' input.date').focus(function() {
		if($(this).val() == "data") {
			$(this).val('');
		}
	});
	$('#meeting' + meeting.id + ' input.date').blur(function() {
		if($(this).val() == "") {
			$(this).val('data');
		}
	});
	
	$('#meeting' + meeting.id + ' input.weight').focus(function() {
		if($(this).val() == "waga") {
			$(this).val('');
		}
	});
	$('#meeting' + meeting.id + ' input.weight').blur(function() {
		if($(this).val() == "") {
			$(this).val('waga');
		}
	});
	
	//console.log(JSON.stringify(currGroup.students));
	
	//adding new meeting to each student
	$.each(currGroup.students, function() {
		currStudent = this;
		
		$('.group.' + currGroup.id + ' .students tr.student.' + currStudent.id).append('<td class="meeting ' + meeting.id + '"></td>');
		
		if(currStudent.marksandpresence != undefined) {
			$.each(currStudent.marksandpresence, function() {
				currMarkAndPresence = this;
				
				$('.group.' + currGroup.id + ' .students tr.student.' + currStudent.id + ' .meeting.' + currMarkAndPresence.meetingid).html('<input type="checkbox" class="presence ' + currMarkAndPresence.presenceid + '" id="presence' + currMarkAndPresence.presenceid + '" /><label for="presence' + currMarkAndPresence.presenceid +'"></label><input type="text" maxlength="3" class="mark ' + currMarkAndPresence.markid + '" value="ocena" />');
				
				$('.group.' + currGroup.id + ' .students tr.student.' + currStudent.id + ' td.' + currMarkAndPresence.meetingid + ' .mark').val(currMarkAndPresence.mark);
				if(currMarkAndPresence.present == '1') {
					$('input#presence' + currMarkAndPresence.presenceid).attr('checked', true);
				}
			});
		}
		
		$('.group.' + currGroup.id + ' .students tr.student.' + currStudent.id + ' input.mark').focus(function() {
			if($(this).val() == "ocena") {
				$(this).val('');
			}
		});
		$('.group.' + currGroup.id + ' .students tr.student.' + currStudent.id + ' input.mark').blur(function() {
			if($(this).val() == "") {
				$(this).val('ocena');
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

function addGroup(dateID, group) {
	currGroup = group;
	
	console.log("dodanie grupy " + JSON.stringify(group));
	
	$('#groups .course-date.' + dateID).append('<div class="group ' + currGroup.id + '">' +
			'<header><span class="subject">' + currGroup.subject + '</span><div class="tools"><a href="#" class="more">Więcej</a><a href="#" class="remove-group">Usuń grupę projektową</a><a href="#" class="edit-group">Edytuj grupe projektowa</a></div></header>' +
			'<div class="details"><table class="students"><tr class="header"><td class="empty"></td><td class="add-meeting"><a href=#">dodaj</a></td></tr></table></div>' +
		'</div>');
	
	var group = currGroup;
		
	//enable user dropping
	$('#groups .group.' + group.id).droppable({
		drop: function(e, ui) {
			$(this).removeClass('ui-state-hover');
			
			//adding to students table
			var studentID = ui.draggable.data('studentID');
			var studentMail = ui.draggable.data('studentMail');
			var studentFirstname = ui.draggable.data('studentFirstname');
			var studentSurname = ui.draggable.data('studentSurname');
			var studentIndex = ui.draggable.data('studentIndex');
			
			//Ajax getting marksandpresence
			$.ajax({
				url: serverURL + 'addstudentgroup',
				type: 'POST',
				data: {studentid: studentID, groupid: group.id},
				dataType: 'json',
				success: function(data, textStatus, jqXHR ) {
					console.log("Add student to group: " + data + " " + textStatus);
					
					group.students.push({'id':studentID, 'firstname': studentFirstname, 'surname': studentSurname, 'mail': studentMail, 'index': studentIndex});
					
					$('#groups .group.' + group.id + ' .students').append('<tr class="student ' + studentID + '" id="student' + studentID + '"><td class="name"><a href="mailto:' + studentMail + '">' + studentFirstname + ' ' + studentSurname + '</a><br /><span class="index">(' + studentIndex + ')</span></td></tr>');
				
					$.each(group.meetings, function() {
						currMeeting = this;
						$('#groups .group.' + group.id + ' .students tr.student.' + studentID).append('<td class="meeting ' + currMeeting.id + '"></td>');
					});
					
					$.each(data.marksandpresence, function() {
						currMarkAndPresence = this;
						$('.group.' + group.id + ' .students tr.student.' + currStudent.id + ' .meeting.' + currMarkAndPresence.meetingid).html('<input type="checkbox" class="presence ' + currMarkAndPresence.presenceid + '" id="presence' + currMarkAndPresence.presenceid + '" /><label for="presence' + currMarkAndPresence.presenceid +'"></label><input type="text" maxlength="3" class="mark ' + currMarkAndPresence.markid + '" value="ocena" />');
					});
					
					$('.group.' + group.id + ' .students tr.student.' + studentID + ' input.mark').focus(function() {
						if($(this).val() == "ocena") {
							$(this).val('');
						}
					});
					$('.group.' + group.id + ' .students tr.student.' + studentID + ' input.mark').blur(function() {
						if($(this).val() == "") {
							$(this).val('ocena');
						}
					});
					
					ui.draggable.remove();
					
					$('#notingroup td').first().addClass('top');
				},
				error: function(jqXHR, textStatus, errorThrown) {
					console.log(textStatus + ' ' + errorThrown);
				}
			});
		},
		over: function(e, ui) {
			$(this).addClass('ui-state-hover');
		},
		out: function(e, ui) {
			$(this).removeClass('ui-state-hover');
		}
	});
	
	group = currGroup;
	
	$('#groups .group.' + currGroup.id + ' .students tr.header td.add-meeting a').click(function(e) {
		e.preventDefault();
		
		//first we need to connect to database and get new meeting id
		//getting array of newly created cells
		$.ajax({
			url: serverURL + 'addmeeting',
			type: 'POST',
			data: {groupid: group.id},
			dataType: 'json',
			success: function(data, textStatus, jqXHR ) {
				console.log("Add meeting: " + data + " " + textStatus);
				
				newMeeting = new Object();
				newMeeting.id = data.meetingid;
				newMeeting.name = "nazwa spotkania";
				newMeeting.date = "data";
				newMeeting.weight = "waga";
				
				$.each(data.marksAndPresence, function() {
					currMarkAndPresence = this;
					$.each(group.students, function() {
						if(this.id == currMarkAndPresence.studentid) {
							this.marksandpresence.push({'meetingid': data.meetingid, 'presenceid': currMarkAndPresence.presenceid, 'markid': currMarkAndPresence.markid, 'present': 0, 'mark': 0});
						}
					});
				});
				
				currGroup.meetings.push(newMeeting);
				addMeeting(group, newMeeting);
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(textStatus + ' ' + errorThrown);
			}
		});
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
	$('#groups .group.' + group.id + ' header .tools a.more').click(function() {
		
		if($('.group.' + group.id).hasClass('active')) {
			$('.group.' + group.id + ' .details').hide('blind', 200, function() {
				$('#groups .group.' + group.id).removeClass('active');
			});
		} else {
			$('.group.' + group.id + ' .details').show('blind', 200, function() {
				$('#groups .group.' + group.id).addClass('active');
			});
		}
	});
	
	$('#groups .group.' + group.id + ' header .tools a.remove-group').click(function(e) {
		e.preventDefault();
		
		console.log("remove group " + group.id);
		
		$.ajax({
			url: serverURL + 'removegroup',
			type: 'POST',
			data: {groupid: group.id},
			dataType: 'json',
			success: function(data, textStatus, jqXHR ) {
				console.log("Remove group: " + data + " " + textStatus);
				if(data == '1') {
					//usuwanie grupy ze strony
					$('#groups .group.' + group.id).remove();
				}
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(textStatus + ' ' + errorThrown);
			}
		});
	});
	
	$('#groups .group.' + group.id + ' header .tools a.edit-group').click(function(e) {
		e.preventDefault();
		
		console.log("edit group " + group.id);
		
		$('div#lightbox').show();
		
		$('#edit-group').show();
		$('#edit-group').css('left', $(document).width()/2 - $('#edit-group').width()/2);
		$('#edit-group').css('top', $(document).height()/2 - $('#edit-group').height()/2-50);
		
		$('#edit-group-name').val(group.name);
		$('#edit-group-subject').val(group.subject);
		$('#edit-group-repo').val(group.repo);
		$('#edit-group-comment').val(group.comment);
	});
	
	$('#edit-group input#edit-group-submit-button').click(function(e) {
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
				console.log("Edit group: " + data + " " + textStatus);
				if(data == '1') {
					$('div#edit-group td.info').show();
					$('div#edit-group td.info').html('<span class="success">Grupa została zmieniona!</span>');
					
					//zmiana danych grupy na stronie
					$('#groups .group.' + currGroup.id + ' .subject').html(currGroup.subject);
					
				} else {
					$('div#edit-group td.info').show();
					$('div#edit-group td.info').html('<span class="error">Błąd podczas zmiany grupy!</span>');
				}
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(textStatus + ' ' + errorThrown);
			}
		});
	});
}

function initGroups() {
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
		currGroup.courseid = $('#add-group-courseid').val();
		
		$.ajax({
			url: serverURL + 'addgroup',
			type: 'POST',
			data: {courseid: currGroup.courseid, name: currGroup.name, subject: currGroup.subject, repo: currGroup.repo, comment: currGroup.comment},
			dataType: 'json',
			success: function(data, textStatus, jqXHR ) {
				console.log("Add group: " + data + " " + textStatus);
				
				if(data >= '0') {
					$('div#add-group td.info').show();
					$('div#add-group td.info').html('<span class="success">Grupa została dodana!</span>');
					
					currGroup.id = data;
					
					addGroup(currGroup.courseid, currGroup);
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
}

function unloadDate(id) {
	console.log("unload date" + id);
	
	//moving from groups and notingroup
	$('#notingroup .course-date.' + id).remove();
	$('#groups .course-date.' + id).remove();
	
	//correcting border
	$('#notingroup td').first().addClass('top');
}