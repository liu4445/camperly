package org.project.backend.member.service;

import static org.project.backend.constant.OauthConstants.*;

import org.project.backend.constant.OauthLoginType;
import org.project.backend.environment.GoogleOauthProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.JsonParser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class GoogleService implements OauthService {

	private final GoogleOauthProperties googleOauthProperties;

	@Override
	public OauthLoginType getOauthLoginType() {
		return OauthLoginType.GOOGLE;
	}

	@Override
	public String getUserInfoByOauthToken(String code) {
		String kakaoAccessToken = requestAccessToken(code);
		return requestUserInfo(kakaoAccessToken);
	}

	private String requestAccessToken(String oauthToken) {
		String params = UriComponentsBuilder.fromUriString(googleOauthProperties.getTokenRequestUrl())
				.queryParam(CODE.key, oauthToken)
				.queryParam(CLIENT_ID.key, googleOauthProperties.getClientId())
				.queryParam(CLIENT_SECRET.key, googleOauthProperties.getClientSecret())
				.queryParam(REDIRECT_URI.key, googleOauthProperties.getRedirectUrl())
				.queryParam(GRANT_TYPE.key, getFixGrantType())
				.toUriString();
		log.info("구글 Oauth AccessToken 요청 : {}", params);

		ResponseEntity<String> exchange = new RestTemplate().exchange(
				params,
				HttpMethod.POST,
				null,
				String.class
		);
		log.info("구글 Oauth AccessToken 응답 : {}", exchange);

		return JsonParser.parseString(exchange.getBody())
				.getAsJsonObject()
				.get("access_token")
				.getAsString();
	}

	private String requestUserInfo(String token) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(AUTHORIZATION.key, getFixPrefixJwt() + token);
		log.info("구글 Oauth UserInfo 요청 : {}", httpHeaders);

		ResponseEntity<String> exchange = new RestTemplate().exchange(
				googleOauthProperties.getUserInfoRequestUrl(),
				HttpMethod.GET,
				new HttpEntity(httpHeaders),
				String.class
		);
		log.info("구글 Oauth UserInfo 응답 : {}", exchange);

		return JsonParser.parseString(exchange.getBody())
				.getAsJsonObject()
				.get("sub")
				.getAsString();
	}
}
