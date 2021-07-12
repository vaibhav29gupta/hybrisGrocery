ACC.close = {
    _autoload: [
        ["bindCloseAccountModalButtons", $(".js-close-account-popup-button").length != 0],
        ["bindCloseAccountButton", $(".js-close-account-popup-button").length != 0]
    ],

    bindCloseAccountModalButtons: function () {
        $('.js-close-account-popup-button').click(function (event) {
            event.preventDefault();
            $(".confirm_account_removal").show();
//            var popupTitle = $('.js-close-account-popup-button').data("popupTitle");
//            var popupTitleHtml = ACC.common.encodeHtml(popupTitle);
//            ACC.colorbox.open(popupTitleHtml, {
//                inline: true,
//                href: "#popup_confirm_account_removal",
//                className: "js-close-account-popup",
//                width: '500px',
//                onComplete: function () {
//                    $(this).colorbox.resize();
//                }
//            })
        });
    },

    bindCloseAccountButton: function () {
        $(document).on("click", '.js-close-account-action', function (event) {
            event.preventDefault();
            var url = ACC.config.encodedContextPath + '/my-account/close-account';
            $.ajax({
                url: url,
                type: 'POST',
                success: function (response) {
//                    ACC.colorbox.close();
                    var url = ACC.config.encodedContextPath + '/logout/?site=spruce&closeAcc=true'
                    window.location.replace(url);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log("Failed to close account. Error: [" + errorThrown + "]");   // NOSONAR
                    window.location.reload();
                }
            });
        });
        $(document).on("click", '.close-close-account', function (event) {
        	 $(".confirm_account_removal").hide();
        });
    }
};
