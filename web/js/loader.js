function resizeWindow() {
	var viewportWidth = $(document).width();
	//setting right-col width
	$('.right-col').width(viewportWidth - $('.left-col').width() - 30);
}

$(document).ready(function() {
	resizeWindow();
});



$(window).resize(function() {
	resizeWindow();
});