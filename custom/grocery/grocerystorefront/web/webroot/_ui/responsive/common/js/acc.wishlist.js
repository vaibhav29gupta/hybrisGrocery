ACC.wishlist = {

	_autoload : [ "clickOnNav" ],

	clickOnNav : function() {


		$(document).on("click", "#addToCart", function(e) {
			var formData = $("#customerPreferences");
			/*e.preventDefault();*/

			$.ajax({
				type : "POST",
				url : ACC.config.encodedContextPath + "/wishlist/wishlist-detail",
				data : formData.serialize(),
				success : function(data) {
					
				},
				error : function(data) {
					console.log('An error occurred.');
					console.log(data);
				},
			});

		});
	}
};
