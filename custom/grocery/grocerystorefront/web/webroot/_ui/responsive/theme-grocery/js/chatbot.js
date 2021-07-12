ACC.chatbot = {

    _autoload: [
        'sendQuery'
    ],

    sendQuery() {
        let case1 = "get FV002 and FV001";
        let case2 = "profile related changes";
        let case3 = "no activity msg";
        let case4 = "go to recipe and cart";
        let case5 = "add to cart";
        let case6 = "hi middleware web";
        let query = {
            query: case1,
            token: "token"
        };

        $(document).on("click", ".sendQueryBtn", (e) => {
            $.post("http://localhost:3001/hybris/query",
            query,
            function(data, textStatus, jqXHR)
            {
                console.log(data);
            }).fail(function(jqXHR, textStatus, errorThrown) {
                console.log('post', textStatus);
            });

            // fetch('http://localhost:3001/hybris/query', {
            //     method: 'POST',
            //     mode: 'cors', // no-cors, *cors, same-origin
            //     headers: {
            //         "Access-Control-Allow-Origin": "*",
            //         'Content-Type': 'application/json'
            //         // 'Content-Type': 'application/x-www-form-urlencoded',
            //     },
            //     body: JSON.stringify(data)
            // }).then(function (response) {
            //     console.log(response.json());
            // }).catch(function (err) {
            //     console.log(err);
            // });

            // fetch('http://localhost:3001/hybris', {
            //     method: 'GET',
            //     mode: 'cors', // no-cors, *cors, same-origin
            //     headers: {
            //         "Access-Control-Allow-Origin": "*",
            //         'Content-Type': 'application/json'
            //     }
            // }).then(function (response) {
            //     console.log(response);
            // }).catch(function (err) {
            //     console.log(err);
            // });

            

            // $.ajax({
            //     url: "http://localhost:3001/hybris/query",
            //     data: JSON.parse(data),
            //     // headers: {
            //     //     "Access-Control-Allow-Origin": "*",
            //     //     'Content-Type':'application/json'
            //     // },
            //     beforeSend: function (request) {
            //         request.setRequestHeader("Access-Control-Allow-Origin", "*");
            //         request.setRequestHeader('Content-Type', 'application/json');
            //     },
            //     // crossDomain: true,
            //     crossDomain: true,
            //     crossOrigin: true,
            //     method: "POST",
            //     // xhrFields: {
            //     //     withCredentials: true
            //     // },
            //     dataType: "json",
            //     success: function (data) {
            //         console.log('post', data);
            //     },
            //     error: function (err) {
            //         console.log('post err', err);
            //     },
            // });

            // $.ajax({
            //     url: "http://localhost:3001/hybris",
            //     // headers: {
            //     //     "Access-Control-Allow-Origin": "*",
            //     //     'Content-Type':'application/json'
            //     // },
            //     beforeSend: function (request) {
            //         request.setRequestHeader("Access-Control-Allow-Origin", "*");
            //         request.setRequestHeader('Content-Type', 'application/json');
            //     },
            //     crossOrigin: true,
            //     crossDomain: true,
            //     contentType: "application/json",
            //     xhrFields: {
            //         withCredentials: true
            //     },
            //     method: "GET",
            //     // dataType: "jsonp",
            //     success: function (res) {
            //         console.log('get', res);
            //     },
            //     error: function (err) {
            //         console.log('get err', err);
            //     },
            // });
        })
    }
}