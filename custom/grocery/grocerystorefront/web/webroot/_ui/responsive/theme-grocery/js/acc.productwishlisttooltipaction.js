$(document).on("click", "#copyToClipboard", function(e) {
	var browserUrl = window.location.href;
	var tempInput = document.createElement("input");
	tempInput.style = "position: absolute; left: -1000px; top: -1000px";
	tempInput.value = browserUrl;
	document.body.appendChild(tempInput);
	tempInput.select();
	document.execCommand("copy");
	document.body.removeChild(tempInput);
	$("#copiedToClipboard").removeClass('display-none');
	$("#copyToClipboard").addClass('display-none');
	setTimeout(function() {
		$("#copiedToClipboard").addClass('display-none');
		$("#copyToClipboard").removeClass('display-none');
	}, 2000);
});

$(document).ready(function() {
	$('[data-toggle="tooltip"]').tooltip();
});
