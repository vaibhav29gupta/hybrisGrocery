var loginSection = $(".loginLeftContentSlot");
var registerSection = $(".loginRightContentSlot");
var loginBtn = $("#loginBtn");
var registerBtn = $("#loginNewAccBtn");

function showLoginSection() {
	$(registerSection).addClass('shownone');
	$(loginSection).removeClass('shownone');
	$(loginBtn).addClass("toggle-on");
	$(registerBtn).removeClass("toggle-on");
}

function showRegisterSection() {
	$(loginSection).addClass('shownone');
	$(registerSection).removeClass('shownone');
	$(registerBtn).addClass("toggle-on");
	$(loginBtn).removeClass("toggle-on");
}

$(document).on("click", "#loginBtn", function(e) {
	showLoginSection();
});

$(document).on("click", "#loginNewAccBtn", function(e) {
	showRegisterSection();
});

$(document).ready(function() {

	if (window.location.href.indexOf("register") > -1) {
		showRegisterSection();
	} else {
		showLoginSection();
	}

	$(document).on('click', '.js-register-link', function(){
	
		var url = $(this).data('register-url');
		window.location.href = url;
	
	});
});

