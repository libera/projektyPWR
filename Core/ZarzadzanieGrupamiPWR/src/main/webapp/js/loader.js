var serverURL = 'http://localhost:8080/tut/';

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
		$('div#login td.info').hide();
		$('div#login td.info').html('');
		
		var user = $.trim($('input#login-user'));
		var pass = $.trim($('input#login-pass'));
		
		$.ajax({
			url: serverURL + "login",
			type: 'POST',
			data: {user: user, pass: $.md5(pass)},
			success: function(data, textStatus, jqXHR ) {
				if(data.logged == '1') {
					hideLogin();
					showContent();
				} else {
					$('div#login td.info').show();
					$('div#login td.info').html('<span class="error">Nie można zalogować. Błędny login lub hasło.</span>');
				}
			},
			error: function(jqXHR, textStatus, errorThrown) {
				$('div#login td.info').show();
				$('div#login td.info').html('<span class="error">Nie można zalogować. Powód:<br />' + errorThrown + '</span>');
			}
		});
		
		
	});
	
	$('input#register-button').click(function() {
		$('div#register td.info').hide();
		$('div#register td.info').html('');
		
		var firstname = $.trim($('input#register-firstname').val());
		var surname = $.trim($('input#register-surname').val());
		var email = $.trim($('input#register-email').val());
		var user = $.trim($('input#register-user').val());
	//	var pass = $('input#register-pass').val();
	//	var passRepeat = $('input#register-pass-repeat').val();
		
		//password & empty test
		/*if(pass != passRepeat) {
			$('div#register td.info').show();
			$('div#register td.info').html('<span class="error">Podane hasła nie są identyczne!</span>');
		} */
		 if(firstname == "" || surname == "" || email == "" || user == "" ) {
			$('div#register td.info').show();
			$('div#register td.info').html('<span class="error">Wszystkie pola muszą być wypełnione!</span>');
		} else {
			$.ajax({
				url: serverURL + "register",
				type: 'POST',
				data: {firstname: firstname, surname: surname, email: email, user: user/*, pass: pass*/},
				success: function(data, textStatus, jqXHR ) {
					if(data.registered == '1') {
						hideRegister();
						showContent();
					} else {
						$('div#login td.info').show();
						$('div#login td.info').html('<span class="error">Nie można zarejestrować. Powód:<br />' + textStatus +  '</span>');
					}
					
					$('div#register td.info').show();
					$('div#register td.info').html('<span class="success">Zarejestrowano pomyślnie</span>');
				},
				error: function(jqXHR, textStatus, errorThrown) {
					$('div#register td.info').show();
					$('div#register td.info').html('<span class="error">Nie można zarejestrować. Powód:<br />' + errorThrown + '</span>');
				}
			});
		}
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