ACC.checkoutsteps = {

	_autoload: [
		"permeateLinks","onchangeShipmentMethod"
	],
			
	permeateLinks: function() {
	
		$(document).on("click",".js-checkout-step",function(e){
			e.preventDefault();
			window.location=$(this).closest("a").attr("href")
		})		
	},
	
	onchangeShipmentMethod:function(){
		$('input[type=radio][name=checkout-shipment-type]').click(function(e) {
			e.preventDefault();
			ACC.shipment.showSelectorInEditMode();
		});
		$('.js-edit-pickup').click(function(e){
			e.preventDefault();
			ACC.shipment.showSelectorInEditMode();
		})
	},
	
	editShipmentMethod:function(){
		ACC.shipment.openShipmentColorbox("#shipment-method-component");
		$(".change-shipment .btn-a").parent().hide();
		$(".choose-shipment").show();
	}
};