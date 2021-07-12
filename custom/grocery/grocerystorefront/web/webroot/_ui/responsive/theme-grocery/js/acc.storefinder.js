ACC.storefinder = {
    _autoload: [
        ["init", $(".js-store-finder").length != 0],
        ["bindStoreChange", $(".js-store-finder").length != 0],
        ["bindSearch", $(".js-store-finder").length != 0],
        "bindPagination",
        "pageLoadMapView",
        "searchOnStoreSelect",
    ],

    storeData: "",
    storeId: "",
    coords: {},
    storeSearchData: {},
    locations: [],
    onStoreFinderPageLoad: false,

    createListItemHtml: function (data, id) {
        var $rdioEl = $("<input>")
            .addClass("js-store-finder-input")
            .attr("type", "radio")
            .attr("name", "storeNamePost")
            .attr("id", "store-filder-entry-" + id)
            .attr("data-id", id)
            .val(data.displayName);

        var $spanInfo = $("<span>")
            .addClass("entry__info")
            .append($("<span>").addClass("entry__name").text(data.displayName));

        var $spanDistance = $("<span>").addClass("entry__distance").append($("<span>").text(data.formattedDistance));

        var $label = $("<label>")
            .addClass("js-select-store-label")
            .attr("for", "store-filder-entry-" + id)
            .append($spanInfo)
            .append($spanDistance);

        return $("<li>").addClass("list__entry").append($rdioEl).append($label);
    },

    refreshNavigation: function () {
        data = ACC.storefinder.storeData;

        var $storeList = $(".js-store-finder-navigation-list");
		$storeList.empty();
		$(".store-finder-main").show();

        if (data) {
            for (i = 0; i < data["data"].length; i++) {
                $storeList.append(ACC.storefinder.createListItemHtml(data["data"][i], i));
            }

            // select the first store
            var firstInput = $(".js-store-finder-input")[0];
            $(firstInput).click();
        }

        var page = ACC.storefinder.storeSearchData.page;
        $(".js-store-finder-pager-item-from").text(page * 10 + 1);

        var to = page * 10 + 10 > ACC.storefinder.storeData.total ? ACC.storefinder.storeData.total : page * 10 + 10;
        $(".js-store-finder-pager-item-to").text(to);
        $(".js-store-finder-pager-item-all").text(ACC.storefinder.storeData.total);
        $(".js-store-finder").removeClass("show-store");
    },

    bindPagination: function () {
        $(document).on("click", ".js-store-finder-details-back", function (e) {
            e.preventDefault();

            $(".store-finder-navigation").removeClass("shownone-mobile");
            $(".store-finder-heading").removeClass("shownone");
            $(".store-finder-pagination").removeClass("shownone");
            $('.store-finder-details').addClass("shownone");
        });

        $(document).on("click", ".js-store-finder-pager-prev", function (e) {
            e.preventDefault();
            var page = ACC.storefinder.storeSearchData.page;
            ACC.storefinder.getStoreData(page - 1);
            checkStatus(page - 1);
        });

        $(document).on("click", ".js-store-finder-pager-next", function (e) {
            e.preventDefault();
            var page = ACC.storefinder.storeSearchData.page;
            ACC.storefinder.getStoreData(page + 1);
            checkStatus(page + 1);
        });

        function checkStatus(page) {
            if (page == 0) {
                $(".js-store-finder-pager-prev").attr("disabled", "disabled");
            } else {
                $(".js-store-finder-pager-prev").removeAttr("disabled");
            }

            if (page == Math.floor(ACC.storefinder.storeData.total / 10)) {
                $(".js-store-finder-pager-next").attr("disabled", "disabled");
            } else {
                $(".js-store-finder-pager-next").removeAttr("disabled");
            }
        }
    },

    bindStoreChange: function () {
        $(document).on("change", ".js-store-finder-input", function (e) {
            e.preventDefault();

            storeData = ACC.storefinder.storeData["data"];

            var storeId = $(this).data("id");

            var $ele = $(".js-store-finder-details");
            
            $ele.find(".js-store-image").empty();
            $ele.find(".js-store-openings").empty();
            $ele.find(".js-store-features").empty();
            $ele.find(".openings__title").hide();
            $ele.find(".js-store-openings").removeClass("has-content");
            $.each(storeData[storeId], function (key, value) {
                if (
                    key == "image" ||
                    key == "productcode" ||
                    key == "openings" ||
                    key == "specialOpenings" ||
                    key == "features"
                ) {
                    if (key == "image") {
                        $ele.find(".js-store-image").empty();
                        if (value != "") {
                            $ele.find(".js-store-image").append($("<img>").attr("src", value).attr("alt", ""));
                        }
                    }
                    if (key == "productcode") {
                        $ele.find(".js-store-productcode").val(value);
                    }
                    if (key == "openings") {
						var $oele = $ele.find(".js-store-" + key);
						$oele.removeClass("has-content");
                        $oele.empty();
                        if (value != "") {
							$.each(value, function (key2, value2) {
								var hours = $("<span>").addClass("store-hours");
								
                                hours.append($("<dt>").text(key2));
                                hours.append($("<dd>").text(value2));
								if (!$oele.hasClass("has-content")) {
									$oele.addClass("has-content");
								}
								
                                $oele.append(hours);
                            });
                        }
                    }
                    if (key == "specialOpenings") {
                    }
                    if (key == "features") {
						var $features = $ele.find(".js-store-" + key);
						$features.siblings(".openings__title").hide();
                        $features.empty();
                        if (value != "") {
							$.each(value, function (key2, value2) {
								$features.siblings(".openings__title").show();
                                $features.append($("<li>").text(value2));
                            });
                        }
                    }
                } else {
                    if (value != "") {
                        $ele.find(".js-store-" + key).text(value);
                    } else {
                        $ele.find(".js-store-" + key).empty();
                    }
                }
            });

            ACC.storefinder.storeId = storeData[storeId];
            ACC.storefinder.initGoogleMap();
        });

        $(document).on("click", ".js-select-store-label", function (e) {
            $(".store-finder-navigation").addClass("shownone-mobile");
            $(".store-finder-heading").addClass("shownone");
            $(".store-finder-pagination").addClass("shownone");
            $('.store-finder-details').removeClass("shownone");
        });

        $(document).on("click", ".js-back-to-storelist", function (e) {
            $(".js-store-finder").removeClass("show-store");
        });
    },

    initGoogleMap: function () {
        if ($(".js-store-finder-map").length > 0) {
            ACC.global.addGoogleMapsApi("ACC.storefinder.loadGoogleMap");
        }
    },

    loadGoogleMap: function () {
        storeInformation = ACC.storefinder.storeId;

        if ($(".js-store-finder-map").length > 0) {
            $(".js-store-finder-map").attr("id", "store-finder-map");
            var centerPoint = new google.maps.LatLng(storeInformation["latitude"], storeInformation["longitude"]);

            var mapOptions = {
                zoom: 13,
                zoomControl: true,
                panControl: true,
                streetViewControl: false,
                mapTypeId: google.maps.MapTypeId.ROADMAP,
                center: centerPoint,
            };

            var map = new google.maps.Map(document.getElementById("store-finder-map"), mapOptions);

            var marker = new google.maps.Marker({
                position: new google.maps.LatLng(storeInformation["latitude"], storeInformation["longitude"]),
                map: map,
                title: storeInformation["name"],
                icon: "https://maps.google.com/mapfiles/marker" + "A" + ".png",
            });
            var infowindow = new google.maps.InfoWindow({
                content: ACC.common.encodeHtml(storeInformation["name"]),
                disableAutoPan: true,
            });
            google.maps.event.addListener(marker, "click", function () {
                infowindow.open(map, marker);
            });
        }
    },

    bindSearch: function () {
        $(document).on("submit", "#storeFinderForm", function (e) {
            e.preventDefault();
            var q = $(".js-store-finder-search-input").val();

            if (q.length > 0) {
                ACC.storefinder.getInitStoreData(q);
            } else {
                if ($(".js-storefinder-alert").length < 1) {
                    var emptySearchMessage = $(".btn-primary").data("searchEmpty");
                    $(".js-store-finder").hide();
                    $("#storeFinder").before(
                        $("<div>")
                            .addClass("js-storefinder-alert alert alert-danger alert-dismissable getAccAlert")
                            .append(
                                $("<button>")
                                    .addClass("close closeAccAlert")
                                    .attr("type", "button")
                                    .attr("data-dismiss", "alert")
                                    .attr("aria-hidden", "true")
                                    .text("x")
                            )
                            .text(emptySearchMessage)
                    );
                    $(".closeAccAlert").on("click", function () {
                        $(this).parent(".getAccAlert").remove();
                    });
                }
            }
        });

        $(".js-store-finder").hide();
        $(document).on("click", "#findStoresNearMe", function (e) {
            e.preventDefault();
            ACC.storefinder.getInitStoreData(null, ACC.storefinder.coords.latitude, ACC.storefinder.coords.longitude);
        });
    },

    getStoreData: function (page) {
        ACC.storefinder.storeSearchData.page = page;
        url = $(".js-store-finder").data("url");
        $.ajax({
            url: url,
            data: ACC.storefinder.storeSearchData,
            type: "get",
            dataType: "json",
            success: function (response) {
                $(".search-empty").hide();
                ACC.storefinder.storeData = response;
                if (!ACC.storefinder.onStoreFinderPageLoad) {
                    ACC.storefinder.refreshNavigation();
                }

                ACC.storefinder.showMapAfterGettingData();
				if (ACC.storefinder.storeData.total < 10) {
					$(".js-store-finder-pager-next").css("disabled", "disabled");
                    $(".store-finder-pagination").hide();
                } else {
					$(".store-finder-pagination").show();
				}
            },
            error: function (error) {
                $(".search-empty").css("display", "flex");
                $("#map").css("display","none");
                $(".store-finder-heading").removeClass("on-load-height-class");
				$(".store-finder-main").hide();
            },
        });
    },

    getInitStoreData: function (q, latitude, longitude) {
        //$(".alert").remove();
        data = {
            q: "",
            page: 0,
        };
        if (q != null) {
            data.q = q;
        }

        if (latitude != null) {
            data.latitude = latitude;
        }

        if (longitude != null) {
            data.longitude = longitude;
        }

        ACC.storefinder.storeSearchData = data;
        ACC.storefinder.getStoreData(data.page);
        if (!ACC.storefinder.onStoreFinderPageLoad) {
            $(".store-finder-heading").removeClass("on-load-height-class");
            $("#map").hide();
            $(".js-store-finder").show();
            $(".js-store-finder-pager-prev").attr("disabled", "disabled");
            $(".js-store-finder-pager-next").removeAttr("disabled");
        }
    },

    init: function () {
        $("#findStoresNearMe").attr("disabled", "disabled");
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(
                function (position) {
                    ACC.storefinder.coords = position.coords;
                    $("#findStoresNearMe").removeAttr("disabled");
                },
                function (error) {
                    console.log(
                        "An error occurred... The error code and message are: " + error.code + "/" + error.message
                    ); // NOSONAR
                }
            );
        }
    },

    pageLoadMapView: function () {
        $(document).ready(function () {
            $("#map").show();
            $(".store-finder-heading").addClass("on-load-height-class");
            ACC.storefinder.onStoreFinderPageLoad = true;
            ACC.storefinder.getInitStoreData(null, ACC.storefinder.coords.latitude, ACC.storefinder.coords.longitude);
        });
    },

    showMapAfterGettingData: function () {
        ACC.storefinder.onStoreFinderPageLoad = false;
        for (let store of ACC.storefinder.storeData["data"]) {
            ACC.storefinder.locations.push([store.displayName, store.latitude, store.longitude]);
        }

        var cmap = new google.maps.Map(document.getElementById("map"), {
            zoom: 4,
            center: new google.maps.LatLng(
                ACC.storefinder.storeData.countryLatitude,
                ACC.storefinder.storeData.countryLongitude
            ),
            mapTypeId: google.maps.MapTypeId.ROADMAP,
        });

        var infowindow = new google.maps.InfoWindow();

        var marker, i;

        for (i = 0; i < ACC.storefinder.locations.length; i++) {
            marker = new google.maps.Marker({
                position: new google.maps.LatLng(ACC.storefinder.locations[i][1], ACC.storefinder.locations[i][2]),
                map: cmap,
            });

            google.maps.event.addListener(
                marker,
                "mouseover",
                (function (marker, i) {
                    return function () {
                        infowindow.setContent(`<div class="mark">${ACC.storefinder.locations[i][0]}</div>`);
                        infowindow.open(cmap, marker);
                    };
                })(marker, i)
            );
        }
    },

    searchOnStoreSelect: function () {
        $(document).on("click", ".mark", function (e) {
            e.preventDefault();
            $("#storelocator-query").val($(".mark").text());
            $(".storefinder-search-button").click();
        });
    },

    showErrorMessage: function () {
        $(document).ready(function () {});
    },
};
