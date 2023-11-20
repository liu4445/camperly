package org.project.backend.user.service;

import java.sql.SQLException;
import java.util.UUID;

import org.project.backend.user.dao.UserDao;
import org.project.backend.user.dto.OauthLoginRequest;
import org.project.backend.user.dto.OauthLoginResponse;
import org.project.backend.user.dto.UserDto;
import org.project.backend.util.JwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final OauthServiceContext oauthServiceContext;
	private final JwtUtil jwtUtil;
	private final UserDao userDao;

	@Transactional
	public OauthLoginResponse oauthLogin(OauthLoginRequest oauthLoginRequest) throws SQLException {
		// 로그인 요청
		String loginId = oauthServiceContext.getUserInfoByOauthToken(oauthLoginRequest);
		UserDto userDto = userDao.findByLoginIdAndOauthLoginType(loginId, oauthLoginRequest.getOauthLoginType());
		long userId;

		// 회원가입 되어 있지 않으면 회원가입
		if (userDto == null) {
			userId = register(
					UserDto.builder()
							.loginId(loginId)
							.oauthLoginType(oauthLoginRequest.getOauthLoginType())
							.name(UUID.randomUUID().toString())
							.build()

			);
		} else {
			userId = userDto.getUserId();
		}

		// 토큰 반환
		return new OauthLoginResponse(jwtUtil.generate(userId));
	}

	@Transactional
	public long register(UserDto userDto) throws SQLException {
		return userDao.save(userDto);
	}
}
