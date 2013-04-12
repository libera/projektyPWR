var serverURL = 'http://localhost:8080/pow/';

function resizeWindow() {
	//setting right-col width
	$('.middle-col').width($(document).width() - $('.left-col').width() - $('.right-col').width() - 32);
	
	//setting cols height
	$('.left-col').height($(document).height() - 100);
	$('.middle-col').height($(document).height() - 100);
}

function showContent() {
	$('#content').show();
	
	loadCourses();
	
	resizeWindow();
}

function hideContent() {
	$('#content').hide();
}

function showRegister() {
	$('#register').show();
	$('#register').css('left', $(document).width()/2 - $('#register').width()/2);
	$('#register').css('top', $(document).height()/2 - $('#register').height()/2-50);
}

function hideRegister() {
	$('#register').hide();
}

function showLogin() {
	$('#login').show();
	$('#login').css('left', $(document).width()/2 - $('#login').width()/2);
	$('#login').css('top', $(document).height()/2 - $('#login').height()/2-50);
}

function hideLogin() {
	$('#login').hide();
}

$(document).ready(function() {
	//when document is ready we display login window and hide content
	hideLightbox();
	hideContent();
	hideRegister();
	showLogin();
	
	//register click event handlers
	$('input#login-button').click(function() {
		$.ajax({
			url: serverURL + "login",
			type: 'GET',
			data: {user:'bla', pass: 'bla'}
		});
		
		hideLogin();
		showContent();
	});
	
	$('input#register-button').click(function() {
		alert('bla');
	});
	
	$('a#register-link').click(function(e) {
		e.preventDefault();
		hideLogin();
		showRegister();
	});
});

$(window).resize(function() {
	resizeWindow();
});

function hideLightbox() {
	$('div#lightbox').hide();
}