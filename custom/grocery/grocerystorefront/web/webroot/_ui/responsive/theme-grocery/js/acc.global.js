ACC.global = {

    _autoload: [
    	"onPageReload",
        ["passwordStrength", $('.password-strength').length > 0],
        "bindToggleOffcanvas",
        "bindToggleXsSearch",
        "bindToggleChildrenDisplay",
        "bindHoverIntentMainNavigation",
        "initImager",
        "backToHome",
        "bindDropdown",
        "closeAccAlert",
        ["onCloseStraplineBanner", $(".close-div").length != 0],
        "stickyHeader",
        "shipmentSelectorResizing",
        "bindStoreAddressDropdown"
    ],
    
    onPageReload: function(){
    	$(document).ready(function() {
    		if ($.cookie('strapline-notification') == 'ACCEPTED'){
    			$('.js-strapline-component').hide();
    		}
    		else{
    			$('.js-strapline-component').show();
    		}
    	})
    },
    
    onCloseStraplineBanner : function() {
		$('.close-div').on("click", function(){
			 var date = new Date();
			 date.setTime(date.getTime() + (15 * 60 * 1000));
			$.cookie('strapline-notification', "ACCEPTED", {path:'/', expires: date});
			$('.js-strapline-component').hide();
		});
	},

    passwordStrength: function () {
        $('.password-strength').pstrength({
            verdicts: [ACC.pwdStrengthTooShortPwd,
                ACC.pwdStrengthVeryWeak,
                ACC.pwdStrengthWeak,
                ACC.pwdStrengthMedium,
                ACC.pwdStrengthStrong,
                ACC.pwdStrengthVeryStrong],
            minCharText: ACC.pwdStrengthMinCharText
        });
    },

    bindToggleOffcanvas: function () {
        $(document).on("click", ".js-toggle-sm-navigation", function () {
            ACC.global.toggleClassState($("main"), "offcanvas");
            ACC.global.toggleClassState($("html"), "offcanvas");
            ACC.global.toggleClassState($("body"), "offcanvas");
            ACC.global.resetXsSearch();
        });
    },

    bindToggleXsSearch: function () {
        $(document).on("click", ".js-toggle-xs-search", function () {
            ACC.global.toggleClassState($(".bdm-search"), "active");
            ACC.global.toggleClassState($(".site-search"), "active");
            ACC.global.toggleClassState($(".js-mainHeader .navigation--middle"), "search-open");
        });
    },
    
    bindToggleChildrenDisplay: function () {
        $(document).on("click", ".js-toggle-child-state", function () {
        	var child = $(this).data("child");
            ACC.global.toggleClassState($("."+child), "display");
        });
    },

    resetXsSearch: function () {
        $('.site-search').removeClass('active');
        $('.bdm-search').removeClass('active');
        $(".js-mainHeader .navigation--middle").removeClass("search-open");
    },

    toggleClassState: function ($e, c) {
        $e.hasClass(c) ? $e.removeClass(c) : $e.addClass(c);
        return $e.hasClass(c);
    },

    bindHoverIntentMainNavigation: function () {

        enquire.register("screen and (min-width:" + ACC.common.encodeHtml(screenMdMin) + ")", {

            match: function () {
                // on screens larger or equal screenMdMin (1024px) calculate position for .sub-navigation
                $(".js-enquire-has-sub").hoverIntent(function () {
                    var $this = $(this),
                        itemWidth = $this.width();
                    var $subNav = $this.find('.js_sub__navigation'),
                        subNavWidth = $subNav.outerWidth();
                    var $mainNav = $('.js_navigation--bottom'),
                        mainNavWidth = $mainNav.width();
                    

                    // get the left position for sub-navigation to be centered under each <li>
                    var leftPos = $this.position().left + itemWidth / 2 - subNavWidth / 2;
                    // get the top position for sub-navigation. this is usually the height of the <li> unless there is more than one row of <li>
                    var topPos = $this.position().top + $this.height();
                    if (leftPos > 0 && leftPos + subNavWidth < mainNavWidth) {
                        // .sub-navigation is within bounds of the .main-navigation
                        $subNav.css({
                            "left": leftPos,
                            "top": topPos,
                            "right": "auto"
                        });
                    } else if (leftPos < 0) {
                        // .suv-navigation can't be centered under the <li> because it would exceed the .main-navigation on the left side
                        $subNav.css({
                            "left": 0,
                            "top": topPos,
                            "right": "auto"
                        });
                    } else if (leftPos + subNavWidth > mainNavWidth) {
                        // .suv-navigation can't be centered under the <li> because it would exceed the .main-navigation on the right side
                        $subNav.css({
                            "right": 0,
                            "top": topPos,
                            "left": "auto"
                        });
                    }
                    $this.addClass("show-sub");
                }, function () {
                    $(this).removeClass("show-sub")
                });
            },

            unmatch: function () {
                // on screens smaller than screenMdMin (1024px) remove inline styles from .sub-navigation and remove hoverIntent
                $(".js_sub__navigation").removeAttr("style");
                $(".js-enquire-has-sub").hoverIntent(function () {
                    // unbinding hover
                });
            }

        });
    },

    initImager: function (elems) {
        elems = elems || '.js-responsive-image';
        this.imgr = new Imager(elems);
    },

    reprocessImages: function (elems) {
        elems = elems || '.js-responsive-image';
        if (this.imgr == undefined) {
            this.initImager(elems);
        } else {
            this.imgr.checkImagesNeedReplacing($(elems));
        }
    },

    // usage: ACC.global.addGoogleMapsApi("callback function"); // callback function name like "ACC.global.myfunction"
    addGoogleMapsApi: function (callback) {
        if (callback != undefined && $(".js-googleMapsApi").length == 0) {
        	var googleApiSrc = "//maps.googleapis.com/maps/api/js?key=" + ACC.common.encodeHtml(ACC.config.googleApiKey) + '&sensor=false&callback=' + ACC.common.encodeHtml(callback);
    		$('head').append($("<script>").addClass("js-googleMapsApi")
    								.attr("type", "text/javascript")
    								.attr("src", googleApiSrc));
        } else if (callback != undefined) {
            eval(callback + "()"); //NOSONAR
        }
    },

    backToHome: function () {
        $(".backToHome").on("click", function () {
            var sUrl = ACC.config.contextPath;
            window.location = sUrl;
        });
    },
    
    bindDropdown: function() {
    	$(document).on("click", ".dropdown-toggle", dropdownToggle);
    },

    closeAccAlert: function () {
        $(".closeAccAlert").on("click", function () {
            $(this).parent('.getAccAlert').remove();
        });
    },

    stickyHeader : function() {
        $(window).scroll(function (event) {
			var scroll = $(window).scrollTop();
            if(!$("body").hasClass("template-pages-MultiStepCheckoutSummaryPageTemplate")) {
                if(scroll >= .1) {
                    if($(".top-deck").css("display")== "flex"){
                        $(".top-deck").slideUp(200);
                        $(".navigation--bottom").slideUp(200);
                        $(".breadcrumb-section").slideUp(200);
                        $(".bottom-deck").addClass("sticky-border");
//                        $(".site-logo-hidden").css({"margin-top":"5px"}).slideDown(200,function () {
//                            $(".bdd-left").removeClass("col-sm-6").addClass("col-sm-4");
//                        });
//                        $(".site-logo-hidden").find("img").css({"height":"40px","margin":"5px;"});
                        
                    }
                }
                else if(scroll < .1){
                    if($(".top-deck").css("display")== "none"){
                        $(".top-deck").slideDown(200);
                        $(".navigation--bottom").slideDown(200);
                        $(".breadcrumb-section").slideDown(200);
                        $(".bottom-deck").removeClass("sticky-border");
//                        $(".site-logo-hidden").css({"margin-top":"5px"}).slideUp(200, function(){
//                            $(".bdd-left").removeClass("col-sm-4").addClass("col-sm-6");
//                        });
//                        $(".site-logo-hidden").find("img").css({"height":"40px"});
                        
                    }
                }
            }
			
		});
    },
    
    removeGlobalErrorAlerts:function(){
    	$('.global-alerts').remove();
    },

    shipmentSelectorResizing:function() {
        $( window ).resize(function() {
            $("#colorbox.shipment-selector").css("height", $(window)[0].innerHeight);
            $("#colorbox.shipment-selector #cboxWrapper").css("height", $(window)[0].innerHeight);
            $("#colorbox.shipment-selector #cboxContent").css("height", $(window)[0].innerHeight);
            $("#colorbox.shipment-selector #cboxLoadedContent").css("height", $(window)[0].innerHeight);
            $("#colorbox.shipment-selector .js-full-pageheight").css("height", $(window)[0].innerHeight);
        });
    },

    bindStoreAddressDropdown: function(){
        $(document).on("click", ".js-store-address-dropdown", function () {

            var content = $(this).siblings('.address-details');
            $(content).toggle("slow");
        })
    }
};

