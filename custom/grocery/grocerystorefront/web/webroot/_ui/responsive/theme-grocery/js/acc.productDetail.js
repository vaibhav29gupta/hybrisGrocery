ACC.productDetail = {
    _autoload: ["initPageEvents", "bindVariantOptions", "bindFacets", "bindToReorder", "bindToRecipeCart"],

    initPageEvents: function () {
        $("#Size").change(function () {
            changeOnVariantOptionSelection($("#Size option:selected"));
        });

        $("#variant").change(function () {
            changeOnVariantOptionSelection($("#variant option:selected"));
        });
        
        $(".variant-selector input").each(function(index,value){
        	$(this).click(function () {
            changeOnVariantOptionSelection($(this));
        })});
        
        function changeOnVariantOptionSelection(optionSelected) {
			window.location.href = optionSelected.attr('value');
		}

        $(".selectPriority").change(function () {
            window.location.href = $(this[this.selectedIndex]).val();
        });

        $(document).on("click", ".js-qty-selector-minus", function () {
            ACC.productDetail.checkQtySelector(this, "minus");
        });

        $(document).on("click", ".js-qty-selector-plus", function () {
            ACC.productDetail.checkQtySelector(this, "plus");
        });

        $(document).on("keydown", ".js-qty-selector-input", function (e) {
            if (
                ($(this).val() != " " && ((e.which >= 48 && e.which <= 57) || (e.which >= 96 && e.which <= 105))) ||
                e.which == 8 ||
                e.which == 46 ||
                e.which == 37 ||
                e.which == 39 ||
                e.which == 9
            ) {
            } else if (e.which == 38) {
                ACC.productDetail.checkQtySelector(this, "plus");
            } else if (e.which == 40) {
                ACC.productDetail.checkQtySelector(this, "minus");
            } else {
                e.preventDefault();
            }
        });

        $(document).on("keyup", ".js-qty-selector-input", function (e) {
            ACC.productDetail.checkQtySelector(this, "input");
        });

        $(document).on("focusout", ".js-qty-selector-input", function (e) {
            ACC.productDetail.checkQtySelector(this, "focusout");
        });
    },

    checkQtySelector: function (self, mode) {
        var productCode = $(self).data("productCode");
        var $qtySelector = $(document)
            .find(self)
            .parents(".js-qty-selector-" + productCode);
        if ($qtySelector.length > 0 && !ACC.shipment.isShipmentSet()) {
            var quantity = $(".js-initial-quantity-" + productCode).val();
            $(".js-qty-selector-input").val(quantity);
            ACC.shipment.showSelectorInRawMode();
            return;
        }
        var input = $qtySelector.find(".js-qty-selector-input");
        var inputVal = parseInt(input.val());
        var max = input.data("max");
        $qtySelector.find(".btn").removeAttr("disabled");
        if (mode == "minus") {
            if (inputVal != 0) {
                ACC.productDetail.updateQtyValue(self, inputVal - 1);
                if (inputVal - 1 == 0) {
                    $(".js-minus-" + productCode).attr("disabled", "disabled");
                }
                ACC.productDetail.addProductToCart(self, inputVal - 1, productCode);
            } else {
                $(".js-minus-" + productCode).attr("disabled", "disabled");
            }
        } else if (mode == "reset") {
            ACC.productDetail.updateQtyValue(self, 0);
            ACC.productDetail.addProductToCart(self, 0, productCode);
        } else if (mode == "plus") {
            if (max == "FORCE_IN_STOCK") {
                ACC.productDetail.updateQtyValue(self, inputVal + 1);
                ACC.productDetail.addProductToCart(self, inputVal + 1);
            } else if (inputVal <= max) {
                ACC.productDetail.updateQtyValue(self, inputVal + 1);
                if (inputVal + 1 == max) {
                    $(".js-plus-" + productCode).attr("disabled", "disabled");
                }
                ACC.productDetail.addProductToCart(self, inputVal + 1, productCode);
            } else {
                $(".js-plus-" + productCode).attr("disabled", "disabled");
            }
        } else if (mode == "input") {
            if (inputVal == 0) {
                $(".js-minus-" + productCode).attr("disabled", "disabled");
            } else if (max == "FORCE_IN_STOCK" && inputVal >= 0) {
                ACC.productDetail.updateQtyValue(self, inputVal);
            } else if (inputVal == max) {
                $(".js-plus-" + productCode).attr("disabled", "disabled");
            } else if (inputVal < 0) {
                ACC.productDetail.updateQtyValue(self, 0);
                $(".js-minus-" + productCode).attr("disabled", "disabled");
            } else if (inputVal > max) {
                ACC.productDetail.updateQtyValue(self, max);
                $(".js-plus-" + productCode).attr("disabled", "disabled");
            }
        } else if (mode == "focusout") {
            if (isNaN(inputVal)) {
                var initialQty = $("input[name=initialQuantity-" + productCode + "]").val();
                ACC.productDetail.updateQtyValue(self, initialQty);
            } else if (inputVal >= max) {
                $(".js-plus-" + productCode).attr("disabled", "disabled");
                ACC.productDetail.updateQtyValue(self, max);
                ACC.productDetail.addProductToCart(self, max, productCode);
            } else if (inputVal >= 0) {
                if (inputVal == 0) $(".js-minus-" + productCode).attr("disabled", "disabled");
                ACC.productDetail.updateQtyValue(self, inputVal);
                ACC.productDetail.addProductToCart(self, inputVal, productCode);
            }
        }
    },

    updateQtyValue: function (self, value) {
        var productCode = $(self).data("productCode");
        var input = $(document)
            .find(self)
            .parents(".js-qty-selector-" + productCode)
            .find(".js-qty-selector-input");
        var productDetailQty = $(document)
            .find(self)
            .parents(".productDetail-component")
            .find("#addToCartForm" + productCode)
            .find(".js-qty-selector-input");
        var configureQty = $(document)
            .find(self)
            .parents(".productDetail-component")
            .find("#configureForm" + productCode)
            .find(".js-qty-selector-input");
        input.val(value);
        productDetailQty.val(value);
        configureQty.val(value);
        if (value == 1) {
            $(input).siblings(".input-group-btn").find(".fa-minus").removeClass("fa-minus").addClass("fa-trash");
        } else {
            $(input).siblings(".input-group-btn").find(".fa-trash").removeClass("fa-trash").addClass("fa-minus");
        }
    },

    addProductToCart: function (self, newCartQuantity, productCode) {
        var form = $(self)
            .parents(".js-qty-selector-" + productCode)
            .parents("#addToCartForm-" + productCode);
        var initialCartQuantity = form.find("input[name=initialQuantity-" + productCode + "]").val();
        if (initialCartQuantity != newCartQuantity) {
            ACC.productDetail.bindToAddToCartForm(productCode, form);

            //			For updating the prices of the items based on + & - Btn
            var windowUrl = window.location.href;
            if (windowUrl.includes("checkout")) {
                ACC.productDetail.updateTotalPrice(productCode, newCartQuantity);
            }
            form.submit();
            return true;
        }
        return false;
    },

    updateTotalPrice: function (productCode, newCartQuantity) {
        var method = "POST";
        $.ajax({
            url: ACC.config.encodedContextPath + "/cart/updateMultiD",
            data: { productCode: productCode, quantity: newCartQuantity, entryNumber: -1 },
            type: method,
            success: function (data, textStatus, xhr) {
                var itemIndex = $(this).data("index");
                ACC.cart.refreshCartData(data, -1, newCartQuantity, itemIndex);
                mapCodeQuantity[variantCode] = newCartQuantity;
            },
            error: function (xhr, textStatus, error) {
                var redirectUrl = xhr.getResponseHeader("redirectUrl");
                var connection = xhr.getResponseHeader("Connection");
                // check if error leads to a redirect
                if (redirectUrl !== null) {
                    window.location = redirectUrl;
                    // check if error is caused by a closed connection
                } else if (connection === "close") {
                    window.location.reload();
                }
            },
        });
    },

    bindToAddToCartForm: function (productCode, form) {
        form.ajaxForm({
            type: "POST",
            success: function (res, status, xhr, form) {
                ACC.productDetail.displayAddToCartPopup(res, productCode);
            },
            error: function (e) {
                console.log(e);
                $(".js-mini-cart-container .generic-error").show();
                $(".js-mini-cart-container").css("display", "flex").hide().fadeIn().delay(5000).fadeOut();
                var quantity = $(".js-initial-quantity-" + productCode).val();
                $(self)
                    .parents(".js-qty-selector-" + productCode)
                    .find(".js-qty-selector-input")
                    .val(quantity);
            },
        });
    },

    bindToReorder: function () {
        $(".reorder-btn").on("click", function () {
            if (ACC.shipment.isShipmentSet()) {
                var orderCode = $(this).data("reorder-code");
                var reorderUrl = ACC.config.encodedContextPath + "/cart/reorder/";
                $.ajax({
                    url: reorderUrl,
                    type: "POST",
                    data: { orderCode: orderCode },
                    //contentType: "application/json",
                    success: function (response) {
                        ACC.productDetail.displayReorderAddToCartPopup(response);
                        ACC.carousel.bindCarousel();
                    },
                    error: function (error) {
                        $(".reorder-cart-error-msg").removeClass("hidden");
                    },
                });
            } else {
                ACC.shipment.showSelectorInRawMode();
            }
        });
    },

    displayReorderAddToCartPopup: function (response) {
        if (response.shipmentTypeSet && response.shipmentTypeSet == "false") {
            ACC.shipment.showSelectorInRawMode();
            var quantity = $(".js-initial-quantity-" + productCode).val();
            $(".js-qty-selector-input").val(quantity);
        } else {
            if (typeof ACC.minicart.updateMiniCartDisplay == "function") {
                ACC.minicart.updateMiniCartDisplay(); //TODO
            }

            var html = response.addToCartLayer;
            $(".reorder-cart-content").html(html);

            var updatedCartLayer = response.cartLayer;
            $(".latest-cart").html(updatedCartLayer);
            $(".latest-cart .cart-layer-empty-cart-body img").attr(
                "src",
                $(".cart-image-url").data("emptycartimageurl")
            );
        }
    },

    displayAddToCartPopup: function (cartResult, productCode) {
        if (cartResult.shipmentTypeSet && cartResult.shipmentTypeSet == "false") {
            ACC.shipment.showSelectorInRawMode();
            var quantity = $(".js-initial-quantity-" + productCode).val();
            $(".js-qty-selector-input").val(quantity);
        } else {
            if (typeof ACC.minicart.updateMiniCartDisplay == "function") {
                ACC.minicart.updateMiniCartDisplay(); //TODO
            }
            $(".js-mini-cart-container .generic-error").hide();
            $("#addToCartLayer").remove();
            
            var html = cartResult.addToCartLayer;
            $(".mini-cart-content").html(html);

            var updatedCartLayer = cartResult.cartLayer;
            $(".latest-cart").html(updatedCartLayer);
            ACC.productDetail.updateAffectedProducts(updatedCartLayer);
            
            //Scroll issue fix
            if($('.shipment-selector .cart-item-' + productCode).length > 0){
            	$('.shipment-selector .mini-cart').animate({ scrollTop: $('.shipment-selector .cart-item-' + productCode).offset().top }, 10);
            }
            
            $(".latest-cart .cart-layer-empty-cart-body img").attr(
                "src",
                $(".cart-image-url").data("emptycartimageurl")
			);
			$('#message').fadeIn('slow', function(){
				$('#message').delay(5000).fadeOut(); 
			 });
            $(".js-mini-cart-container").css("display", "flex").delay(2000).fadeOut();
            //			var titleHeader = $('#addToCartTitle').html();
            //
            //			ACC.colorbox.open(titleHeader, {
            //				html : cartResult.addToCartLayer,
            //				width : "460px"
            //			});
            $(".js-initial-quantity-" + productCode).val(cartResult.updatedQuantity);
            $(".js-qty-selector-input-" + productCode).val(cartResult.updatedQuantity);

            var quantityField = cartResult.updatedQuantity;
            var quantity = 1;
            if (quantityField != undefined) {
                quantity = quantityField;
            }

            var cartAnalyticsData = cartResult.cartAnalyticsData;

            var cartData = {
                cartCode: cartAnalyticsData.cartCode,
                productCode: productCode,
                quantity: quantity,
                productPrice: cartAnalyticsData.productPostPrice,
                productName: cartAnalyticsData.productName,
            };
            ACC.track.trackAddToCart(productCode, quantity, cartData);
        }
    },

    updateAffectedProducts: function (updatedCartLayer) {
        var affectedProductsDiv = $(".affected-mini-cart");
        var tempDiv = $("<div>").addClass("temporary-cart").append($(updatedCartLayer));
        var updatedCartDiv = tempDiv.find(".mini-cart");
        if (updatedCartLayer.length > 0) {
            affectedProductsDiv.html(updatedCartDiv);
            $.each(affectedProductsDiv.find(".cart-layer-item"), function (i, val) {
                $(val).addClass("affected-item");
                var productCode = $(val).data("item-code");
                $(val)
                    .removeClass("cart-item-" + productCode)
                    .addClass("affected-item-" + productCode);
            });
        }
    },

    bindVariantOptions: function () {
        ACC.productDetail.bindCurrentStyle();
        ACC.productDetail.bindCurrentSize();
        ACC.productDetail.bindCurrentType();
    },

    bindCurrentStyle: function () {
        var currentStyle = $("#currentStyleValue").data("styleValue");
        var styleSpan = $(".styleName");
        if (currentStyle != null) {
            styleSpan.text(": " + currentStyle);
        }
    },

    bindCurrentSize: function () {
        var currentSize = $("#currentSizeValue").data("sizeValue");
        var sizeSpan = $(".sizeName");
        if (currentSize != null) {
            sizeSpan.text(": " + currentSize);
        }
    },

    bindCurrentType: function () {
        var currentSize = $("#currentTypeValue").data("typeValue");
        var sizeSpan = $(".typeName");
        if (currentSize != null) {
            sizeSpan.text(": " + currentSize);
        }
    },

    bindFacets: function () {
        $(document).on("click", ".filter-facet-button", function (e) {
            $(".facet-refine-hidden").slideToggle(500, function () {});
        });

        $(window).resize(function () {
            if (window.innerWidth > "991") {
                if ($(".facet-refine-hidden").css("display") == "block") {
                    $(".facet-refine-hidden").css({ display: "none" });
                }
            }
        });
    },
    
    bindToRecipeCart: function () {
    	$(document).on("click", ".recipe-btn", function (e) {
            if (ACC.shipment.isShipmentSet()) {
                var recipeCode = $(this).data("recipecode");
                var reorderUrl = ACC.config.encodedContextPath + "/recipe/addAllToCart";
                $.ajax({
                    url: reorderUrl,
                    type: "POST",
                    data: { recipeCode: recipeCode },                
                    success: function (response) {
                    	if(response.error) {
                    		$(".recipe-cart-failure-message").removeClass("shownone");			
                    	} 
                    	else {              		   		
                    		ACC.productDetail.updateQtyOfRecipeEntries(response.entries);
                            if (typeof ACC.minicart.updateMiniCartDisplay == "function") {
                                ACC.minicart.updateMiniCartDisplay(); 
                            } 
  
                            var listOfUnAffectedProducts = response.listOfUnAffectedProducts;
                            var element = listOfUnAffectedProducts.length == 0 ? ".recipe-cart-success-message" 
                            		      : ".recipe-cart-partial-success-message";                    
	                       
                            $(element).removeClass("shownone");		
	                        
                            var updatedCartLayer = response.cartLayer;
                            $(".latest-cart").html(updatedCartLayer);
                            $(".latest-cart .cart-layer-empty-cart-body img").attr(
                                "src",
                                $(".cart-image-url").data("emptycartimageurl")
                            );
                        }
                    },
                    error: function (error) {
                    	$(".recipe-cart-failure-message").removeClass("shownone");	
                    },
                });
            } else {
                ACC.shipment.showSelectorInRawMode();
            }
        });
    },
    
    updateQtyOfRecipeEntries: function (entries) {
        entries.forEach(function (e1, index) {
            $(".js-initial-quantity-" + e1.productCode).val(e1.quantity);
            $(".js-qty-selector-input-" + e1.productCode).val(e1.quantity);
        });
    },
};
