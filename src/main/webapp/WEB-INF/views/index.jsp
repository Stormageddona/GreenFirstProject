<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
<h1>Login Page</h1>
<button onclick="googleAuth()">Login with Google</button>
<button onclick="kakaoAuth()">Login with Kakao</button>
<button onclick="naverAuth()">Login with Naver</button>

<script>
    function googleAuth() {
        var redirectUri = 'http://localhost:8080/api/auth/google';

        var encodedRedirectUri = encodeURIComponent(redirectUri);
        // URL 구성 부분 분리
        var baseUrl = "https://accounts.google.com/o/oauth2/v2/auth";
        var responseType = "code";
        var clientId = "873909366016-aj06hqodpd0co5f520qo6knns44j6i4b.apps.googleusercontent.com";
        var scope = "email";
        var link = baseUrl + "?response_type=" + responseType + "&client_id=" + clientId + "&redirect_uri=" + encodedRedirectUri + "&scope=" + scope;

        // 이동
        window.open(link)
    }

    function kakaoAuth() {
        var redirectUri = 'http://localhost:8080/api/auth/kakao';

        var encodedRedirectUri = encodeURIComponent(redirectUri);

        // URL 구성 부분 분리
        var baseUrl = "https://kauth.kakao.com/oauth/authorize";
        var responseType = "code";
        var clientId = "e6860ebb151f4e90a443a315755febab";
        // var scope = "email";

        var link = baseUrl + "?response_type=" + responseType + "&client_id=" + clientId + "&redirect_uri=" + encodedRedirectUri ;


        // 이동
        window.open(link)
    }
    function naverAuth() {
        var redirectUri = 'http://localhost:8080/api/auth/naver';

        var encodedRedirectUri = encodeURIComponent(redirectUri);

        // URL 구성 부분 분리
        var baseUrl = "https://nid.naver.com/oauth2.0/authorize";
        var responseType = "code";
        var clientId = "o0cYXLLCqN1s_eXjOmc7";
        var state = "test";

        var link = baseUrl + "?response_type=" + responseType + "&client_id=" + clientId + "&redirect_uri=" + encodedRedirectUri + "&state=" + state;


        // 이동
        window.open(link,'login','width=500,height=500,resizeable=yes')
    }
    // 메시지 수신 이벤트 핸들러
    window.addEventListener('message', function(event) {
        if (event.origin !== window.location.origin) {
            // 신뢰할 수 없는 도메인에서 보낸 메시지 필터링
            return;
        }
        console.log('자식 창에서 받은 메시지:', event.data);
    });
</script>
</body>
</html>
