ACC.shipment = {
    _autoload: [
        "bindShipmentSelectionAndMiniCartHeaderLink",
        "bindClickEventsInShipmentBox",
        "bindValidateAndSetDeliveryEvents",
        "bindSearchPickupStoreEvents",
        "bindSubmitPickupStoreEvents",
        "shipmentMode",
        "readShipmentCookieAndPrompt",
        "bindShipmentCookieClickEvents",
        "readLocationCookie",
        "readShipmentCookie",
        "readStoreNameCookie",
        "bindClickEventsOfCheckoutPageInShipmentBox"
    ],

    bindShipmentSelectionAndMiniCartHeaderLink: function () {
        // shipment selector button
        $(document).on("click", ".shipment-method", function (e) {
            if (ACC.shipment.isShipmentSet()) {
                ACC.shipment.showSelectorInEditMode();
            } else {
                ACC.shipment.showSelectorInRawMode();
            }
        });
        // mini cart button
        $(document).on("click", ".js-mini-cart-link", function (e) {
            if (ACC.shipment.isShipmentSet()) {
                ACC.shipment.showSelectorInCartMode();
            } else {
                ACC.shipment.showSelectorInRawMode();
            }
        });
        
        if($(".open-cart-empty").length > 0 && ACC.shipment.isShipmentSet()){
        	ACC.shipment.showSelectorInCartMode();
        }
    },

    isShipmentSet: function () {
        if ($("#isShipmentSet").data("isshipmentset")) {
            return true;
        }
        return false;
    },

    readLocationCookie() {
        if ($.cookie("postal-code")) {
            return $.cookie("postal-code");
        }
    },

    readStoreNameCookie() {
        if ($.cookie("store-name")) {
            return $.cookie("store-name");
        }
    },

    readShipmentCookie() {
        if ($.cookie("shipment-mode")) {
            return $.cookie("shipment-mode");
        }
    },

    showSelectorInRawMode: function () {
        ACC.shipment.rawMode();
        ACC.shipment.openShipmentColorbox();
    },

    showSelectorInEditMode: function () {
        ACC.shipment.editMode();
        ACC.shipment.openShipmentColorbox();
    },

    showSelectorInCartMode: function () {
        ACC.shipment.cartMode();
        ACC.shipment.openShipmentColorbox();
    },

    rawMode: function () {
        $(".pickup-selection").hide();
        $(".delivery-selection").hide();
    },

    editMode: function () {
        $(".cart-screen").hide();
        $(".choose-shipment").show();
    },

    cartMode: function () {
        $(".cart-screen").show();
        $(".choose-shipment").hide();
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
                // removing class fa-remove from below <i> tag to remove extra closing button
                // appearing in shipment selector
                // close: '<i class="fa fa-remove"></i>',
                close: '<i class="fa"></i>',
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
                    $(".save-cart-form").hide();
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
                onClosed: function () {
                    ACC.shipment.reverseAffectedProducts();
                },
            });

           
    },

    bindClickEventsInShipmentBox: function () {
        // Toggle between Delivery and C&C forms in shipment box on option click
        // Can be reused for Vertical Shipment selector
        $(document).on("click", ".choose-option", function (e) {
            $(this).siblings(".choose-option").removeClass("selected");
            $(this).addClass("selected");
            if ($(this).hasClass("choose-delivery")) {
                $(this).closest(".choose-shipment").find(".pickup-selection").hide();
                $(this).closest(".choose-shipment").find(".delivery-selection").css("display", "flex");
            } else {
                $(this).closest(".choose-shipment").find(".delivery-selection").hide();
                $(this).closest(".choose-shipment").find(".pickup-selection").css("display", "flex");
            }
        });
        // In Cart Mode, Clicking on change shipment should open the selection
        // forms
        $(document).on("click", ".change-shipment", function (e) {
            ACC.shipment.editMode();
        });
    },

    bindValidateAndSetDeliveryEvents: function () {
        // Delivery Button Click on Horizontal Selector
        $(document).on("click", ".submit-delivery", function (e) {
            if ($(this).hasClass("disabled")) {
                return;
            }
            var inputBox = $(e.target).siblings(".input-postalcode");
            validateAndSetPostalCodeInSession(inputBox, e.target, true, true, true, true);
        });

        // Focus Out on Input Box in Horizontal Selector
        $(document).on("focusout", ".input-postalcode", function (e) {
            var submitButton = $(e.target).siblings(".submit-delivery");
            validateAndSetPostalCodeInSession(e.target, submitButton, false, true, true);
        });

        // Key Up on Input Box in Horizontal Selector
        $(document).on("keyup", ".input-postalcode", function (e) {
            var submitButton = $(e.target).siblings(".submit-delivery");
            validateAndSetPostalCodeInSession(e.target, submitButton, false, false, true);
        });

        //Affected Products Consent from Customer
        $(document).on("click", ".proceed-with-shipment-change.delivery", function (e) {
            var inputBox = $(".submit-delivery").siblings(".input-postalcode");
            $(inputBox).val($(e.target).data("location"));
            validateAndSetPostalCodeInSession(inputBox, e.target, true, true, true, false);
        });

        // Focus Out on Input Box in Vertical Selector
        $(document).on("focusout", ".input-postal-code-customer", function (e) {
            var submitButton = $(e.target)
                .parents(".preferred-shipment-section")
                .siblings(".preferres-shipment-siblings")
                .find(".preferred-shipment-btn");
            validateAndSetPostalCodeInSession(e.target, submitButton, false, true, false);
        });

        // Key Up on Input Box in Vertical Selector
        $(document).on("keyup", ".input-postal-code-customer", function (e) {
            var submitButton = $(e.target)
                .parents(".preferred-shipment-section")
                .siblings(".preferres-shipment-siblings")
                .find(".preferred-shipment-btn");
            validateAndSetPostalCodeInSession(e.target, submitButton, false, true, false);
        });

        $(document).on("focusout", ".input-postal-code-checkout", function (e) {
            var submitButton = $(e.target).siblings(".submit-delivery");
            validateAndSetPostalCodeInSession(e.target, submitButton, false, true, false, true);
        });

        // Key Up on Input Box in Vertical Selector
        $(document).on("keyup", ".input-postal-code-checkout", function (e) {
            var submitButton = $(e.target).siblings(".submit-delivery");
            validateAndSetPostalCodeInSession(e.target, submitButton, false, true, false, true);
        });

        //Set Session from Cookie after consent from Customer
        $(document).on("click", "#setDeliveryCookieYes", function (e) {
            var shipmentType = localStorage.getItem("shipmentType").toLowerCase();
            $("." + shipmentType + "CookieNotification").addClass("shownone");
            var inputBox = $("#" + shipmentType + "Cookie");        
            $(inputBox).val(localStorage.getItem("customerLocation"));
            validateAndSetPostalCodeInSession(inputBox, e.target, true, false, false, false);
        });

        // Mecca of Delivery Submission
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
                                        ACC.shipment.showAffectedProducts(
                                            affectedProducts,
                                            "delivery",
                                            enteredPostalCode
                                        );
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
                                    //track pickup GTM
                                    //ACC.track.trackShipmentSelection(locationText,"delivery");
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

    setShipmentCookie: function (shipmentMode, locationText, storeName) {
        ACC.shipment.removeShipmentCookie();
        $.cookie("shipment-mode", shipmentMode, {path: '/', expires: 15});
        $.cookie("postal-code", locationText, {path: '/', expires: 15});
        $.cookie("store-name", storeName, {path: '/', expires: 15});
    },

    removeShipmentCookie: function () {
    	  $.cookie("shipment-mode", null, {path: '/'});
          $.cookie("postal-code", null, {path: '/'});
          $.cookie("store-name", null, {path: '/'});
    },

    showSuccessMessagesDelivery(showSuccessOrErrorMessages, successMessageDiv, errorMessageDiv) {
        if (showSuccessOrErrorMessages) {
            $(successMessageDiv).show();
            $(errorMessageDiv).hide();
        }
    },

    showErrorMessagesDelivery(showSuccessOrErrorMessages, successMessageDiv, errorMessageDiv) {
        if (showSuccessOrErrorMessages) {
            $(successMessageDiv).hide();
            $(errorMessageDiv).show().delay(2000).fadeOut();
        }
    },

    enableSubmitDeliveryButton(submitButton) {
        $(submitButton).removeClass("disable-action");
    },

    disableSubmitDeliveryButton(submitButton) {
        $(submitButton).addClass("disable-action");
    },

    bindSearchPickupStoreEvents: function () {
        //Autocomplete on pickup input
        $(document).on("keyup", ".input-pickup", function (e) {
            var inputBox = $(e.target);
            var enteredStoreID = $(inputBox).val();
            if (enteredStoreID.trim().length > 0) {
//                if (e.keyCode === 13 || enteredStoreID.trim().length > 3 || $(inputBox).data("activated")) {
            	if (e.keyCode === 13 || enteredStoreID.trim().length > 1 || $(inputBox).data("activated")) {
                    $(inputBox).data("activated", true);
                    searchAndPopulateAvailableStores(inputBox, false);
                }
            }
        });
        //Focusout set input as active for further autocomplete triggers
        $(document).on("focusout", ".input-pickup", function (e) {
            var inputBox = $(e.target);
            $(inputBox).data("activated", false);
        });
        //Button to search for POS results
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
                            var storeJSONData = $.parseJSON(foundStores);
                            if (storeJSONData && storeJSONData.results) {
                                ACC.shipment.refreshPickupStoresData(storeJSONData, inputBox);
                            }
                        } else {
                            ACC.shipment.showErrorMessagesPickup(errorMessageDiv);
                        }
                    } catch (error) {
                        console.log(error);
                        ACC.shipment.showErrorMessagesPickup(errorMessageDiv);
                    }
                }
            }
        }
    },

    findStoresForText: function (locationText, url) {
        return new Promise((resolve, reject) => {
            $.ajax({
                url: url,
                data: { locationQuery: locationText },
                type: "POST",
                dataType: "json",
                success: resolve,
                error: reject,
            });
        });
    },

    refreshPickupStoresData: function (data, inputBox) {
        var $storeList = $(".js-pickup-store-list");
        $storeList.empty();

        $(".js-pickup-component").data("data", data.results);

        if (data["results"].length > 0) {
            for (i = 0; i < data["results"].length; i++) {
                $storeList.append(ACC.shipment.createListItemHtml(data["results"][i], i));
            }
        } else {
            var msg = $(".noStoreFound").data("message");
            $storeList.append($("<li>").addClass("store-availability no-store-found").text(msg));
        }

        $(inputBox).parent().find(".store-navigation").show();
        //$(".store-navigation").show();
        ACC.shipment.bindHideStoreList();
    },

    createListItemHtml: function (data, id) {
        var $spanElStInfo = ACC.shipment.getStoreInfoSpan(data);
        var $isExtVar;
        if (data.external) {
            $isExtVar = $("<span>").addClass("isExternal-div fa fa-asterisk").attr("title", $(".curbExternal").data("message")).append($("<span>").addClass("animation"));
        } else {
            $isExtVar = $("<span>").addClass("self-pos fa fa-map-marker").attr("title", $(".self-pos").data("message")).append($("<span>").addClass("animation"));
        }

        var $spanElStAvail = $("<span>")
            .addClass("store-availability")
            .append($("<span>").addClass("available").append(document.createTextNode(data.formattedDistance)));

        return $("<li>")
            .data("storeid", data.name)
            .data("storename", data.displayName)
            .addClass("pickup-store-list-entry")
            .addClass(data.external ? "curb-side" : "")
            .append($isExtVar)
            .append($spanElStInfo)
            .append($spanElStAvail);
    },

    getStoreInfoSpan: function (data) {
        var text = data.displayName;
        if (data.external) {
        text = text + " (" + $(".curbExternal").data("message") + ")";
        }
        var $spanElStInfo = $("<span>")
            .addClass("pickup-store-info")
            .append(
                $("<span>")
                    .addClass("pickup-store-list-entry-name")
                    .text(text)
            );
        $spanElStInfo = ACC.shipment.getStoreAddressSpan(data, $spanElStInfo);
        return $spanElStInfo;
    },

    getStoreAddressSpan: function (data, span) {
        var $spanElStInfo = span;
        if (data.address && data.address.line1 && data.address.line2 && data.address.town) {
            $spanElStInfo
                .append(
                    $("<span>")
                        .addClass("pickup-store-list-entry-address")
                        .text(data.address.line1 + " " + data.address.line2)
                )
                .append($("<span>").addClass("pickup-store-list-entry-city").text(data.address.town));
        }
        return $spanElStInfo;
    },

    bindHideStoreList: function () {
        $(document).on("click", function (event) {
            if (!$(event.target).closest(".store-navigation").length) {
                $(".store-navigation").hide();
            }
        });
    },

    bindSubmitPickupStoreEvents: function () {
        //Click on POS Results
        $(document).on("click", ".searchPOSForm .store-navigation .pickup-store-list-entry", function (e) {
            var storeID = $(this).data("storeid");
            var storeName = $(this).data("storename");
            var inputBox = $(this).closest(".store-navigation").siblings(".input-pickup");
            validateAndSetPickupStoreInSession(inputBox, storeID, true, true, true, storeName);
        });

        $(document).on("click", ".searchPOSForm-customer .store-navigation .pickup-store-list-entry", function (e) {
            var storeID = $(this).data("storeid");
            var storeName = $(this).data("storename");
            var inputBox = $(this).closest(".store-navigation").siblings(".input-pickup");
            validateAndSetPickupStoreInSession(inputBox, storeID, false, false, false, storeName);
        });

        $(document).on("click", ".searchPOSForm-checkout .store-navigation .pickup-store-list-entry", function (e) {
            var storeID = $(this).data("storeid");
            var storeName = $(this).data("storename");
            var inputBox = $(this).closest(".store-navigation").siblings(".input-pickup");
            validateAndSetPickupStoreInSession(inputBox, storeID, true, true, true, storeName);
        });
        //Click on Affected Products Proceed after Customer Consent
        $(document).on("click", ".proceed-with-shipment-change.pickup", function () {
            var inputBox = $(".input-pickup");
            var storeID = $(this).data("location");
            validateAndSetPickupStoreInSession(inputBox, storeID, true, true, false);
        });

      //Set Pickup from Cookie after customer consent
        $(document).on("click", "#setPickupCookieYes", function (e) {
            var shipmentType = localStorage.getItem("shipmentType").toLowerCase();
            $("." + shipmentType + "CookieNotification").addClass("shownone");
            var inputBox = $("#" + shipmentType + "Cookie");
            var storeName = localStorage.getItem("customerLocation");
            
            validateAndSetPickupStoreInSession(
                inputBox,
                ACC.shipment.readLocationCookie(),
                true,
                false,
                false,
                storeName
            );
        });

        // Add your store pickup event here. Separate because you dont have to
        // set in session

        // Medina of Click and Collect Submission
        async function validateAndSetPickupStoreInSession(
            inputBox,
            locationText,
            setInSession = false,
            isHorizontalSelector = false,
            shouldFindAffectedProducts = false,
            storeName = ""
        ) {
            var errorMessageDiv;
            if (isHorizontalSelector) {
                errorMessageDiv = $(inputBox).siblings(".error-message-submit-pickup");
            }
            var validateurl = $(inputBox).data("validateurl");
            if (validateurl) {
                try {
                    var validationResult = await ACC.shipment.validateStoreID(validateurl, locationText);
                    if (validationResult.result) {
                        if (setInSession) {
                            if (ACC.shipment.isCartNonEmpty() && shouldFindAffectedProducts) {
                                var cartUpdateURL = $(inputBox).data("cartupdateurl");
                                var affectedProducts = await ACC.shipment.getAffectedProducts(
                                    cartUpdateURL,
                                    locationText,
                                    "PICKUP"
                                );
                                if (affectedProducts && affectedProducts.length > 0) {
                                    console.log(affectedProducts);
                                    if (isHorizontalSelector) {
                                        ACC.shipment.showSelectorInCartMode();
                                    }
                                    ACC.shipment.showAffectedProducts(affectedProducts, "pickup", locationText);
                                    return 1;
                                }
                            }
                            var submissionurl = $(inputBox).data("submissionurl");
                            var submissionResult = await ACC.shipment.setPickup(submissionurl, locationText);
                            if (submissionResult.result) {
                                ACC.shipment.setShipmentCookie("pickup", locationText, storeName);
                                //this code is used for gtm its track user shipment activity
                                //ACC.track.trackShipmentSelection(locationText,"pickup");
                                location.reload();
                            } else {
                                ACC.shipment.showErrorMessagesPickup(errorMessageDiv);
                            }
                        } else if (!isHorizontalSelector) {
                            // Call your success method here - i.e. set the
                            // value in input box
                            $("#pickup-form-input").val(locationText);
                            $(".input-pickup").val(storeName);
                            $(".store-navigation").hide();
                            ACC.shipment.enableSubmitDeliveryButton(".preferred-shipment-btn");
                        }
                    } else {
                        ACC.shipment.showErrorMessagesPickup(errorMessageDiv);
                    }
                } catch (error) {
                    console.log(error);
                    ACC.shipment.showErrorMessagesPickup(errorMessageDiv);
                }
            } else {
                ACC.shipment.showErrorMessagesPickup(errorMessageDiv);
            }
        }
    },

    validateStoreID: function (url, storeID) {
        return new Promise((resolve, reject) => {
            $.ajax({
                url: url,
                data: { storeID: storeID },
                type: "POST",
                dataType: "json",
                success: resolve,
                error: reject,
            });
        });
    },

    setPickup: function (url, sid) {
        return new Promise((resolve, reject) => {
            $.ajax({
                url: url,
                data: { storeID: sid },
                type: "POST",
                dataType: "json",
                success: resolve,
                error: reject,
            });
        });
    },

    showErrorMessagesPickup: function (errorMessageDiv) {
        $("#pickup-form-input").val("");
        $(errorMessageDiv).show().delay(2000).fadeOut();
    },

    isCartNonEmpty: function () {
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

    showAffectedProducts: function (affectedProducts, shipmentType, locationText) {
        affectedProducts.forEach((element) => {
            $(".affected-cart")
                .find(".affected-item-" + element.code)
                .css("display", "flex");
        });
        $(".proceed-with-shipment-change").addClass(shipmentType);
        $(".proceed-with-shipment-change").data("location", locationText);
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
        $(".proceed-with-shipment-change").removeClass("delivery");
        $(".proceed-with-shipment-change").removeClass("pickup");
        $(".proceed-with-shipment-change").data("location", "");
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
    },

    shipmentMode: function () {
        $(document).ready(function () {
            // register Page
            if ($(".register-shipment-selector").length > 0) {
                var isShipmentTypeExist = $(".radiobutton").data("shipment-type") ? true : false;
                var shipmentData = ACC.shipment.bindActionToButtonAndText(
                    isShipmentTypeExist,
                    ".preferred-shipment-btn"
                );
            }

            // profile page
            if ($(".profile-shipment-selector").length > 0) {
                var shipmentData = ACC.shipment.bindActionToButtonAndText(true, ".preferred-shipment-btn");
            }
        });
    },

    bindActionToButtonAndText: function (isShipmentTypeExist, submitButton) {
        if (isShipmentTypeExist) {
            // show input box for selected shipment type
            var selectedRadio = $(".preferredShipment:checked").val();
            ACC.shipment.showShipmentTypeInputBox(selectedRadio);

            ACC.shipment.enableSubmitDeliveryButton(submitButton);
        } else {
            ACC.shipment.enableSubmitDeliveryButton(submitButton);
        }

        ACC.shipment.bindActionsToShipmentForm(submitButton);
    },

    bindActionsToShipmentForm: function (submitButton) {
        $(".preferredShipment").click(function () {
            var selectedRadio = $(this).attr("value");
            ACC.shipment.showShipmentTypeInputBox(selectedRadio);

            // if DECIDELATER is selected
            if (selectedRadio == "DECIDELATER") {
                ACC.shipment.enableSubmitDeliveryButton(submitButton);
            }

            if (selectedRadio == "PICKUP") {
                var storeName = $("." + selectedRadio).find(".input-pickup");

                if (storeName.val()) {
                    var value = storeName.val();
                    storeName.attr("placeholder", value);
                    var storeNameInput = storeName.val("");
                }
                ACC.shipment.disableSubmitDeliveryButton(submitButton);
            }

            if (selectedRadio == "DELIVERY") {
                var postalCode = $("." + selectedRadio).children(".customerDeliverForm");

                if (postalCode.val()) {
                    var value = postalCode.val();
                    postalCode.attr("placeholder", value);
                    var postalcodeInput = postalCode.val("");
                }
                ACC.shipment.disableSubmitDeliveryButton(submitButton);
            }
        });
    },

    showShipmentTypeInputBox: function (selectedRadio) {
        var targetBox = $("." + selectedRadio);
        $(".delivery-option").not(targetBox).hide();
        $(targetBox).css("display","flex");
    },

    readShipmentCookieAndPrompt: function () {
        $(document).ready(function () {
            if (!ACC.shipment.isShipmentSet()) {
            	
            	 var shipmentType = ACC.shipment.readShipmentCookie() == "delivery" ? "delivery" :
  	                                ACC.shipment.readShipmentCookie() == "pickup" ? "pickup" : null;

                 var customerLocation = shipmentType == "delivery" ? ACC.shipment.readLocationCookie() : 
                                        shipmentType == "pickup" ? ACC.shipment.readStoreNameCookie() : null;

                if (shipmentType && customerLocation) {
                    $("." + shipmentType + "CookieNotification").removeClass("shownone");
                    $("." + shipmentType + "CookiePreferances").val(
                        shipmentType.toUpperCase() + " , " + customerLocation.toUpperCase()
                    );
 
                   localStorage.setItem("shipmentType", shipmentType);
                   localStorage.setItem("customerLocation", customerLocation);
             	   ACC.shipment.removeShipmentCookie();
             	   
             	    if (! $('.' +  shipmentType + 'CookieNotification').hasClass('shownone')) {
                        setTimeout(function() {
                              $('.' +  shipmentType + 'CookieNotification').addClass("shownone");
                          }, 30 * 1000);
                    }
                }   
            }          	 
        });
    },
    
    bindShipmentCookieClickEvents: function () {
        $(document).on("click", ".setUserCookieNo", function (e) {
            ACC.shipment.removeShipmentCookie();
            $(".deliveryCookieNotification").addClass("shownone");
            $(".pickupCookieNotification").addClass("shownone");
        });
    },

    bindClickEventsOfCheckoutPageInShipmentBox: function () {
        // Toggle between Delivery and Pickup forms in checkout page
        $(document).on("click", ".checkout-shipment-mode", function (e) {
            $(this).siblings(".checkout-shipment-mode").removeClass("selected");
            $(this).addClass("selected");
            if ($(this).hasClass("choose-delivery")) {
                $(this).closest(".checkout-shipmentmode").find(".pickup-selection").hide();
                $(this).closest(".checkout-shipmentmode").find(".delivery-selection").css("display", "flex");
                
            } else {
                $(this).closest(".checkout-shipmentmode").find(".delivery-selection").hide();
                $(this).closest(".checkout-shipmentmode").find(".pickup-selection").css("display", "flex");
            }
        });
    },
};
