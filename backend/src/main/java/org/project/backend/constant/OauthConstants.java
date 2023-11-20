package org.project.backend.constant;

public enum OauthConstants {
	CLIENT_ID("client_id"),
	CLIENT_SECRET("client_secret"),
	CODE("code"),
	GRANT_TYPE("grant_type"),
	REDIRECT_URI("redirect_uri"),
	ID_TOKEN("id_token"),
	AUTHORIZATION("Authorization");

	public final String key;

	OauthConstants(String key) {
		this.key = key;
	}

	public static String getFixGrantType() {
		return "authorization_code";
	}

	public static String getFixPrefixJwt() {
		return "Bearer ";
	}
}
