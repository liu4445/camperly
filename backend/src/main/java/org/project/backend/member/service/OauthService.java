package org.project.backend.member.service;

import org.project.backend.constant.OauthLoginType;

public interface OauthService {

	OauthLoginType getOauthLoginType();

	String getUserInfoByOauthToken(String oauthToken);
}
