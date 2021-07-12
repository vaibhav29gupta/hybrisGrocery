ACC.savedcarts = {

    _autoload: [
        ["bindRestoreSavedCartClick", $(".js-restore-saved-cart").length != 0],
        ["bindDeleteSavedCartLink", $('.js-delete-saved-cart').length != 0],
        ["bindDeleteConfirmLink", $('.js-savedcart_delete_confirm').length != 0],
        "bindSaveCartForm",
        ["bindUpdateUploadingSavedCarts", $(".js-uploading-saved-carts-update").length != 0]
    ],
    
    $savedCartRestoreBtn: {},
    $currentCartName: {},

    bindRestoreSavedCartClick: function () {
        $(".js-restore-saved-cart").click(function (event) {
                    
            event.preventDefault();
            var popupTitle = $(this).data('restore-popup-title');
            var cartId = $(this).data('savedcart-id');
            var url = ACC.config.encodedContextPath +'/my-account/saved-carts/'+encodeURIComponent(cartId)+'/restore';
            var popupTitleHtml = ACC.common.encodeHtml(popupTitle);
            
            ACC.common.checkAuthenticationStatusBeforeAction(function(){
            	$.get(url, undefined, undefined, 'html').done(function (data) {
            		ACC.colorbox.open(popupTitleHtml, {
            			html: data,
            			width: 600,
            			className: "save-cart-restore",
            			onComplete: function () {
            				ACC.common.refreshScreenReaderBuffer();
            				ACC.savedcarts.bindRestoreModalHandlers();
            				ACC.savedcarts.bindPostRestoreSavedCartLink();
                            ACC.carousel.bindCarousel();
            			},
            			
            			onClosed: function () {
            				ACC.common.refreshScreenReaderBuffer();
            			}
            		});
            	});
            });
        });
    },

    bindRestoreModalHandlers: function () {

        ACC.savedcarts.$savedCartRestoreBtn= $('.js-save-cart-restore-btn');
        ACC.savedcarts.$currentCartName= $('.js-current-cart-name');

        $(".js-prevent-save-active-cart").on('change', function (event) {
            if($(this).prop('checked') === true){
                ACC.savedcarts.$currentCartName.attr('disabled', 'disabled');
                ACC.savedcarts.$savedCartRestoreBtn.removeAttr('disabled');
            } else {
                ACC.savedcarts.$currentCartName.removeAttr('disabled');
                var inputVal = ACC.savedcarts.$currentCartName.val();
                if (inputVal == "" && inputVal.length === 0) {
                    ACC.savedcarts.$savedCartRestoreBtn.attr('disabled', 'disabled');
                }
            }
        });

        ACC.savedcarts.$currentCartName.on('focus', function (event) {
            $('.js-restore-current-cart-form').removeClass('has-error');
            $('.js-restore-error-container').html('');
        });

        ACC.savedcarts.$currentCartName.on('blur', function (event) {
            if (this.value == "" && this.value.length === 0) {
                ACC.savedcarts.$savedCartRestoreBtn.attr('disabled', 'disabled');
            } else {
                ACC.savedcarts.$savedCartRestoreBtn.removeAttr('disabled');
            }
        });
    },

    bindPostRestoreSavedCartLink: function () {
        var keepRestoredCart = true;
        var preventSaveActiveCart = false;

        $(document).on("click", '.js-keep-restored-cart', function (event) {
            keepRestoredCart = $(this).prop('checked');
        });

        $(document).on("click", '.js-prevent-save-active-cart', function (event) {
            preventSaveActiveCart = $(this).prop('checked');
        });

        $(document).on("click", '.js-save-cart-restore-btn', function (event) {
        	
            event.preventDefault();
            var cartName = $('#activeCartName').val();
            var url = $(this).data('restore-url');
            var postData = {preventSaveActiveCart: preventSaveActiveCart, keepRestoredCart: keepRestoredCart, cartName: cartName};
            
            ACC.common.checkAuthenticationStatusBeforeAction(function(){
            	$.post(url, postData, undefined, 'html').done(function (result, data, status) {
            		result = ACC.sanitizer.sanitize(result);
            		if (result == "200 OK") {
            			var url = ACC.config.encodedContextPath + "/cart"
            			window.location.replace(url);
            		} else {
            			var errorMsg = status.responseText.slice(1, -1);
            			$('.js-restore-current-cart-form').addClass('has-error');
            			$('.js-restore-error-container').html(errorMsg);
            			$('.js-savedcart_restore_confirm_modal').colorbox.resize();
            		}
            	});
            });
        });

        $(document).on("click", '.js-cancel-restore-btn', function (event) {
            ACC.colorbox.close();
        });
    },

    bindDeleteSavedCartLink: function () {
        $(document).on("click", '.js-delete-saved-cart', function (event) {
            event.preventDefault();
            var cartId = $(this).data('savedcart-id');
            var popupTitle = $(this).data('delete-popup-title');
            var popupTitleHtml = ACC.common.encodeHtml(popupTitle);

            ACC.colorbox.open(popupTitleHtml, {
                inline: true,
                className: "js-savedcart_delete_confirm_modal",
                href: "#popup_confirm_savedcart_delete_" + cartId,
                width: '500px',
                onComplete: function () {
                    $(this).colorbox.resize();
                }
            });
        });
    },

    bindDeleteConfirmLink: function () {
        $(document).on("click", '.js-savedcart_delete_confirm', function (event) {
            event.preventDefault();
            var cartId = $(this).data('savedcart-id');
            var url = ACC.config.encodedContextPath + '/my-account/saved-carts/' + encodeURIComponent(cartId) + '/delete';
            ACC.common.checkAuthenticationStatusBeforeAction(function(){
            	$.ajax({
            		url: url,
            		type: 'DELETE',
            		success: function (response) {
            			ACC.colorbox.close();
            			var url = ACC.config.encodedContextPath + "/my-account/saved-carts"
            			window.location.replace(url);
            		}
            	});
            });
        });

        $(document).on("click", '.js-savedcart_delete_confirm_cancel', function (event) {
            ACC.colorbox.close();
        });
    },
    
    bindSaveCartForm: function ()
	{
    	ACC.savedcarts.charactersLeftInit();
		var form = $('#colorbox #saveCartForm');
		var saveCart = false;
        var showSaveCartFormCallback = function () {
        var title = $('#saveCart').data("saveCartTitle");
        var titleHtml = ACC.common.encodeHtml(title);  
        if ($('.shipment-selector #saveCartName').val()) {
        	ACC.savedcarts.disableSaveCartButton(false);
        }
        $(".save-cart-form").css("display", "flex");
//            ACC.colorbox.open(titleHtml, {
//                href: "#saveCart",
//                inline: true,
//                width: "620px",
//                onOpen: function () {
//                },
//                onComplete: function () {
//                    $(this).colorbox.resize();
//                    saveCart = false;
//                },
//                onClosed: function () {
//
//                    if (saveCart) {
//                        form.submit();
//                    }
//                    document.getElementById("saveCartForm").reset();
//                    ACC.savedcarts.disableSaveCartButton(true);
//                    ACC.savedcarts.charactersLeftInit();
//                }
//            });
		};
		
		var showSaveCartEditFormCallback = function () {
			var title = $('#saveCart').data("saveCartTitle");
			var titleHtml = ACC.common.encodeHtml(title);  
			if ($('#colorbox #saveCartName').val()) {
				ACC.savedcarts.disableSaveCartButton(false);
			}
	           ACC.colorbox.open(titleHtml, {
	               href: ".edit-cart-form #saveCart",
	               inline: true,
				   width: "620px",
				   overlayClose : false,
	               onOpen: function () {
	               },
	               onComplete: function () {
	                   $(this).colorbox.resize();
	                   saveCart = false;
	                   $('html, body').css('overflow', 'hidden');
	               },
	               onClosed: function () {
	
	                   if (saveCart) {
	                       form.submit();
	                   }
	                   ACC.savedcarts.disableSaveCartButton(true);
	                   ACC.savedcarts.charactersLeftInit();
	                   $('html, body').css('overflow', '');
	               }
	           });
			};

        $(document).on("click",".shipment-selector .js-save-cart-link",function(e){
            e.preventDefault();
            ACC.common.checkAuthenticationStatusBeforeAction(showSaveCartFormCallback);
		});

		$(document).on("click",".js-update-saved-cart",function(e){
            e.preventDefault();
            ACC.common.checkAuthenticationStatusBeforeAction(showSaveCartEditFormCallback);
		});
		
		$(document).on("click",'#cancelSaveCartButton', function (e) {
			ACC.colorbox.close();
		});
		
		$(document).on("keyup", '#colorbox #saveCartName', function() {		
			// enable the save cart button
	 		$('#colorbox #saveCart #saveCartButton').prop('disabled', this.value.trim() == "" ? true : false);  	
			// limit the text length 
            var maxchars = 255;
			var value=$('#localized_val').attr('value');
			var tlength = $(this).val().length;
			remain = maxchars - parseInt(tlength);
        	$('#colorbox #remain').text(value+' : '+remain);
		});

		$(document).on("keyup", '#colorbox #saveCartDescription', function() {
			
			if ($('#colorbox #saveCartName').val()) {
				ACC.savedcarts.disableSaveCartButton(false);
			}
			var maxchars = 255;
			var value=$('#localized_val').attr('value');
			var tlength = $(this).val().length;
			remain = maxchars - parseInt(tlength);
        	$('#colorbox #remainTextArea').text(value+' : '+remain);
		});
		
		$(document).on("click",'#colorbox #saveCart #saveCartButton', function (e) {
			e.preventDefault();
			$('#colorbox #saveCartForm').submit();
		});
	},
	
	charactersLeftInit: function() {
	    $('#colorbox #remain').text($('#localized_val').attr('value')+' : 255');
     	$('#colorbox #remainTextArea').text($('#localized_val').attr('value')+' : 255');
	},
	
	disableSaveCartButton: function(value) {
		$('#saveCart #saveCartButton').prop('disabled', value);
	},
	

	bindUpdateUploadingSavedCarts : function() {
		var cartIdRowMapping = $(".js-uploading-saved-carts-update").data("idRowMapping");
		var refresh = $(".js-uploading-saved-carts-update").data("refreshCart");
		if (cartIdRowMapping && refresh) {
			var interval = $(".js-uploading-saved-carts-update").data("refreshInterval");
			var arrCartIdAndRow = cartIdRowMapping.split(',');
			var mapCartRow = new Object();
			var cartCodes = [];
			for (i = 0; i < arrCartIdAndRow.length; i++) {
				var arrValue = arrCartIdAndRow[i].split(":");
				if (arrValue != "") {
					mapCartRow[arrValue[0]] = arrValue[1];
					cartCodes.push(arrValue[0]);
				}
			}

			if (cartCodes.length > 0) {
				setTimeout(function() {ACC.savedcarts.refreshWorker(cartCodes, mapCartRow, interval)}, interval);
			}	
		}
	},
	

	refreshWorker : function(cartCodes, mapCartRow, interval) {
		$.ajax({
			dataType : "json",
			url : ACC.config.encodedContextPath	+ '/my-account/saved-carts/uploadingCarts',
			data : {
				cartCodes : cartCodes
			},
			type : "GET",
			traditional : true,
			success : function(data) {
				if (data != undefined) {
					var hidden = "hidden";
					var rowId = "#row-";
					for (i = 0; i < data.length; i++) {
						var cart = data[i];

						var index = $.inArray(cart.code, cartCodes);
						if (index > -1) {
							cartCodes.splice(index, 1)
						}	
						var rowIdIndex = mapCartRow[cart.code]; 
						if (rowIdIndex != undefined) {
							var rowSelector = rowId + rowIdIndex;
							$(document).find(rowSelector + " .js-saved-cart-name").removeClass("not-active");
							$(document).find(rowSelector + " .js-saved-cart-date").removeClass("hidden");
							$(document).find(rowSelector + " .js-file-importing").remove();
							$(document).find(rowSelector + " .js-saved-cart-description").text(cart.description);
							var numberOfItems = cart.entries.length;
							$(document).find(rowSelector + " .js-saved-cart-number-of-items").text(numberOfItems);
							$(document).find(rowSelector + " .js-saved-cart-total").text(cart.totalPrice.formattedValue);
							if (numberOfItems > 0) {
								$(document).find(rowSelector + " .js-restore-saved-cart").removeClass(hidden);
							}
							$(document).find(rowSelector + " .js-delete-saved-cart").removeClass(hidden);
						}
					}
				};
				
				if (cartCodes.length > 0) {
					setTimeout(function() {ACC.savedcarts.refreshWorker(cartCodes, mapCartRow, interval)}, interval);
				}
			}
		})
	}
}
