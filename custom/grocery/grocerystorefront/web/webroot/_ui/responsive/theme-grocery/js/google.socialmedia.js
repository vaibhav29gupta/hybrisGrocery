var googleUser = {};
var startApp = function () {
    gapi.load('auth2', function () {
        // Retrieve the singleton for the GoogleAuth library and set up the client.
        auth2 = gapi.auth2.init({
            client_id: $("#googleClientId").val(),
            cookiepolicy: 'single_host_origin',
            // Request scopes in addition to 'profile' and 'email'
            //scope: 'additional_scope'
        });
        attachSignin(document.getElementById('google'));
    });
};

function attachSignin(element) {
    auth2.attachClickHandler(element, {},
        function (googleUser) {
            var profile = googleUser.getBasicProfile();
            console.log('Google ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
            console.log('Google Name: ' + profile.getName());
            console.log('Google Image URL: ' + profile.getImageUrl());
            console.log('Google Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
            var id_token = googleUser.getAuthResponse().id_token;
            $.post(ACC.config.encodedContextPath + "/login/google", {
                id_token: id_token
            }).done(function (resultData) {
                window.location = ACC.config.encodedContextPath + resultData;
            });
        }, function (error) {
            console.log(JSON.stringify(error, undefined, 2));
        });
}

startApp();
