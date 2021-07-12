ACC.shipment = {
    _autoload: [
        "bindShipmentSelectionAndMiniCartHeaderLink",
        "bindClickEventsInShipmentBox",
        "bindValidateAndSetDeliveryEvents",
		"bindSearchPickupStoreEvents",
		"bindSubmitPickupStoreEvents",
        "shipmentMode"
    ],

    bindShipmentSelectionAndMiniCartHeaderLink: function () {
        //shipment selector button
        $(document).on("click", ".shipment-method", function (e) {
            if (ACC.shipment.isShipmentSet()) {
                ACC.shipment.showSelectorInEditMode();
            } else {
                ACC.shipment.showSelectorInRawMode();
            }
        });
        //mini cart button
        $(document).on("click", ".js-mini-cart-link", function (e) {
            if (ACC.shipment.isShipmentSet()) {
                ACC.shipment.showSelectorInCartMode();
            } else {
                ACC.shipment.showSelectorInRawMode();
            }
        });
    },

    isShipmentSet: function () {
        if ($("#isShipmentSet").data("isshipmentset")) {
            return true;
        }
        return false;
    },

    showSelectorInRawMode: function () {
        ACC.shipment.openShipmentColorbox();
    },

    showSelectorInEditMode: function () {
        ACC.shipment.editMode();
        ACC.shipment.openShipmentColorbox();
    },

    showSelectorInCartMode: function () {
        $(".cart-screen").show();
        $(".choose-shipment").hide();
        ACC.shipment.openShipmentColorbox();
    },

    editMode: function () {
        $(".cart-screen").hide();
        $(".choose-shipment").show();
    },

    openShipmentColorbox: function () {
        var colorboxTarget = "#shipment-method-component";
        $("#cboxLoadedContent").empty().append($(colorboxTarget).html()),
            ACC.colorbox.open("Shipment Selection", {
                top: 0,
                right: 0,
                left: "auto",
                height: "100vh",
                initialHeight: "100%",
                fixed: !0,
                inline: !0,
                href: $(colorboxTarget).html(),
                maxWidth: "100%",
                scrolling: !1,
                opacity: 0.6,
                width: "auto",
                transition: "none",
                close: '<i class="fa fa-remove"></i>',
                className: "shipment-selector static-box",
                onComplete: function () {
                    $(this).colorbox.resize();
                    $(".js-full-pageheight").css("height", $(window)[0].innerHeight);
                    $(document).on("click", ".cboxClose", function (e) {
                        ACC.colorbox.close();
                    });
                },
                onOpen: function () {
                    $("#cboxContent").hide().show(
                        "slide",
                        {
                            direction: "right",
                        },
                        500
                    );
                },
                onCleanup: function () {
                    ACC.shipment.reverseAffectedProducts();
                    $(".no-clip #cboxContent").hide(
                        "slide",
                        {
                            direction: "right",
                        },
                        500,
                        function () {
                            $(this).show();
                        }
                    );
                },
            });
    },

    bindClickEventsInShipmentBox: function () {
        //Toggle between Delivery and C&C forms in shipment box on option click
        //Can be reused for Vertical Shipment selector
        $(document).on("click", ".choose-option", function (e) {
            $(".choose-option").removeClass("selected");
            $(this).addClass("selected");
            if ($(this).hasClass("choose-delivery")) {
                $(".pickup-selection").hide();
                $(".delivery-selection").css("display", "flex");
            } else {
                $(".delivery-selection").hide();
                $(".pickup-selection").css("display", "flex");
            }
        });
        //In Cart Mode, Clicking on change shipment should open the selection forms
        $(document).on("click", ".change-shipment", function (e) {
            ACC.shipment.editMode();
        });
    },

    bindValidateAndSetDeliveryEvents: function () {
        //Delivery Button Click on Horizontal Selector
        $(document).on("click", ".submit-delivery", function (e) {
            if ($(this).hasClass("disabled")) {
                return;
            }
            var inputBox = $(e.target).siblings(".input-postalcode");
            validateAndSetPostalCodeInSession(inputBox, e.target, true, true, true, true);
        });

        //Focus Out on Input Box in Horizontal Selector
        $(document).on("focusout", $(".input-postalcode"), function (e) {
            var submitButton = $(e.target).siblings(".submit-delivery");
            validateAndSetPostalCodeInSession(e.target, submitButton, false, true, true);
        });

        //Key Up on Input Box in Horizontal Selector
        $(document).on("keyup", $(".input-postalcode"), function (e) {
            var submitButton = $(e.target).siblings(".submit-delivery");
            validateAndSetPostalCodeInSession(e.target, submitButton, false, false, true);
		});
		
		// $(document).on("click", $(".proceed-with-shipment-change"), function(e) {
		// 	var submitButton = $(e.target).closest(".shipment-selector-box").find(".submit-delivery");
		// 	validateAndSetPostalCodeInSession(e.target, submitButton, true, false, true);
		// });
        
        //Focus Out on Input Box in Vertical Selector
        $(document).on("focusout", $(".input-postal-code"), function (e) {
            var submitButton = $(e.target).parents(".preferred-shipment-section").siblings('.preferres-shipment-siblings').find(".preferred-shipment-btn" );
            validateAndSetPostalCodeInSession(e.target, submitButton, false, true, false);
        });

        //Key Up on Input Box in Vertical Selector
        $(document).on("keyup", $(".input-postal-code"), function (e) {
            var submitButton = $(e.target).parents(".preferred-shipment-section").siblings('.preferres-shipment-siblings').find(".preferred-shipment-btn" );
            validateAndSetPostalCodeInSession(e.target, submitButton, false, false, false);
        });

        //Mecca of Delivery Submission
        async function validateAndSetPostalCodeInSession(
            inputBox,
            submitButton,
            shouldSetPostalCode = false,
            showSuccessOrErrorMessages = true,
            isHorizontalSelector = false,
            shouldFindAffectedProducts = false
        ) {
            var enteredPostalCode = $(inputBox).val();
            if (enteredPostalCode.trim().length > 0) {
                var validateurl = $(inputBox).data("validateurl");
                var successMessageDiv, errorMessageDiv;
                if (isHorizontalSelector) {
                    successMessageDiv = $(submitButton).siblings(".success-message-submit-delivery");
                    errorMessageDiv = $(submitButton).siblings(".error-message-submit-delivery");
                } else {
                	successMessageDiv = $(inputBox).siblings(".success-message-submit-delivery");
                    errorMessageDiv = $(inputBox).siblings(".error-message-submit-delivery");
	            }
                if (validateurl) {
                    try {
                        var validationResult = await ACC.shipment.validatePostalCode(validateurl, enteredPostalCode);
                        if (validationResult.result) {
                            ACC.shipment.enableSubmitDeliveryButton(submitButton);
                            if (shouldSetPostalCode) {
                                if (ACC.shipment.isCartNonEmpty() && shouldFindAffectedProducts) {
                                    var cartUpdateURL = $(inputBox).data("cartupdateurl");
                                    var affectedProducts = await ACC.shipment.getAffectedProducts(
                                        cartUpdateURL,
                                        enteredPostalCode,
                                        "DELIVERY"
                                    );
                                    if (affectedProducts && affectedProducts.length > 0) {
                                        console.log(affectedProducts);
                                        ACC.shipment.showAffectedProducts(affectedProducts);
                                        return 1;
                                    }
                                }
                                var submissionurl = $(inputBox).data("submissionurl");
                                var submissionResult = await ACC.shipment.setPostalCode(
                                    submissionurl,
                                    enteredPostalCode
                                );
                                if (submissionResult.result) {
                                    ACC.shipment.setShipmentCookie("delivery", enteredPostalCode);
                                    ACC.shipment.showSuccessMessagesDelivery(
                                        showSuccessOrErrorMessages,
                                        successMessageDiv,
                                        errorMessageDiv
                                    );
                                    location.reload();
                                } else {
                                    ACC.shipment.showErrorMessagesDelivery(
                                        showSuccessOrErrorMessages,
                                        successMessageDiv,
                                        errorMessageDiv
                                    );
                                    ACC.shipment.disableSubmitDeliveryButton(submitButton);
                                }
                            }
                        } else {
                            ACC.shipment.showErrorMessagesDelivery(
                                showSuccessOrErrorMessages,
                                successMessageDiv,
                                errorMessageDiv
                            );
                            ACC.shipment.disableSubmitDeliveryButton(submitButton);
                        }
                    } catch (error) {
                        console.log(error);
                        ACC.shipment.showErrorMessagesDelivery(
                            showSuccessOrErrorMessages,
                            successMessageDiv,
                            errorMessageDiv
                        );
                        ACC.shipment.disableSubmitDeliveryButton(submitButton);
                    }
                }
            }
        }
    },

    validatePostalCode: function (url, pcode) {
        return new Promise((resolve, reject) => {
            $.ajax({
                url: url,
                data: { postalCode: pcode },
                type: "POST",
                dataType: "json",
                success: resolve,
                error: reject,
            });
        });
    },

    setPostalCode: function (url, pcode) {
        return new Promise((resolve, reject) => {
            $.ajax({
                url: url,
                data: { postalCode: pcode },
                type: "POST",
                dataType: "json",
                success: resolve,
                error: reject,
            });
        });
    },

    setShipmentCookie: function (shipmentMode, locationText) {
        $.cookie("shipment-mode", null);
        $.cookie("shipment-mode", shipmentMode);
        $.cookie("postal-code", null);
        $.cookie("postal-code", locationText);
    },

    showSuccessMessagesDelivery(showSuccessOrErrorMessages, successMessageDiv, errorMessageDiv) {
        if (showSuccessOrErrorMessages) {
            $(successMessageDiv).hide();
            $(errorMessageDiv).show();
        }
    },

    showErrorMessagesDelivery(showSuccessOrErrorMessages, successMessageDiv, errorMessageDiv) {
        if (showSuccessOrErrorMessages) {
            $(successMessageDiv).hide();
            $(errorMessageDiv).show().delay(2000).fadeOut();
        }
	},
	
	
    enableSubmitDeliveryButton(submitButton) {
		$(submitButton).removeClass("disabled");
    },
	
    disableSubmitDeliveryButton(submitButton) {
        $(submitButton).addClass("disabled");
    },

    bindSearchPickupStoreEvents: function () {
        $(document).on("keyup", ".input-pickup", function (e) {
            var inputBox = $(e.target);
            var enteredStoreID = $(inputBox).val();
            if (enteredStoreID.trim().length > 0) {
                if (e.keyCode === 13 || enteredStoreID.trim().length > 3) {
                    searchAndPopulateAvailableStores(inputBox, false);
                }
            }
        });
        $(document).on("click", ".search-for-pos", function (e) {
            var inputBox = $(e.target).siblings(".input-pickup");
            searchAndPopulateAvailableStores(inputBox, false);
        });

        async function searchAndPopulateAvailableStores(inputBox, isHorizontalSelector = false) {
            var enteredStoreID = $(inputBox).val();
            if (enteredStoreID.trim().length > 0) {
                var findstoresurl = $(inputBox).data("findstoresurl");
                var successMessageDiv, errorMessageDiv;
                if (isHorizontalSelector) {
                    errorMessageDiv = $(inputBox).siblings(".error-message-submit-pickup");
                } else {
                }
                if (findstoresurl) {
                    try {
						var foundStores = await ACC.shipment.findStoresForText(enteredStoreID, findstoresurl);
						if (foundStores) {
							console.log(foundStores);
							ACC.pickupinstore.refreshPickupInStoreColumn(foundStores);
						} else {
							ACC.shipment.showErrorMessagesPickup(errorMessageDiv);
						}
                    } catch (error) {
                        console.log(error);
                        ACC.shipment.showErrorMessagesPickup(errorMessageDiv);
                    }
                }
            }
        };
	},

	findStoresForText: function(locationText, url) {
		return new Promise((resolve, reject) => {
			$.ajax({
				url: url,
				data: {locationQuery: locationText},
				type: "POST",
				dataType: "json",
				success: resolve,
				error: reject,
			});
		});
	},

	bindSubmitPickupStoreEvents: function() {
		
		$(document).on("click", function (event) {
            if (!$(event.target).closest(".store-navigation").length) {
                $("#colorbox .store-navigation").hide();
            }
		});
		
		$(document).on("click", ".store-navigation .pickup-store-list-entry", function (e) {
			var storeID = $(this).find(".pickup-store-list-entry-name").text();
			validateAndSetPickupStoreInSession(inputBox);
            var url = $(this).parent().parent().data("url");
            ACC.shipment.submitStoreID(url, storeID);
		});
		
		//Medina of Click and Collect Submission
		async function validateAndSetPickupStoreInSession(inputBox, isHorizontalSelector) {
			
		}
        // async function validateAndSetPickupInSession(
        //     inputBox,
        //     submitButton,
        //     shouldSetPickup = false,
        //     showSuccessOrErrorMessages = true,
        //     isHorizontalSelector = false,
        //     shouldFindAffectedProducts = false
        // ) {
        //     var enteredStore = $(inputBox).val();
        //     if (enteredStore.trim().length > 0) {
        //         var validateurl = $(inputBox).data("validateurl");
        //         var successMessageDiv, errorMessageDiv;
        //         if (isHorizontalSelector) {
        //             successMessageDiv = $(submitButton).siblings(".success-message-submit-delivery");
        //             errorMessageDiv = $(submitButton).siblings(".error-message-submit-delivery");
        //         } else {
        //         }
        //         if (validateurl) {
        //             try {
        //                 var validationResult = await ACC.shipment.validatePostalCode(validateurl, enteredStore);
        //                 if (validationResult.result) {
        //                     ACC.shipment.enableSubmitDeliveryButton(submitButton);
        //                     if (shouldSetPostalCode) {
        //                         if (ACC.shipment.isCartNonEmpty() && shouldFindAffectedProducts) {
        //                             var cartUpdateURL = $(inputBox).data("cartupdateurl");
        //                             var affectedProducts = await ACC.shipment.getAffectedProducts(
        //                                 cartUpdateURL,
        //                                 enteredPostalCode,
        //                                 "DELIVERY"
        //                             );
        //                             if (affectedProducts && affectedProducts.length > 0) {
        //                                 console.log(affectedProducts);
        //                                 ACC.shipment.showAffectedProducts(affectedProducts);
        //                                 return 1;
        //                             }
        //                         }
        //                         var submissionurl = $(inputBox).data("submissionurl");
        //                         var submissionResult = await ACC.shipment.setPostalCode(
        //                             submissionurl,
        //                             enteredPostalCode
        //                         );
        //                         if (submissionResult.result) {
        //                             ACC.shipment.setShipmentCookie("delivery", enteredPostalCode);
        //                             ACC.shipment.showSuccessMessagesDelivery(
        //                                 showSuccessOrErrorMessages,
        //                                 successMessageDiv,
        //                                 errorMessageDiv
        //                             );
        //                             location.reload();
        //                         } else {
        //                             ACC.shipment.showErrorMessagesDelivery(
        //                                 showSuccessOrErrorMessages,
        //                                 successMessageDiv,
        //                                 errorMessageDiv
        //                             );
        //                             ACC.shipment.disableSubmitDeliveryButton(submitButton);
        //                         }
        //                     }
        //                 } else {
        //                     ACC.shipment.showErrorMessagesDelivery(
        //                         showSuccessOrErrorMessages,
        //                         successMessageDiv,
        //                         errorMessageDiv
        //                     );
        //                     ACC.shipment.disableSubmitDeliveryButton(submitButton);
        //                 }
        //             } catch (error) {
        //                 console.log(error);
        //                 ACC.shipment.showErrorMessagesDelivery(
        //                     showSuccessOrErrorMessages,
        //                     successMessageDiv,
        //                     errorMessageDiv
        //                 );
        //                 ACC.shipment.disableSubmitDeliveryButton(submitButton);
        //             }
        //         }
        //     }
        // }
	},


	submitStoreID: function (url, sid) {
		return new Promise((resolve, reject) => {
            $.ajax({
                url: url,
                data: {storeID: sid},
                type: "POST",
                dataType: "json",
                success: resolve,
                error: reject,
            });
        });
    },

    showErrorMessagesPickup: function(errorMessageDiv) {
        $(errorMessageDiv).show().delay(2000).fadeOut();
    },

    isCartNonEmpty: function() {
        if ($(".cart-length") && $(".cart-length").data("cartlength") > 0) {
            return true;
        }
        return false;
    },

    getAffectedProducts: function (url, pcode, shipmentType) {
        return new Promise((resolve, reject) => {
            $.ajax({
                url: url,
                data: { locationText: pcode, shipmentType: shipmentType },
                type: "GET",
                dataType: "json",
                success: resolve,
                error: reject,
            });
        });
    },

    showAffectedProducts: function (affectedProducts) {
        affectedProducts.forEach((element) => {
            $(".affected-cart")
                .find(".affected-item-" + element.code)
                .css("display", "flex");
        });
        $(".shipment-text").hide();
        $(".shipment-text-postcode").hide();
        $(".cart-heading").hide();
        $(".change-shipment").hide();
        $(".affected-products-heading").show();
        $(".latest-cart").hide();
        $(".choose-shipment").hide();
        $(".affected-cart").show();
        $(".cart-screen").show();
        $(".affected-cart .cancel-shipment-change").click(function (e) {
            ACC.colorbox.close();
        });
    },

    reverseAffectedProducts: function () {
        $(".affected-item").hide();
        $(".shipment-text").show();
        $(".shipment-text-postcode").show();
        $(".cart-heading").show();
        $(".change-shipment").show();
        $(".affected-products-heading").hide();
        $(".latest-cart").show();
        $(".choose-shipment").show();
        $(".affected-cart").hide();
        $(".cart-screen").hide();

		//hide change link
		//bind events
		//on colorbox close, show affected items, hide affected cart, show latest items, show change link
	},

    shipmentMode: function () {
        $(document).ready(function () {
            $(".preferredShipment").click(function () {
                var inputValue = $(this).attr("value");
                var targetBox = $("." + inputValue);
                $(".delivery-option").not(targetBox).hide();
                $(targetBox).show();
            });
        });
    },
        
    showShipmentTypeInputBox: function(selectedRadio){
        var targetBox = $("." + selectedRadio);
        $(".delivery-option").not(targetBox).hide();
        $(targetBox).show();
    }
};
