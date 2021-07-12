ACC.addtocartaction = {
	_autoload : [ "initPageEvents" ],

//	initPageEvents : function() {
//		$(document).on("click", '.js-qty-selector-minus', function() {
//			var productCode = $(this).data("productCode");
//			ACC.addtocartaction.checkQtySelector(this, "minus", productCode);
//		});
//
//		$(document).on("click", '.js-qty-selector-plus', function() {
//			var productCode = $(this).data("productCode");
//			ACC.addtocartaction.checkQtySelector(this, "plus", productCode);
//		});
//
//		$(document)
//				.on(
//						"keydown",
//						'.js-qty-selector-input',
//						function(e) {
//							var productCode = $(this).attr('data-product-code');
//							if (($(this).val() != " " && ((e.which >= 48 && e.which <= 57) || (e.which >= 96 && e.which <= 105)))
//									|| e.which == 8
//									|| e.which == 46
//									|| e.which == 37
//									|| e.which == 39
//									|| e.which == 9) {
//							} else if (e.which == 38) {
//								ACC.addtocartaction.checkQtySelector(this,
//										"plus", productCode);
//							} else if (e.which == 40) {
//								ACC.addtocartaction.checkQtySelector(this,
//										"minus", productCode);
//							} else {
//								e.preventDefault();
//							}
//						});
//
//		$(document).on(
//				"keyup",
//				'.js-qty-selector-input',
//				function(e) {
//					var productCode = $(this).attr('data-product-code');
//					ACC.addtocartaction.checkQtySelector(this, "input",
//							productCode);
//					ACC.addtocartaction.updateQtyValue(this, $(this).val(),
//							productCode);
//
//				});
//
//		$(document).on(
//				"focusout",
//				'.js-qty-selector-input',
//				function(e) {
//					var productCode = $(this).attr('data-product-code');
//					ACC.addtocartaction.checkQtySelector(this, "focusout",
//							productCode);
//					ACC.addtocartaction.updateQtyValue(this, $(this).val(),
//							productCode);
//				});
//
//	}
	
//	checkQtySelector : function(self, mode, productCode) {
//		var $qtySelector = $(document).find(self).parents(
//				".js-qty-selector-" + productCode);
//		if ($qtySelector.data("isShipmentSet")) {
//			console.log($qtySelector.data("isShipmentSet"));
//			ACC.shipment.showShipmentForm();
//		}
//		var input = $qtySelector.find(".js-qty-selector-input");
//		var inputVal = parseInt(input.val());
//		var max = input.data("max");
//		var minusBtn = $qtySelector.find(".js-qty-selector-minus");
//		var plusBtn = $qtySelector.find(".js-qty-selector-plus");
//		$qtySelector.find(".btn").removeAttr("disabled");
//
//		if (mode == "minus") {
//			if (inputVal != 0) {
//				ACC.addtocartaction.updateQtyValue(self, inputVal - 1,
//						productCode)
//				if (inputVal - 1 == 0) {
//					minusBtn.attr("disabled", "disabled")
//				}
//				ACC.addtocartaction.addProductToCart(inputVal - 1, productCode)
//			} else {
//				minusBtn.attr("disabled", "disabled")
//			}
//		} else if (mode == "reset") {
//			ACC.addtocartaction.updateQtyValue(self, 0, productCode)
//			ACC.addtocartaction.addProductToCart(0, productCode)
//
//		} else if (mode == "plus") {
//			if (max == "FORCE_IN_STOCK") {
//				ACC.addtocartaction.updateQtyValue(self, inputVal + 1,
//						productCode)
//				ACC.addtocartaction.addProductToCart(inputVal + 1)
//			} else if (inputVal <= max) {
//				ACC.addtocartaction.updateQtyValue(self, inputVal + 1,
//						productCode)
//				if (inputVal + 1 == max) {
//					plusBtn.attr("disabled", "disabled")
//				}
//				ACC.addtocartaction.addProductToCart(inputVal + 1, productCode)
//			} else {
//				plusBtn.attr("disabled", "disabled")
//			}
//		} else if (mode == "input") {
//			if (inputVal == 0) {
//				minusBtn.attr("disabled", "disabled");
//			} else if (max == "FORCE_IN_STOCK" && inputVal >= 0) {
//				ACC.addtocartaction.updateQtyValue(self, inputVal, productCode);
//			} else if (inputVal == max) {
//				plusBtn.attr("disabled", "disabled");
//			} else if (inputVal < 0) {
//				ACC.addtocartaction.updateQtyValue(self, 0, productCode);
//				minusBtn.attr("disabled", "disabled");
//			} else if (inputVal > max) {
//				ACC.addtocartaction.updateQtyValue(self, max, productCode);
//				plusBtn.attr("disabled", "disabled");
//			}
//		} else if (mode == "focusout") {
//			if (isNaN(inputVal) || inputVal == 0) {
//				ACC.addtocartaction.updateQtyValue(self, 0, productCode);
//				minusBtn.attr("disabled", "disabled");
//				ACC.addtocartaction.addProductToCart(0, productCode);
//			} else if (inputVal >= max) {
//				plusBtn.attr("disabled", "disabled");
//				ACC.addtocartaction.updateQtyValue(self, max, productCode);
//				ACC.addtocartaction.addProductToCart(max, productCode);
//			} else if (inputVal > 0) {
//				ACC.addtocartaction.updateQtyValue(self, inputVal, productCode);
//				ACC.addtocartaction.addProductToCart(inputVal, productCode);
//			}
//		}
//
//	},
//
//	updateQtyValue : function(self, value, productCode) {
//		var input = $(document).find(self).parents(
//				".js-qty-selector-" + productCode).find(
//				".js-qty-selector-input");
//		var addtocartactionQty = $(document).find(self).parents(
//				".addtocartaction-component").find(
//				"#addToCartForm" + productCode).find(".js-qty-selector-input");
//		var configureQty = $(document).find(self).parents(
//				".addtocartaction-component").find(
//				"#configureForm" + productCode).find(".js-qty-selector-input");
//		input.val(value);
//		addtocartactionQty.val(value);
//		configureQty.val(value);
//	},
//
//	addProductToCart : function(newCartQuantity, productCode) {
//		var form = $(document).find(
//				"form[id=addToCartForm-" + productCode + "]");
//		var initialCartQuantity = form.find(
//				'input[name=initialQuantity-' + productCode + ']').val();
//		if (initialCartQuantity != newCartQuantity) {
//			ACC.addtocartaction.bindToAddToCartForm(productCode, form);
//			form.submit();
//			return true;
//		}
//		return false;
//	},
//
//	bindToAddToCartForm : function(productCode, form) {
//		form.ajaxForm({
//			success : function(res, status, xhr, form) {
//				ACC.addtocartaction.displayAddToCartPopup(res, productCode)
//			}
//		});
//	},
//
//	displayAddToCartPopup : function(cartResult, productCode) {
//		$ajaxCallEvent = true;
//		if (cartResult.sessionError.message === 'No mode of shipment') {
//			var quantity = $('.js-initial-quantity-' + productCode).val();
//			$('.js-qty-selector-input').val(quantity);
//			$("#shipment-method-component").removeClass("display-none");
//		} else {
//			$('.js-initial-quantity-' + productCode).val(
//					cartResult.updatedQuantity);
//			$('.js-qty-selector-input-' + productCode).val(
//					cartResult.updatedQuantity);
//			$('#addToCartLayer').remove();
//			if (typeof ACC.minicart.updateMiniCartDisplay == 'function') {
//				ACC.minicart.updateMiniCartDisplay();
//			}
//			var titleHeader = $('#addToCartTitle').html();
//
//			ACC.colorbox.open(titleHeader, {
//				html : cartResult.addToCartLayer,
//				width : "460px"
//			});
//
//			var quantityField = $('.js-qty-selector-input-' + productCode)
//					.val();
//
//			var quantity = 1;
//			if (quantityField != undefined) {
//				quantity = quantityField;
//			}
//
//			var cartAnalyticsData = cartResult.cartAnalyticsData;
//
//			var cartData = {
//				"cartCode" : cartAnalyticsData.cartCode,
//				"productCode" : productCode,
//				"quantity" : quantity,
//				"productPrice" : cartAnalyticsData.productPostPrice,
//				"productName" : cartAnalyticsData.productName
//			};
//			ACC.track.trackAddToCart(productCode, quantity, cartData);
//		}
//	},
//
//	initPageEvents : function() {
//		$(document).on("click", '.js-qty-selector-minus', function() {
//			var productCode = $(this).data("productCode");
//			ACC.addtocartaction. (this, "minus", productCode);
//		});
//
//		$(document).on("click", '.js-qty-selector-plus', function() {
//			var productCode = $(this).data("productCode");
//			ACC.addtocartaction.checkQtySelector(this, "plus", productCode);
//		});
//
//		$(document)
//				.on(
//						"keydown",
//						'.js-qty-selector-input',
//						function(e) {
//							var productCode = $(this).attr('data-product-code');
//							if (($(this).val() != " " && ((e.which >= 48 && e.which <= 57) || (e.which >= 96 && e.which <= 105)))
//									|| e.which == 8
//									|| e.which == 46
//									|| e.which == 37
//									|| e.which == 39
//									|| e.which == 9) {
//							} else if (e.which == 38) {
//								ACC.addtocartaction.checkQtySelector(this,
//										"plus", productCode);
//							} else if (e.which == 40) {
//								ACC.addtocartaction.checkQtySelector(this,
//										"minus", productCode);
//							} else {
//								e.preventDefault();
//							}
//						});
//
//		$(document).on(
//				"keyup",
//				'.js-qty-selector-input',
//				function(e) {
//					var productCode = $(this).attr('data-product-code');
//					ACC.addtocartaction.checkQtySelector(this, "input",
//							productCode);
//					ACC.addtocartaction.updateQtyValue(this, $(this).val(),
//							productCode);
//
//				});
//
//		$(document).on(
//				"focusout",
//				'.js-qty-selector-input',
//				function(e) {
//					var productCode = $(this).attr('data-product-code');
//					ACC.addtocartaction.checkQtySelector(this, "focusout",
//							productCode);
//					ACC.addtocartaction.updateQtyValue(this, $(this).val(),
//							productCode);
//				});
//
//	}
};
