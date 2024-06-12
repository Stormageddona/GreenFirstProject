package com.green.greenfirstproject.user.socialservice;


import com.green.greenfirstproject.user.UserMapper;
import com.green.greenfirstproject.user.UserService;
import com.green.greenfirstproject.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class LoginServiceForNaver {

    private final UserMapper mapper  ;

    private final UserService userService  ;
    private final WebClient webClient = WebClient.builder().baseUrl("https://openapi.naver.com").build();;

    @Value("${naver.client.id}")
    private String CLIENT_ID  ;

    @Value("${naver.client.secret}")
    private String KEY_ID ;

    @Value("${naver.redirect.uri}")
    public static String REDIRECT_URL ;


    public User clientNaverOauth(String code,String state) throws Exception {

        if (code == null) return null;

        String snsId = getToken(code,state);
        System.out.println(snsId);

        if (snsId == null) return null;

        snsId = "naver_" + snsId ;
        User user = mapper.selectUserById(snsId) ;

        if (user == null) user = userService.socialUserJoin(snsId,3); ;

        return user ;

    }

    public String getToken(String code, String state) throws Exception {
        // 인가코드로 토큰받기
        System.out.println(CLIENT_ID);
        String host = "https://nid.naver.com/oauth2.0/token";
        URL url = new URL(host);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        String token = "";
        try
        {
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true); // 데이터 기록 알려주기
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()));
            String sb = "grant_type=authorization_code" +
                    "&client_id=" + CLIENT_ID + // 본인이 발급받은 key
                    "&client_secret=" + KEY_ID + // 본인이 발급받은 key
                    "&state=" + state +
                    "&redirect_uri=" + REDIRECT_URL + // 본인이 설정해 놓은 경로
                    "&code=" + code;

            bw.write(sb);
            bw.flush();


            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));
            String line = "";
            StringBuilder result = new StringBuilder();
            while ((line = br.readLine()) != null) {
                result.append(line);
            }
            // //System.out.println("result = " + result);
            // json parsing
            JSONObject elem = new JSONObject(result.toString()) ;

            token = elem.get("access_token").toString();
            br.close();
            bw.close();

        }
        catch (Exception e)
        {
//            e.printStackTrace();
            return null ;
        }

        return GetGoogleAuth(token);
    }

    public String GetGoogleAuth(String access_Token) throws Exception
    {
        // Use the access token to get user info
        Mono<Map<String, Object>> userInfoMono = webClient.get()
                .uri("/v1/nid/me")
                .header("Authorization", "Bearer " + access_Token)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {});

        Map<String, Object> userInfo = userInfoMono.block();

        System.out.println(userInfo);
        JSONObject elem = new JSONObject(userInfo.get("response").toString());
        return elem.get("id").toString();

    }

}
