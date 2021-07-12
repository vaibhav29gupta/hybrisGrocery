ACC.productDetail = {

	_autoload : [ "initPageEvents", "bindVariantOptions", "bindFacets" ],

	checkQtySelector : function(self, mode) {
		var $qtySelector = $(document).find(self).parents(".js-qty-selector");
		var input = $qtySelector.find(".js-qty-selector-input");
		var inputVal = parseInt(input.val());
		var max = input.data("max");
		var minusBtn = $qtySelector.find(".js-qty-selector-minus");
		var plusBtn = $qtySelector.find(".js-qty-selector-plus");
		$qtySelector.find(".btn").removeAttr("disabled");

		if (mode == "minus") {
			if (inputVal != 0) {
				ACC.productDetail.updateQtyValue(self, inputVal - 1)
				if (inputVal - 1 == 0) {
					minusBtn.attr("disabled", "disabled")
				}
				ACC.productDetail.addProductToCart(inputVal - 1)
			} else {
				minusBtn.attr("disabled", "disabled")
			}
		} else if (mode == "reset") {
			ACC.productDetail.updateQtyValue(self, 1)
			ACC.productDetail.addProductToCart(1)

		} else if (mode == "plus") {
			if (max == "FORCE_IN_STOCK") {
				ACC.productDetail.updateQtyValue(self, inputVal + 1)
				ACC.productDetail.addProductToCart(inputVal + 1)
			} else if (inputVal <= max) {
				ACC.productDetail.updateQtyValue(self, inputVal + 1)
				if (inputVal + 1 == max) {
					plusBtn.attr("disabled", "disabled")
				}
				ACC.productDetail.addProductToCart(inputVal + 1)
			} else {
				plusBtn.attr("disabled", "disabled")
			}
		} else if (mode == "input") {
			if (inputVal == 0) {
				minusBtn.attr("disabled", "disabled")
			} else if (max == "FORCE_IN_STOCK" && inputVal >= 0) {
				ACC.productDetail.updateQtyValue(self, inputVal)
			} else if (inputVal == max) {
				plusBtn.attr("disabled", "disabled")
			} else if (inputVal < 0) {
				ACC.productDetail.updateQtyValue(self, 0)
				minusBtn.attr("disabled", "disabled")
			} else if (inputVal > max) {
				ACC.productDetail.updateQtyValue(self, max)
				plusBtn.attr("disabled", "disabled")
			}
		} else if (mode == "focusout") {
			if (isNaN(inputVal) || inputVal == 0) {
				ACC.productDetail.updateQtyValue(self, 0);
				minusBtn.attr("disabled", "disabled");
				ACC.productDetail.addProductToCart(0)
			} else if (inputVal >= max) {
				plusBtn.attr("disabled", "disabled");
				ACC.productDetail.updateQtyValue(self, max);
				ACC.productDetail.addProductToCart(max)
			} else if (inputVal > 0) {
				ACC.productDetail.updateQtyValue(self, inputVal);
				ACC.productDetail.addProductToCart(inputVal)
			}

		}

	},

	updateQtyValue : function(self, value) {
		var input = $(document).find(self).parents(".js-qty-selector").find(
				".js-qty-selector-input");
		var addtocartQty = $(document).find(self).parents(
				".addtocart-component").find("#addToCartForm").find(
				".js-qty-selector-input");
		var configureQty = $(document).find(self).parents(
				".addtocart-component").find("#configureForm").find(
				".js-qty-selector-input");
		input.val(value);
		addtocartQty.val(value);
		configureQty.val(value);
	},

	addProductToCart : function(newCartQuantity) {
		var form = $(document).find('form[id=addToCartForm]');
		var initialCartQuantity = form.find('input[name=initialQuantity]')
				.val();
		if (initialCartQuantity != newCartQuantity) {
			form.submit();
			return true;
		}
		return false;
	},

	initPageEvents : function() {
		$(document).on("click", '.js-qty-selector .js-qty-selector-minus',
				function() {
					ACC.productDetail.checkQtySelector(this, "minus");
				})

		$(document).on("click", '.js-qty-selector .js-qty-selector-plus',
				function() {
					ACC.productDetail.checkQtySelector(this, "plus");
				})

		$(document)
				.on(
						"keydown",
						'.js-qty-selector .js-qty-selector-input',
						function(e) {

							if (($(this).val() != " " && ((e.which >= 48 && e.which <= 57) || (e.which >= 96 && e.which <= 105)))
									|| e.which == 8
									|| e.which == 46
									|| e.which == 37
									|| e.which == 39
									|| e.which == 9) {
							} else if (e.which == 38) {
								ACC.productDetail
										.checkQtySelector(this, "plus");
							} else if (e.which == 40) {
								ACC.productDetail.checkQtySelector(this,
										"minus");
							} else {
								e.preventDefault();
							}
						})

		$(document).on("keyup", '.js-qty-selector .js-qty-selector-input',
				function(e) {
					ACC.productDetail.checkQtySelector(this, "input");
					ACC.productDetail.updateQtyValue(this, $(this).val());

				})

		$(document).on("focusout", '.js-qty-selector .js-qty-selector-input',
				function(e) {
					ACC.productDetail.checkQtySelector(this, "focusout");
					ACC.productDetail.updateQtyValue(this, $(this).val());
				})

		$("#Size").change(function() {
			changeOnVariantOptionSelection($("#Size option:selected"));
		});

		$("#variant").change(function() {
			changeOnVariantOptionSelection($("#variant option:selected"));
		});

		$(".selectPriority").change(function() {
			window.location.href = $(this[this.selectedIndex]).val();
		});

		function changeOnVariantOptionSelection(optionSelected) {
			window.location.href = optionSelected.attr('value');
		}
	},

	bindVariantOptions : function() {
		ACC.productDetail.bindCurrentStyle();
		ACC.productDetail.bindCurrentSize();
		ACC.productDetail.bindCurrentType();
	},

	bindCurrentStyle : function() {
		var currentStyle = $("#currentStyleValue").data("styleValue");
		var styleSpan = $(".styleName");
		if (currentStyle != null) {
			styleSpan.text(": " + currentStyle);
		}
	},

	bindCurrentSize : function() {
		var currentSize = $("#currentSizeValue").data("sizeValue");
		var sizeSpan = $(".sizeName");
		if (currentSize != null) {
			sizeSpan.text(": " + currentSize);
		}
	},

	bindCurrentType : function() {
		var currentSize = $("#currentTypeValue").data("typeValue");
		var sizeSpan = $(".typeName");
		if (currentSize != null) {
			sizeSpan.text(": " + currentSize);
		}
	},
	
	bindFacets : function() {
        $(document).on("click",".filter-facet-button",function(e) {
                    $(".facet-refine-hidden").slideToggle(500, function () {console.log("entered");
                });
        })
    }
};