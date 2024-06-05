//package com.green.greenfirstproject.user.socialservice;
//
//
//import com.dreamidea.jobworld.model.UserInfo;
//import com.dreamidea.jobworld.repository.UserInfoRepository;
//import com.green.greenfirstproject.user.UserMapper;
//import com.green.greenfirstproject.user.UserService;
//import com.nimbusds.jwt.ReadOnlyJWTClaimsSet;
//import com.nimbusds.jwt.SignedJWT;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import lombok.RequiredArgsConstructor;
//import org.apache.http.HttpEntity;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.util.EntityUtils;
//import org.bouncycastle.util.io.pem.PemObject;
//import org.bouncycastle.util.io.pem.PemReader;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//import org.springframework.stereotype.Service;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.FileReader;
//import java.io.IOException;
//import java.security.KeyFactory;
//import java.security.PrivateKey;
//import java.security.spec.PKCS8EncodedKeySpec;
//import java.util.*;
//
//
//@Service
//@RequiredArgsConstructor
//public class LoginServiceForApple {
//
//	private final UserMapper mapper  ;
//
//	private final UserService userService  ;
//
//    private static final String CLIENT_ID = "com.metajobworld" ;
//    private static final String TEAM_ID = "5735TDTU36" ;
//    private static final String KEY_ID = "JF26XA7U7X";
//    private static final String KEY_PATH = "static/AuthKey_JF26XA7U7X.p8";
//	public static final String AUTH_URL = "https://appleid.apple.com";
//
//    public static String REDIRECT_URL = "https://metajobworld.kr/auth/client-apple-login" ;
//
//
//	public String AppleLoginPage(HttpServletRequest request) throws Exception {
//
//		String redirect_uri = request.getScheme() + "://" + request.getServerName();
//		if (request.getServerPort() != 80 && request.getServerPort() != 443) { // http, https 일때는 포트 번호가 사용되지 않음
//			redirect_uri = redirect_uri + ":" + request.getServerPort();
//		}
//		redirect_uri = redirect_uri + REDIRECT_URL; // 로그인 후 처리할 controller mapping
//
//		String reqUrl = AUTH_URL + "/auth/authorize?client_id=" + CLIENT_ID + "&redirect_uri=" + REDIRECT_URL
//				+ "&response_type=code id_token&scope=name email&response_mode=form_post";
//
//		return reqUrl;
//	}
//
//	//
//	public String ClientAppleLoginPage(HttpServletRequest request,String key,String type) throws Exception {
//
//		String redirect_uri = request.getScheme() + "://" + request.getServerName();
//		if (request.getServerPort() != 80 && request.getServerPort() != 443) { // http, https 일때는 포트 번호가 사용되지 않음
//			redirect_uri = redirect_uri + ":" + request.getServerPort();
//		}
//		redirect_uri = redirect_uri + REDIRECT_URL; // 로그인 후 처리할 controller mapping
//
//		String reqUrl = AUTH_URL + "/auth/authorize?client_id=" + CLIENT_ID + "&redirect_uri=" + REDIRECT_URL
//				+ "&response_type=code id_token&scope=name email&response_mode=form_post&state="+key+"~"+type;
//
//		return reqUrl;
//	}
//
//	// 애플 로그인 화면 호출
//	public UserInfo ClientAppleOauth(HttpServletRequest request, String code, HttpServletResponse response) throws Exception {
//		// 애플로그인 취소시 로그인페이지로 이동
//		if (code == null) {
//			return null ;
//		}
//
//		String client_id = CLIENT_ID;
//		String client_secret = createClientSecret(TEAM_ID, CLIENT_ID, KEY_ID, KEY_PATH, AUTH_URL);
//
//		// 토큰 검증 및 발급
//		String reqUrl = AUTH_URL + "/auth/token";
//		Map<String, String> tokenRequest = new HashMap<>();
//		tokenRequest.put("client_id", client_id);
//		tokenRequest.put("client_secret", client_secret);
//		tokenRequest.put("code", code);
//		tokenRequest.put("grant_type", "authorization_code");
//		String apiResponse = doPost(reqUrl, tokenRequest);
//
//		JSONObject tokenResponse = new JSONObject(apiResponse);
//		// System.out.println(tokenResponse);
//		String token = tokenResponse.getString("id_token");
//		JSONObject appleInfo = decodeFromIdToken(token);
//
//		String snsId = token;
//		String sub = appleInfo.getString("sub");
//		// System.out.println(appleInfo);
//        snsId = "apple_" + sub ;
//        UserInfo user = userInfoRepository.findByEmailAndJoinType(snsId,6).orElse(null) ;
//
//        if (user == null) user = userService.socialJoinUser(snsId,6) ;
//
//		return user ;
//		// UserInfo searchUserinfo = userInfoRepository.getAppleUserBySnsId(sub).orElse(null);
//		// int step = Integer.parseInt(request.getSession().getAttribute("step").toString());
//
//		// if (searchUserinfo == null && step == 0) {
//		// 	return "/auth/login-term-of-use/apple-client";
//		// }
//
//		// if (searchUserinfo == null) {
//		// 	String lastName = userInfoRepository.getAppleCount().orElse(null);
//		// 	int countNumber = lastName != null ? Integer.parseInt(lastName.replace("apple_", "")) : 0;
//		// 	String count = String.valueOf(countNumber + 1);
//		// 	String id = count;
//		// 	if (count.length() < 4) {
//		// 		for (int i = 3; i > count.length(); i--) {
//		// 			id = "0" + id;
//		// 		}
//		// 	}
//		// 	String username = "apple_" + id;
//		// 	searchUserinfo = new UserInfo();
//		// 	searchUserinfo.setSnsId(sub);
//		// 	searchUserinfo.setSns("APPLE");
//		// 	searchUserinfo.setUsername(username);
//		// 	String salt = BCrypt.gensalt();
//		// 	String newPassword = BCrypt.hashpw("qefhtsdf234f!sdagsga", salt);
//		// 	// String newPassword = encoder.encode("qefhtsdf234f!sdagsga");
//		// 	String uuid = UUID.randomUUID().toString();
//		// 	String uuidtoken = UUID.randomUUID().toString();
//		// 	searchUserinfo.setPassword(newPassword);
//		// 	searchUserinfo.setUuid(uuid);
//		// 	searchUserinfo.setToken(uuidtoken);
//		// 	searchUserinfo.setSalt(salt);
//		// 	searchUserinfo.setRole("ROLE_USER");
//		// 	searchUserinfo.setGrade("일반 이용자");
//		// 	userInfoRepository.save(searchUserinfo);
//		// }
//
//		// // 시큐리티 수동 로그인
//		// PrincipalDetail principal = new PrincipalDetail(searchUserinfo);
//		// Authentication authentication = new UsernamePasswordAuthenticationToken(principal, principal.getPassword(), principal.getAuthorities());
//		// SecurityContext securityContext = SecurityContextHolder.getContext();
//		// securityContext.setAuthentication(authentication);
//		// request.getSession().setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
//
//		// return "/auth/client?token=" + searchUserinfo.getToken();
//	}
//
//	public String createClientSecret(String teamId, String clientId, String keyId, String keyPath, String authUrl) throws Exception {
//
//		Date now = new Date();
//		byte[] privateKey = readPrivateKey(keyPath);
//		KeyFactory kf = KeyFactory.getInstance("EC"); // or "EC" or whatever
//		PrivateKey privateKey2 = kf.generatePrivate(new PKCS8EncodedKeySpec(privateKey));
////        PublicKey publicKey = kf.generatePublic(new X509EncodedKeySpec(publicKeyBytes));
//
//		String jwts = Jwts.builder().setSubject(clientId).setIssuedAt(now).setExpiration(new Date(now.getTime() + 3600000)).setAudience(authUrl)
//				.setIssuer(teamId).signWith(SignatureAlgorithm.ES256, privateKey2).compact();
//
//		return jwts;
//	}
//
//	private byte[] readPrivateKey(String keyPath) {
//
//		Resource resource = new ClassPathResource(keyPath);
//		byte[] content = null;
//		try (FileReader keyReader = new FileReader(resource.getFile());
//				PemReader pemReader = new PemReader(keyReader)) {
//			{
//				PemObject pemObject = pemReader.readPemObject();
//				content = pemObject.getContent();
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		return content;
//	}
//
//	public String doPost(String url, Map<String, String> param) {
//		String result = null;
//		CloseableHttpClient httpclient = null;
//		CloseableHttpResponse response = null;
//		Integer statusCode = null;
//		try {
//			httpclient = HttpClients.createDefault();
//			HttpPost httpPost = new HttpPost(url);
//			httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
//			List<NameValuePair> nvps = new ArrayList<>();
//			Set<Map.Entry<String, String>> entrySet = param.entrySet();
//			for (Map.Entry<String, String> entry : entrySet) {
//				String fieldName = entry.getKey();
//				String fieldValue = entry.getValue();
//				nvps.add(new BasicNameValuePair(fieldName, fieldValue));
//			}
//			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nvps);
//			httpPost.setEntity(formEntity);
//			response = httpclient.execute(httpPost);
//			statusCode = response.getStatusLine().getStatusCode();
//			HttpEntity entity = response.getEntity();
//			result = EntityUtils.toString(entity, "UTF-8");
//
//			if (statusCode != 200) {
//				System.out.println("애러");
//			}
//			EntityUtils.consume(entity);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (response != null) {
//					response.close();
//				}
//				if (httpclient != null) {
//					httpclient.close();
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return result;
//	}
//
//	public JSONObject decodeFromIdToken(String id_token) {
//
//		try {
//			SignedJWT signedJWT = SignedJWT.parse(id_token);
//			ReadOnlyJWTClaimsSet getPayload = signedJWT.getJWTClaimsSet();
//			String appleInfo = getPayload.toJSONObject().toJSONString();
//			JSONObject payload = new JSONObject(appleInfo);
//
//			if (payload != null) {
//				return payload;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return null;
//	}
//}
