package org.project.backend.user.service;

import java.util.List;

import org.project.backend.constant.OauthLoginType;
import org.project.backend.user.dto.OauthLoginRequest;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OauthServiceContext {

	private final List<OauthService> oauthServices;

	public Long getUserInfoByOauthToken(OauthLoginRequest oauthLoginRequest) {
		OauthService findService = oauthServices.stream()
				.filter(oauthService -> oauthService.getOauthLoginType().equals(oauthService.getOauthLoginType()))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException());
		return findService.getUserInfoByOauthToken(oauthLoginRequest.getOauthToken());
	}
}
