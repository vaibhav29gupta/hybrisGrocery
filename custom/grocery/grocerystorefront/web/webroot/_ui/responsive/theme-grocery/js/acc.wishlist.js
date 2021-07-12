ACC.wishlist = {
    _autoload: [
        "bindWishlistToCreateAction",
        "bindWishlistToRenameAction",
        "bindWishlistPopupToClick",
        "bindWishInputBoxToActions",
        "bindWishlistPopupToAllAction",
        "bindCreateAndAddProductToWishlist",
        "bindWishlistPopToAddAndRemoveProduct",
        "bindRemoveProductFromWishlist",
        "bindAllProductToCart",
        "bindRenameButtonActions",
    ],
    lastSelected: -1,

    bindWishlistToCreateAction: function () {
        // create wishlist on create button click
        $(document).on("click", ".js-create-page-wishlist-btn:not(.disabled-btn)", function (e) {
            var wishlistName = $(".create-wishlist-name").val();

            $.ajax({
                type: "POST",
                url: ACC.config.encodedContextPath + "/wishlist/create-wishlist",
                data: { wishlistName: wishlistName },
                dataType: "json",
                success: function (response) {
                    if (response.result) {
                        setTimeout(function () {
                            // wait for 1 secs(2)
                            location.reload(); // then reload the page.(3)
                        }, 1000);
                        ACC.wishlist.showSuccessMessage($(".success-created").val());
                    } else {
                        ACC.wishlist.showErrorMessage($(".error-created").val());
                    }
                },
                error: function (error) {
                    ACC.wishlist.showErrorMessage($(".error-created").val());
                },
            });
        });
    },

    bindWishlistToRenameAction: function () {
        $(document).on("click", ".js-rename-page-wishlist-btn:not(.disabled-btn)", function () {
            var parentContainer = $(this).closest(".wishlist-card");
            var wishlistNameContainer = parentContainer.find(".js-wishlist-name");
            var oldWishlistName = $(wishlistNameContainer).data("wishlist-name");
            var newWishlistName = parentContainer.find(".create-wishlist-name").val();

            $.ajax({
                type: "POST",
                url: ACC.config.encodedContextPath + "/wishlist/rename-wishlist",
                data: {
                    wishlistName: oldWishlistName,
                    wishListNewName: newWishlistName,
                },
                dataType: "json",
                success: function (response) {
                    if (response.result) {
                        // update old name with new
                        $(wishlistNameContainer).text(newWishlistName);
                        $(wishlistNameContainer).attr("data-wishlist-name", newWishlistName);

                        var updatedAnchorTag = $(wishlistNameContainer).data("wishlist-url-prefix") + newWishlistName;
                        $(wishlistNameContainer).closest("a").attr("href", updatedAnchorTag);

                        var selectedElement = parentContainer.find(".rename-wishlist");

                        parentContainer.find(".js-rename-wishlist").css("display", "flex");
                        parentContainer.find(".js-remove-wishlist").css("display", "flex");
                        parentContainer.find(".close-rename").hide();
                        parentContainer.find(".js-rename-page-wishlist-btn").hide();
                        parentContainer.find(".wishlist-details").css("display", "flex");
                        selectedElement.hide();
                    } else {
                        ACC.wishlist.showErrorMessage($(".error-rename").val());
                    }
                },
                error: function (error) {
                    ACC.wishlist.showErrorMessage($(".error-rename").val());
                },
            });
        });
    },

    bindWishInputBoxToActions: function () {
        // show inputbox on create button click
        $(document).on("click", ".wishlist-create-btn", function (e) {
            $(".create-wishlist-pane").toggle("shownone");

            $(this).siblings(".wishlist-page").toggleClass("shownone");

            if ($(this).hasClass("cancel-operation")) {
                $(this).removeClass("cancel-operation");
                $(this).removeClass("btn-contrast");
                $(this).addClass("btn-a");
                $(this).html("");
                $(this).text($(".create").val());
            } else {
                $(this).addClass("cancel-operation");
                $(this).addClass("btn-contrast");
                $(this).removeClass("btn-a");
                $(this).html("");
                $(this).append($("<i>").addClass("fa fa-times"));
            }
        });

        // close rename pane
        $(document).on("click", ".close-rename", function (e) {
            var parentElement = $(this).parents(".wishlist-data");
            var selectedElement = parentElement.find(".rename-wishlist");

            parentElement.find(".js-rename-wishlist").css("display", "flex");
            parentElement.find(".js-remove-wishlist").css("display", "flex");
            parentElement.find(".close-rename").hide();
            parentElement.find(".js-rename-page-wishlist-btn").hide();
            parentElement.find(".wishlist-details").css("display", "flex");
            selectedElement.hide();
        });

        $(document).on("click", ".js-rename-wishlist", function (e) {
            e.preventDefault();

            var parentElement = $(this).parents(".wishlist-data");
            var selectedElement = parentElement.find(".rename-wishlist");

            $(".close-rename").hide();
            $(".js-rename-page-wishlist-btn").hide();
            $(".rename-wishlist").hide();
            $(".js-remove-wishlist").css("display", "flex");
            $(".js-rename-wishlist").css("display", "flex");
            $(".wishlist-details").css("display", "flex");
            parentElement.find(".js-rename-wishlist").hide();
            parentElement.find(".js-remove-wishlist").hide();
            parentElement.find(".close-rename").css("display", "flex");
            parentElement.find(".js-rename-page-wishlist-btn").css("display", "flex");
            parentElement.find(".wishlist-details").hide();
            selectedElement.css("display", "flex");
        });

        // check wishlist name should greater then 3
        $(document).on("keyup", ".create-wishlist-name", function (e) {
            var submitButton = $(e.target).closest(".wishlist-pane").find(".disabled-btn");
            validateEnterWishlistName(e.target, submitButton);
        });

        async function validateEnterWishlistName(inputBox, submitButton) {
            var enteredWishlistName = $(inputBox).val();
            $(".wishlist-name-validation").html("");
            if (enteredWishlistName.trim().length > 3) {
                var isExists = await isWishlistNameDuplicate(enteredWishlistName, $(".js-wishlist-name"));

                if (isExists) {
                    $(submitButton).addClass("disabled-btn");
                    showError(inputBox, $(".duplicate").val());
                } else {
                    $(submitButton).removeClass("disabled-btn");
                    hideError();
                }
            } else {
                $(submitButton).addClass("disabled-btn");
                showError(inputBox, $(".miniCharacters").val());
            }
        }

        // check duplicate
        function isWishlistNameDuplicate(targetName, allNames) {
            var isExist = false;
            $(allNames).each(function () {
                var name = $(this).text();
                if (targetName.trim().toLowerCase() === name.trim().toLowerCase()) {
                    isExist = true;
                }
            });
            return isExist ? true : false;
        }

        function showError(inputBox, message) {
            $(inputBox).parent().siblings(".wishlist-name-validation").append(message).show().delay(3000).fadeOut();
        }

        function hideError() {
            $(".wishlist-name-validation").append(" ");
        }
    },

    bindWishlistPopupToClick: function () {
        $(document).on("click", ".js-add-to-wishlist", function () {
            var productCode = $(this).data("productcode");
            var loginUrl = $(this).data("loginUrl");

            var url = ACC.config.encodedContextPath + "/wishlist/product/get";

            waitForResponse(url, { productCode: productCode }, loginUrl);
        });

        async function waitForResponse(url, data, loginUrl) {
            var response = await ACC.wishlist.genericAjaxForGetCall(url, data);
            if (response.addToWishlistLayer.length > 0) {
                ACC.wishlist.displayWishlistPopup(response, data.productCode, loginUrl);
            }
        }
    },

    displayWishlistPopup: function (wishlistResult, productCode, loginUrl) {
        var targetClass = $(".js-add-to-wishlist-" + productCode);
        var hideClass = $(".add-to-wishlist");

        ACC.wishlist.bindShowActionToTargetBlock(targetClass, hideClass);

        var attachedToClass = $(".js-attached-dynamic-popup-" + productCode);
        var html = wishlistResult.addToWishlistLayer;
        attachedToClass.html(html);

        if ($(html).find(".user-not-found-error").length > 0) {
            setTimeout(function () {
                window.location.href = loginUrl;
            }, 2500);
        }
    },

    bindWishlistPopupToAllAction: function () {
        $(document).click(function (e) {
            if (!$(".add-to-wishlist").has(e.target).length == 1) {
                $(".add-to-wishlist").css("display", "none");
            }
        });

        // close on x
        $(document).on("click", ".wishlist-close-btn", function () {
            $(this).parents(".add-to-wishlist").hide();
        });

        // create wishlist button
        $(document).on("click", ".wishlist-popup-create-wishlist-btn", function () {
            $(this).siblings(".wishlist-create-panel").show();
            $(this).siblings(".wishlist-panel").hide();

            // header text show/hide
            $(this).siblings(".wishlist-header").children(".wishlist-title").hide();
            $(this).siblings(".wishlist-header").children(".wishlist-back").show();
            $(this).hide();
        });

        // back button press
        $(document).on("click", ".wishlist-back", function () {
            $(this).parent(".wishlist-header").siblings(".wishlist-create-panel").hide();
            $(this).parent(".wishlist-header").siblings(".wishlist-panel").show();

            $(this).hide();
            $(this).siblings(".wishlist-title").show();
            $(this).closest(".add-to-wishlist").find(".wishlist-popup-create-wishlist-btn").css("display", "flex");
        });
    },

    bindShowActionToTargetBlock: function (targetClass, hideClass) {
        $(hideClass).not(targetClass).hide();
        $(targetClass).show();
    },

    bindCreateAndAddProductToWishlist: function () {
        $(document).on("click", ".js-create-popup-wishlist-btn", function () {
            var index = $(this).index(".js-create-popup-wishlist-btn");
            var productCode = $(this)
                .parents(".wishlist-create-panel")
                .siblings(".wishlist-panel")
                .data("product-code");
            var wishlistName = $(this).siblings(".create-wishlist-name").val();

            $.ajax({
                type: "POST",
                url: ACC.config.encodedContextPath + "/wishlist/create-wishlist/product/add/",
                data: {
                    productCode: productCode,
                    wishlistName: wishlistName,
                },
                dataType: "json",
                success: function (response) {
                    // open wishlist popup from homepage
                    if (response) {
                        var url = ACC.config.encodedContextPath + "/wishlist/product/get";
                        waitForResponse(url, { productCode: productCode });
                    }
                },
                error: function (error) {},
            });
        });

        async function waitForResponse(url, data) {
            var response = await ACC.wishlist.genericAjaxForGetCall(url, data);
            if (response.addToWishlistLayer.length > 0) {
                ACC.wishlist.displayWishlistPopup(response, data.productCode);
            }
        }
    },

    bindWishlistPopToAddAndRemoveProduct: function () {
        $(document).on("click", ".check-box", function () {
            var currentCheckedItem = this.classList[2];
            var wishlistName = $(this).closest(".js-wishlist-items").find(".js-wishlist-name-input").val();
            var productCode = $(this).closest(".js-wishlist-items").find(".js-product-code-input").val();

            var data = {
                wishlistName: wishlistName,
                productCode: productCode,
            };

            if ($("." + currentCheckedItem).is(":checked")) {
                var url = ACC.config.encodedContextPath + "/wishlist/addWishlistentryByName";
                validateAndPerformAction(url, data);
            } else {
                var url = ACC.config.encodedContextPath + "/wishlist/remove-wishlist-product/";
                validateAndPerformAction(url, data);
            }
        });

        async function validateAndPerformAction(url, data) {
            var response = await ACC.wishlist.genericAjaxForPostCall(url, data);
            if (response.result) {
                // TODO show success
            } else {
                // show err
            }
        }
    },

    bindRemoveProductFromWishlist: function () {
        $(document).on("click", ".js-remove-product", function () {
        	var parent = $(this).closest(".account-section-content");
            var wishlistName = parent.data("wishlist-name");
            var productCode = $(this).closest(".wishlist-details-card").data("product-code");
            var container = $(this).closest(".wishlist-details-card");
            var currentWishlistCount = $(".cart-product-count").text();

            var data = {
                wishlistName: wishlistName,
                productCode: productCode,
            };
            var url = ACC.config.encodedContextPath + "/wishlist/remove-wishlist-product/";
            validateAndPerformAction(url, data, container, currentWishlistCount);
        });

        async function validateAndPerformAction(url, data, container, currentWishlistCount) {
            var response = await ACC.wishlist.genericAjaxForPostCall(url, data);

            if (response.result) {
                // update and hide wishlist
                container.hide();
                $(".cart-product-count").text(currentWishlistCount - 1);
                // show success message
                ACC.wishlist.showSuccessMessage($(".success-deleted").val());
            } else {
                // show error message
                ACC.wishlist.showErrorMessage($(".error-deleted").val());
            }
        }
    },

    genericAjaxForPostCall: function (url, data) {
        return new Promise((resolve, reject) => {
            $.ajax({
                url: url,
                data: data,
                type: "POST",
                dataType: "json",
                success: resolve,
                error: reject,
            });
        });
    },

    genericAjaxForGetCall: function (url, data) {
        return new Promise((resolve, reject) => {
            $.ajax({
                url: url,
                data: data,
                type: "GET",
                dataType: "json",
                success: resolve,
                error: reject,
            });
        });
    },

    bindAllProductToCart: function () {
        $(document).on("click", ".js-add-to-cart-btn", function () {
            var wishListName = $(this).data("wishlist-name");

            if (ACC.shipment.isShipmentSet()) {
                $.ajax({
                    type: "POST",
                    url: ACC.config.encodedContextPath + "/wishlist/entries/add/all/" + wishListName,
                    success: function (response) {
                        if (response.error != "") {
                            ACC.wishlist.showErrorMessage($(".error-added").val());
                        } else {
                            if (typeof ACC.minicart.updateMiniCartDisplay == "function") {
                                ACC.minicart.updateMiniCartDisplay();
                            }
                            var updatedCartLayer = response.cartLayer;
                            $(".latest-cart").html(updatedCartLayer);
                            ACC.wishlist.updateQtyOfWishlistEntries(response.entries);
                            var listOfUnAffectedProducts = response.listOfUnAffectedProducts;
                            if (listOfUnAffectedProducts.length == 0) {
                                // all products are added i.e. no product is
                                // outofstock present
                                ACC.wishlist.showSuccessMessage($(".success-added").val());
                            } else {
                                // list of product codes to show css which are
                                // unaffected i.e. not added to cart
                                var affectedProductCodeString = "";
                                listOfUnAffectedProducts.forEach((element) => {
                                    var productCode = element;
                                    $(".affected-item-" + productCode).addClass("affectedItemRedBox");
                                    affectedProductCodeString = affectedProductCodeString + productCode + ",";
                                    // currently not working. Please check this
                                    // $(".affected-item-" + productCode).addClass("affected-cart affected-item");
                                    // setTimeout(function(){
                                    // $(".affected-item-" + productCode)
                                    // .removeClass("affected-item");
                                    // },3000)
                                });
                                ACC.wishlist.showErrorMessage(
                                    $(".error-not-added").val() +
                                        affectedProductCodeString.substr(0, affectedProductCodeString.length - 1)
                                );
                            }
                        }
                    },
                    error: function (error) {
                        ACC.wishlist.showErrorMessage($(".error-added").val());
                    },
                });
            } else {
                ACC.shipment.showSelectorInRawMode();
            }
        });
    },

    updateQtyOfWishlistEntries: function (entries) {
        entries.forEach(function (e1, index) {
            $(".js-initial-quantity-" + e1.productCode).val(e1.quantity);
            $(".js-qty-selector-input-" + e1.productCode).val(e1.quantity);
        });
    },

    showErrorMessage: function (message) {
        $(".wishlist-error-message").empty();
        $(".wishlist-error-message").append(message).show().delay(5000).fadeOut();
    },

    showSuccessMessage: function (message) {
        $(".wishlist-success-message").empty();
        $(".wishlist-success-message").append(message).show().delay(5000).fadeOut();
    },

    bindRenameButtonActions: function () {
        $("rename-wishlist").click(function (e) {
            e.stopPropagation();
        });
    },

    toggleVisibility: function (element) {
        if (element.css("display") == "flex") {
            element.css("display", "none");
        } else {
            element.css("display", "flex");
        }
    },

    toggleVisibilityBlock: function (element) {
        if (element.css("display") == "block") {
            element.css("display", "none");
        } else {
            element.css("display", "block");
        }
    },
};
