package org.project.backend.member.service;

import java.util.List;

import org.project.backend.constant.OauthLoginType;
import org.project.backend.member.dto.OauthLoginRequest;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OauthServiceContext {

	private final List<OauthService> oauthServices;

	public String getUserInfoByOauthToken(OauthLoginType oauthLoginType, String code) {
		OauthService findService = oauthServices.stream()
				.filter(oauthService -> oauthService.getOauthLoginType().equals(oauthLoginType))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException());
		return findService.getUserInfoByOauthToken(code);
	}
}
