package org.project.backend.user.service;

import org.project.backend.constant.OauthLoginType;

public interface OauthService {

	OauthLoginType getOauthLoginType();

	Long getUserInfoByOauthToken(String oauthToken);
}
