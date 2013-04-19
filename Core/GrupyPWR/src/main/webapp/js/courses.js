//sample json courses data
var coursesSample = {'courses':
	[
		{'name':'Informatyka w gospodarce', 'id':'1', 'dates':
			[
				{'id':'1', 'code':'bla1', 'name':'PN 13.15'},
				{'id':'2', 'code':'bla2', 'name':'WT 11.15'},
				{'id':'3', 'code':'bla3', 'name':'PT 19.55'},
			]
		},
		{'name':'Inżynieria oprogramowania', 'id':'2', 'dates':
			[
				{'id':'4', 'code':'bla4', 'name':'PN 13.15'},
				{'id':'5', 'code':'bla5', 'name':'WT 11.15'},
				{'id':'6', 'code':'bla6', 'name':'PT 19.55'},
			]
		},
		{'name':'Architektura komputerów', 'id':'3', 'dates':
			[
				{'id':'7', 'code':'bla7', 'name':'PN 13.15'},
				{'id':'8', 'code':'bla8', 'name':'WT 11.15'},
				{'id':'9', 'code':'bla9', 'name':'PT 19.55'},
			]
		},
		{'name':'Inny przedmiot', 'id':'4', 'dates':
			[
				{'id':'10', 'code':'bla10', 'name':'PN 13.15'},
				{'id':'11', 'code':'bla11', 'name':'WT 11.15'},
				{'id':'12', 'code':'bla12', 'name':'PT 19.55'},
			]
		}
	]
};

function initCourses() {
	//courses tools
	$('a#import-csv-button').click(function(e) {
		e.preventDefault();
		$('div#lightbox').show();
		
		$('#upload-csv').show();
		$('#upload-csv').css('left', $(document).width()/2 - $('#upload-csv').width()/2);
		$('#upload-csv').css('top', $(document).height()/2 - $('#upload-csv').height()/2-50);
	});
	
	$('#upload-csv input:button').click(function(e) {
		e.preventDefault();
		var fileContent = new FormData($('#upload-csv form')[0]);
		
		$.ajax({
			url: serverURL + 'importcsv',
			type: 'POST',
			success: function(data, textStatus, jqXHR) {
				alert(data + " " + textStatus);
				
				//showing imported dialog
				/*$('div#lightbox').show();
				$('#importcsv-success').show();
				$('#importcsv-success').css('left', $(document).width()/2 - $('#importcsv-success').width()/2);
				$('#importcsv-success').css('top', $(document).height()/2 - $('#importcsv-success').height()/2-50);*/
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert(textStatus + " " + errorThrown);
			},
			data: {filecontent: fileContent},
			cache: false,
			contentType: false,
			processData: false
		});
	});
}

function loadCourses() {
	//clearing previous
	$('#courses').html('');

	//TO DO: get courses by http request
	$.each(coursesSample.courses, function() {
		currCourse = this;
	
		$('#courses').append(
			'<li class="course ' + currCourse.id + '">' + 
				'<header>' + currCourse.name + '</header><ul>');
		
		$.each(currCourse.dates, function() {
			$('#courses li.course.' + currCourse.id + ' ul').append('<li class="date ' + this.id + '"><input type="checkbox" name="checkedDates" id="date-' + this.id + '-check" value="' + this.id + '" /><label for="date-' + this.id + '-check">' + this.name + ' (' + this.code + ')</label></li>');
		});
		
		$('#courses').append('</ul></li>');
	});
	
	$('#courses li.course ul').hide();
	
	$('#courses li.course header').click(function() {
		if(!$(this).parent().hasClass('active')) {
			//hiding other courses
			$('#courses li.course ul').hide('blind', {}, 200);
			$('#courses li.course').removeClass('active');
			
			//removing all checks
			$('input[name="checkedDates"]:checked').attr('checked', false);
			
			//showing clicked course
			$(this).next().show('blind', {}, 200);
			$(this).parent().addClass('active');
		}
	});
	
	//dates click events
	$('#courses li.course ul li input').change(function() {
		loadGroups();
	});
}