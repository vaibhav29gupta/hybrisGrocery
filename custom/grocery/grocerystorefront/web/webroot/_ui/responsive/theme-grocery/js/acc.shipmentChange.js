ACC.shipmentchange = {
		
	 showShipmentNotificationSection: function(e) {
		    e.preventDefault();
		    this.blur();
		    window.focus();
			$(shipmentTypeChangeNotification).removeClass('hidden');
			$('html,body').animate({
				scrollTop : $(shipmentTypeChangeNotification).offset().top - 250
			}, 'slow');
		},

		 hideShipmentNotificationSection: function() {
			$(shipmentTypeChangeNotification).addClass('hidden');
		 },
		 
		 showShipmentChangeSection: function(e) {
			 var changeShipmentUrl = $(this).data("changeshipment");
			 window.location.href = changeShipmentUrl;
		 }
};

var countrySelector = $(".page-multiStepCheckoutSummaryPage #countrySelector select");
var townCityInput = $("#town-city-input");
var postCodeInput = $("#post-code-input");

var shipmentTypeChangeNotification = $("#shipment-type-change-notification");
var shipmentChangedYesBtn = $("#shipment-changed-yes-btn");
var shipmentChangedNoBtn = $("#shipment-changed-no-btn");


$(countrySelector).click(ACC.shipmentchange.showShipmentNotificationSection);
$(townCityInput).click(ACC.shipmentchange.showShipmentNotificationSection);
$(postCodeInput).click(ACC.shipmentchange.showShipmentNotificationSection);

$(shipmentChangedYesBtn).click(ACC.shipmentchange.showShipmentChangeSection);
$(shipmentChangedNoBtn).click(ACC.shipmentchange.hideShipmentNotificationSection);

