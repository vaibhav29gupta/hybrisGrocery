ACC.checkout = {
    _autoload: [
        "bindCheckO",
        "bindForms",
        "bindSavedPayments",
        "checkoutNextStep",
        "checkoutMobileView",
        "hideShowItems",
        "bindSelectBoxes"
    ],

    bindForms: function () {
        $(document).on("click", "#addressSubmit", function (e) {
            e.preventDefault();
            $("#addressForm").submit();
        });

        $(document).on("click", "#deliveryMethodSubmit", function (e) {
            e.preventDefault();
            $("#selectDeliveryMethodForm").submit();
        });
    },

    bindSavedPayments: function () {
        $(document).on("click", ".js-saved-payments", function (e) {
            e.preventDefault();

            var title = $("#savedpaymentstitle").html();

            $.colorbox({
                href: "#savedpaymentsbody",
                inline: true,
                maxWidth: "100%",
                opacity: 0.7,
                //width:"320px",
                title: title,
                close: '<span class="glyphicon glyphicon-remove"></span>',
                onComplete: function () {},
            });
        });
    },

    bindCheckO: function () {
        var cartEntriesError = false;

        // Alternative checkout flows options
        $(".doFlowSelectedChange").change(function () {
            if ("multistep-pci" == $("#selectAltCheckoutFlow").val()) {
                $("#selectPciOption").show();
            } else {
                $("#selectPciOption").hide();
            }
        });
        $(document).on("click",".js-continue-shopping-button", function () {
            var checkoutUrl = $(this).data("continueShoppingUrl");
            window.location = checkoutUrl;
        });

        $(".js-create-quote-button").click(function () {
            $(this).prop("disabled", true);
            var createQuoteUrl = $(this).data("createQuoteUrl");
            window.location = createQuoteUrl;
        });

        $(".expressCheckoutButton").click(function () {
            document.getElementById("expressCheckoutCheckbox").checked = true;
        });

        $(document).on("input", ".confirmGuestEmail,.guestEmail", function () {
            var orginalEmail = $(".guestEmail").val();
            var confirmationEmail = $(".confirmGuestEmail").val();

            if (orginalEmail === confirmationEmail) {
                $(".guestCheckoutBtn").removeAttr("disabled");
            } else {
                $(".guestCheckoutBtn").attr("disabled", "disabled");
            }
        });

        $(document).on("click",".js-continue-checkout-button", function () {
            var checkoutUrl = $(this).data("checkoutUrl");

            cartEntriesError = ACC.pickupinstore.validatePickupinStoreCartEntires();
            if (!cartEntriesError) {
                var expressCheckoutObject = $(".express-checkout-checkbox");
                if (expressCheckoutObject.is(":checked")) {
                    window.location = expressCheckoutObject.data("expressCheckoutUrl");
                } else {
                    var flow = $("#selectAltCheckoutFlow").val();
                    if (flow == undefined || flow == "" || flow == "select-checkout") {
                        // No alternate flow specified, fallback to default behaviour
                        window.location = checkoutUrl;
                    } else {
                        // Fix multistep-pci flow
                        if ("multistep-pci" == flow) {
                            flow = "multistep";
                        }
                        var pci = $("#selectPciOption").val();

                        // Build up the redirect URL
                        var redirectUrl = checkoutUrl + "/select-flow?flow=" + flow + "&pci=" + pci;
                        window.location = redirectUrl;
                    }
                }
            }
            return false;
        });
    },

    checkoutNextStep: function () {
        $(".submit_cartReviewFrom").click(function () {
            $("#cartReviewFrom").submit();
        });

        $(".submit_paymentDetailOrderPostForm").click(function () {
            $("#paymentDetailOrderPostForm").submit();
        });
    },

    checkoutMobileView: function () {
        $(".sticky-checkout-footer").click(function () {
            if ($(this).css("position") == "fixed") {
                $(".checkout-right-financials-mobile").html($(".checkout-right-financials-desktop").html());
                if ($(this).hasClass("toggled")) {
                    $(this).removeClass("toggled");
                    $(".checkout-right-financials-mobile").slideUp();
                } else {
                    $(this).addClass("toggled");
                    $(".checkout-right-financials-mobile").slideDown();
                }
                $(document).click(function (event) {
                    $target = $(event.target);
                    if (
                        !$target.closest(".sticky-checkout-footer").length &&
                        $(".sticky-checkout-footer").hasClass("toggled")
                    ) {
                        $(".sticky-checkout-footer").removeClass("toggled");
                        $(".checkout-right-financials-mobile").slideUp();
                    }
                });
            }
            if ($(this).css("display") == "flex") {
                if ($(this).hasClass("toggled")) {
                    $(this).removeClass("toggled");
                    $(this).siblings().show();
                } else {
                    $(this).addClass("toggled");
                    $(this).siblings().hide();
                }
            }
        });
    },

    hideShowItems: function () {
        $(".hide-show-link").click(function () {
            if ($(this).hasClass("hide-initial")) {
                $(".hide-initial").hide();
                $(this).siblings(".hide-show-link").css("display", "flex");
            } else {
                $(this).hide();
                $(".hide-initial").css("display", "flex");
            }
        });
    },

    bindSelectBoxes: function () {
        for (const dropdown of document.querySelectorAll(".js-checkout-select-wrapper")) {
            dropdown.addEventListener("click", function () {
                this.querySelector(".js-checkout-select").classList.toggle("open");
            });
        }

        for (const option of document.querySelectorAll(".js-checkout-custom-option")) {
            option.addEventListener('click', function() {
                if (!this.classList.contains('selected') && !this.classList.contains('disabled')) {
                    this.parentNode.querySelector('.js-checkout-custom-option.selected').classList.remove('selected');
                    this.classList.add('selected');
                    this.closest('.js-checkout-select').querySelector('.js-checkout-select__trigger span').textContent = this.textContent;
                    this.closest('.js-checkout-select').querySelector('.set-value-to-input').value = this.getAttribute('data-value');
                }
            })
        }

        window.addEventListener("click", function (e) {
            for (const select of document.querySelectorAll(".js-checkout-select")) {
                if (!select.contains(e.target)) {
                    select.classList.remove("open");
                }
            }
        });
    },
};