// ***** Dropdown begins *****
function dropdownParent($this) {
    var selector = $this.attr('href')
    selector = selector && /#[A-Za-z]/.test(selector) && selector.replace(/.*(?=#[^\s]*$)/, '') // strip for ie7

    var $parent = selector && $(selector)

    return $parent && $parent.length ? $parent : $this.parent()
  };

function dropdownClearMenus(e) {
	// if right click, exit
    if (e && e.which === 3) return
    
    // remove class added on dropdownToggle
    $('.dropdown-backdrop').remove()
    
    $(".dropdown-toggle").each(function () {
      var $parent       = dropdownParent($(this))
      var relatedTarget = { relatedTarget: this }

      if (!$parent.hasClass('open')) return

      if (e && e.type == 'click' && /input|textarea/i.test(e.target.tagName) && $.contains($parent[0], e.target)) return

      $parent.removeClass('open')
    })
  };

 function dropdownToggle(e) {
	var $this = $(this)

    if ($this.is('.disabled, :disabled')) return

    var $parent  = dropdownParent($this)
    var isActive = $parent.hasClass('open')

    dropdownClearMenus()

    if (!isActive) {
      if ('ontouchstart' in document.documentElement && !$parent.closest('.navbar-nav').length) {
        // if mobile we use a backdrop because click events don't delegate
        $(document.createElement('div'))
          .addClass('dropdown-backdrop')
          .insertAfter($(this))
          .on('click', dropdownClearMenus)
      }

      var relatedTarget = { relatedTarget: this }

      if (e.isDefaultPrevented()) return

      // expand the <ul> on the dropdown
      $this
        .trigger('focus')
        .attr('aria-expanded', 'true')

      // set parent to open
      $parent.toggleClass('open')
    }

    return false
};
//***** Dropdown ends *****

$(document).ready(function() {
    setTimeout(ACC.global.removeGlobalErrorAlerts,8000);
});