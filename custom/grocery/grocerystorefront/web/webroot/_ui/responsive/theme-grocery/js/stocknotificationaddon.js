ACC.stocknotification = {
  _autoload: ["bindStockNotification", "enableNotificationButton"],
  bindStockNotification: function () {
    $(".js-add-to-cart.outOfStock").click(function (e) {
      var title = ACC.common.encodeHtml($(this).attr("data-box-title"));
      var productCode = $(this).closest(".product-actions").data("productcode");
      if (! productCode ) {
        var productCode = $(this).closest(".addtocart-component").data("productcode");
      }

      var url = encodeURI(ACC.config.encodedContextPath + "/my-account/my-stocknotification/open/" + productCode + "?channel=pdp");

      ACC.common.checkAuthenticationStatusBeforeAction(function () {
        ACC.colorbox.open(title, {
          href: url,
          maxWidth: "100%",
          width: "550px",
          initialWidth: "550px"
        });
      });
    });
  },
  enableNotificationButton: function () {
    $(".js-add-to-cart.outOfStock").removeAttr("disabled");
  }
};