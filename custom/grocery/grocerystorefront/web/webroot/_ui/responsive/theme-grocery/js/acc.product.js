ACC.product = {

	_autoload : [ "enableStorePickupButton", "enableVariantSelectors",
			"bindFacets" ],

	bindFacets : function() {
		$(document).on(
				"click",
				".js-show-facets",
				function(e) {
					e.preventDefault();
					var selectRefinementsTitle = $(this).data(
							"selectRefinementsTitle");
					var colorBoxTitleHtml = ACC.common
							.encodeHtml(selectRefinementsTitle);
					ACC.colorbox.open(colorBoxTitleHtml, {
						href : ".js-product-facet",
						inline : true,
						width : "480px",
						onComplete : function() {
							$(document).on(
									"click",
									".js-product-facet .js-facet-name",
									function(e) {
										e.preventDefault();
										$(".js-product-facet  .js-facet")
												.removeClass("active");
										$(this).parents(".js-facet").addClass(
												"active");
										$.colorbox.resize()
									})
						},
						onClosed : function() {
							$(document).off("click",
									".js-product-facet .js-facet-name");
						}
					});
				});
		enquire.register("screen and (min-width:" + screenSmMax + ")",
				function() {
					$("#cboxClose").click();
				});
	},

	enableVariantSelectors : function() {
		$('.variant-select').prop("disabled", false);
	},

	bindToAddToCartStorePickUpForm : function() {
		var addToCartStorePickUpForm = $('#colorbox #add_to_cart_storepickup_form');
		addToCartStorePickUpForm.ajaxForm({
			success : ACC.product.displayAddToCartPopup
		});
	},

	enableStorePickupButton : function() {
		$('.js-pickup-in-store-button').prop("disabled", false);
	},

};

if ($(".list-grid").length > 0) {
	$(window).scroll(function() {
		if ($(window).scrollTop() >= ($(document).height() - $(window).height())*0.9)  {
			if ($(window).data('ajax_in_progress') === true)
				return;
			$(window).data('ajax_in_progress', true);
			var listingDiv = $(".list-grid").children().last();//this div is for search page
			var listingDivSearch=$(".product__list--wrapper ul.list-grid").children().last();//this div is for category page
			$form = $(".btn-show-more-product-listing").closest(".show-more-products-wrapper").find("form");
			var pageNo=parseInt($("#pageNo").val());
			
			var showMoreUrl = ACC.config.encodedContextPath + $form.attr("action");
			showMoreUrl = showMoreUrl + "/results-display";
			$.ajax({
				url : showMoreUrl,
				data : $form.serialize(),
				type : "GET",
				success :function(data) {
					if(data.length<=7 && $("input[name=page]").val()>1)
					{
						$("#endNotification").show()
					}
					else{
						$("#endNotification").hide();
						$(window).data('ajax_in_progress', false);
						$("#pageNo").val(pageNo+1);
						var listingReplacementDiv = data;
						$(listingReplacementDiv).insertAfter(listingDiv);//category list page
						$(listingReplacementDiv).insertAfter(listingDivSearch);//search list page 
					}
					
				},
				error : function(data) {
					$(window).data('ajax_in_progress', false);
					
				}
			});
		}
		
	});
}