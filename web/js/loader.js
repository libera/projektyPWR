
function resizeWindow() {
	//setting right-col width
	$('.right-col').width($(document).width() - $('.left-col').width() - 50);
	
	//setting cols height
	$('.left-col').height($(document).height() - 100);
	$('.right-col').height($(document).height() - 100);
}

function showContent() {
	$('#content').show();
	
	loadCourses();
	
	resizeWindow();
}

function hideContent() {
	$('#content').hide();
}

function showLogin() {
	$('#login').show();
	$('#login').css('left', $(document).width()/2 - $('#login').width()/2);
	$('#login').css('top', $(document).height()/2 - $('#login').height()/2-50);
	
	$('input#login-button').click(function() {
		//TO DO: ajax login operation
		hideLogin();
		showContent();
	});
}

function hideLogin() {
	$('#login').hide();
}

$(document).ready(function() {
	//when document is ready we display login window and hide content
	hideLightbox();
	hideContent();
	showLogin();
});

$(window).resize(function() {
	resizeWindow();
});

function hideLightbox() {
	$('div#lightbox').hide();
}