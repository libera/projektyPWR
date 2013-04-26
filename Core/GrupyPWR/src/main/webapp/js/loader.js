var serverURL = 'http://localhost:8080/tut/';
var userID = -1;
var userName = "";

function resizeWindow() {
	//setting right-col width
	$('.middle-col').width($(document).width() - $('.left-col').width() - $('.right-col').width() - 12);
	
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

function initLightbox() {
	$('#lightbox .bg').click(function() {
		$('.dialog').hide();
		$('#lightbox').hide();
	});
}

function initUser() {
	$('#user-change-pass').click(function(e) {
		e.preventDefault();
		
		$('div#change-pass td.info').html('');
    	$('div#change-pass td.info').hide();
		
		$('div#lightbox').show();
		
		$('div#change-pass td.info').html('');
    	$('div#change-pass td.info').hide();
		
		$('#change-pass').show();
		$('#change-pass').css('left', $(document).width()/2 - $('#change-pass').width()/2);
		$('#change-pass').css('top', $(document).height()/2 - $('#change-pass').height()/2-50);
	});
	
	$('#change-pass-button').click(function() {
		var oldPass = $('#change-pass-old').val();
		var newPass = $('#change-pass-new').val();
		var newPassRepeat = $('#change-pass-repeat').val();
		
		if(newPass != newPassRepeat) {
			$('div#change-pass td.info').show();
			$('div#change-pass td.info').html('<span class="error">Podane hasła nie są identyczne!</span>');
		} else if(oldPass == "" || newPass == "" || newPassRepeat == "") {
			$('div#change-pass td.info').show();
			$('div#change-pass td.info').html('<span class="error">Nie wszystkie pola zostały wypełnione!</span>');
		} else {
			$.ajax({
				url: serverURL + "changepass",
				type: 'POST',
				data: {userid: userID, oldpass: $.md5(oldPass), newpass: $.md5(newPass)},
				dataType: 'json',
				success: function(data, textStatus, jqXHR ) {
					if(data == '1') {
						$('div#change-pass td.info').show();
						$('div#change-pass td.info').html('<span class="success">Hasło zostało zmienione!</span>');
					} else {
						$('div#change-pass td.info').show();
						$('div#change-pass td.info').html('<span class="error">Hasło nie zostało zmienione!</span>');
					}
				},
				error: function(jqXHR, textStatus, errorThrown) {
					$('div#change-pass td.info').show();
					$('div#change-pass td.info').html('<span class="error">Nie można zmienić hasła. Powód:<br />' + errorThrown + '</span>');
				}
			});
		}
	});
	
	$('#user-logout').click(function(e) {
		e.preventDefault();
		
		$.ajax({
			url: serverURL + "logout",
			type: 'POST',
			data: {userid: userID},
			success: function(data, textStatus, jqXHR) {
				console.log(data + " " + textStatus);
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(textStatus + " " + errorThrown);
			}
		});
		
		userID = -1;
		userName = "";
		
		hideContent();
		showLogin();
		
		//showing logout dialog
		$('div#lightbox').show();
		$('#logout-success').show();
		$('#logout-success').css('left', $(document).width()/2 - $('#logout-success').width()/2);
		$('#logout-success').css('top', $(document).height()/2 - $('#logout-success').height()/2-50);
		
		//TO DO: clear dynamic data
		$('#login-user').val('');
		$('#login-pass').val('');
	});
}

function initLogin() {
	//register click event handlers
	$('input#login-button').click(function(e) {
		$('div#login td.info').hide();
		$('div#login td.info').html('');
		
		var user = $.trim($('input#login-user').val());
		var pass = $.trim($('input#login-pass').val());
		
		userName = user;
		
		$.ajax({
			url: serverURL + "login",
			type: 'POST',
			data: {user: user, pass: $.md5(pass)},
			success: function(data, textStatus, jqXHR) {
				if(data >= 0) {
					userID = parseInt(data);
					
					hideLogin();
					showContent();
					
					$("#user-logged-name").html(userName);
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
	
	//enter key event
	$('input#login-pass').keydown(function(e) {
		if(e.keyCode == '13') {
			$('#login-button').click();
		}
	});
}

function initRegister() {
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
		if(email.indexOf('pwr.wroc.pl') <= 0) {
			$('div#register td.info').show();
			$('div#register td.info').html('<span class="error">Adres email musi być w domenie pwr.wroc.pl!</span>');
		} else if(firstname == "" || surname == "" || email == "" || user == "" ) {
			$('div#register td.info').show();
			$('div#register td.info').html('<span class="error">Wszystkie pola muszą być wypełnione!</span>');
		} else {
			$.ajax({
				url: serverURL + "register",
				type: 'POST',
				data: {firstname: firstname, surname: surname, email: email, user: user},
				success: function(data, textStatus, jqXHR ) {
					if(data == '1') {
						hideRegister();
						showLogin();
						
						//showing registered dialog
						$('div#lightbox').show();
						$('#register-success').show();
						$('#register-success').css('left', $(document).width()/2 - $('#register-success').width()/2);
						$('#register-success').css('top', $(document).height()/2 - $('#register-success').height()/2-50);
					} else {
						$('div#login td.info').show();
						$('div#login td.info').html('<span class="error">Nie można zarejestrować. Powód:<br />' + textStatus +  '</span>');
					}
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
}

$(document).ready(function() {
	//when document is ready we display login window and hide content
	hideLightbox();
	hideContent();
	hideRegister();
	showLogin();
	
	//init functions for static elements - called only once!
	initLogin();
	initRegister();
	
	initLightbox();
	initCourses();
	initUser();
});

$(window).resize(function() {
	resizeWindow();
});

function hideLightbox() {
	$('div#lightbox').hide();
}