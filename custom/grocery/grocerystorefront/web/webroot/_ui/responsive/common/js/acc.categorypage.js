/**
 * author pappu
 */
ACC.categorypage = {

    _autoload : [
        "bindShowMoreButton",
        "bindFacetExpansion",
        "bindFilterFacetButton",
        "bindFacetApplyButton",
        "bindOtherSearchPageTabs"
    ],

    bindShowMoreButton : function() {
        $(window).scroll(function() {

            if ($(".product-item").length == 0 || $("#show-more").length == 0) {
                return;
            }

            if ($(document).height() -$("footer").height()<= $(window).scrollTop() + $(window).height()) {

                if ($(window).data('ajax_in_progress') === true)
                    return;
                $(window).data('ajax_in_progress', true);
               // var listingDiv = $(".btn-show-more-product-listing").closest(".product-list-right-component").find(".product__list--item").last();
                var listingDiv = $("li.product__list--item:last-child");
               // var seeMoreDiv = $(".btn-show-more-product-listing").closest(".show-more-products-wrapper");-->commented in main file contentListerPgination
                $form = $(".btn-show-more-product-listing").closest(".show-more-products-wrapper").find("form");
                $serchData= $('.btn-show-more-product-listing show-more-products-wrapper form').serialize();

                var showMoreUrl = ACC.config.encodedContextPath + $form.attr("action");
                if (!(ACC.common.stringEndsWith(showMoreUrl, "results-display"))) {
                    showMoreUrl = showMoreUrl + "/results-display";
                }

                $.ajax({
                    url : showMoreUrl,
                    data : $form.serialize(),
                    type : "GET",
                    success : function(data) {
                        $(window).data('ajax_in_progress', false);
                        console.log("data recieved" + data);
                        var listingReplacementDiv = $(data).find(".product-listing-section").html();
                        $(listingReplacementDiv).insertAfter($(listingDiv));
                        seeMoreDiv.html($(data).find(".see-more-section").html());
                    },
                    error : function() {
                        ACC.utils.appendGlobalMessage(ACC.globalMessageTypes.ERROR_MESSAGES_HOLDER, ACC.messages.showMoreProductsError);
                    }
                });
            }
        });
    },

    bindFacetExpansion : function() {
        var noFacetSelected = true;
        $("#facetAccordion").find(".panel.noScroll").each(function() {
            var facetSelected = false;
            $(this).find(".js-facet-checkbox").each(function() {
                 if ($(this).prop("checked")) {
                     facetSelected = true;
                 }
            })
            var facet = $(this).children().find(".collapse-button");
            var facetPane = $(this).find(".panel-collapse")
            if (facetSelected && facet.hasClass("collapsed")) {
                noFacetSelected = false;
                facet.removeClass("collapsed");
                facetPane.addClass("in");
                facetPane.css("height","");
                }
        })
        if(noFacetSelected) {
            $("#facetAccordion").find(".panel.noScroll").find('.collapsed').first().trigger('click');
        }
    },

    bindFilterFacetButton : function() {
        $(document).on("click", ".filter-facet-button", function(e) {
            e.preventDefault();
            if (!$(".new-plp .pagination-wrapper .product__facet").hasClass("active")) {
                $(".new-plp .filter-facet-button").addClass("active");
                $(".new-plp .pagination-wrapper .product__facet").addClass("active");
            } else {
                $(".new-plp .pagination-wrapper .product__facet").removeClass("active");
                $(".new-plp .filter-facet-button").removeClass("active");
            }
        });
    },

    bindFacetApplyButton : function() {
        $(document).on("click", ".facet-button-apply", function() {
            var facetQuery = $(this).closest(".js-facet").data("currentQuery");

            $(this).closest(".panel-body").find(".js-facet-mobile-checkbox:checked").each(function() {
                var enodedQuery = ":" + $(this).data("facetCode") + ":" + encodeURIComponent($(this).data("facetValueCode"));
                if (!facetQuery.includes(enodedQuery)) {
                    facetQuery = facetQuery + enodedQuery;
                }
            });

            $(this).closest(".panel-body").find(".js-facet-mobile-checkbox:not(:checked)").each(function() {
                var enodedQuery = ":" + $(this).data("facetCode") + ":" + encodeURIComponent($(this).data("facetValueCode"));
                if (facetQuery.includes(enodedQuery)) {
                    facetQuery = facetQuery.replace(enodedQuery, "");
                }
            });

            $form = $(this).closest(".mob-product-facet").find("#mobile-facet-apply-form");
            $form.find("input").val(facetQuery);
            $form.submit();
        });
    },

    bindOtherSearchPageTabs : function() {
        $('.content-button').click(function(e) {
            e.preventDefault();
            $('.content-button').add('active');
            $('.product-button').remove('active');
            $('.product-search').hide();
            $('.content-search').show();
        });
        $('.product-button').click(function(e) {
            e.preventDefault();
            $('.product-button').add('active');
            $('.content-button').remove('active');
            $('.content-search').hide();
            $('.product-search').show();
        });
        /*
         * //fillters checkbox $(".facet__list__label").click(function(e) {
         * e.preventDefault(); if ($(this).prev().prop("checked") == false) {
         * $(this).prev().prop("checked", true); } else {
         * $(this).prev().prop("checked", false); } });
         */
    }
}