package org.project.backend.member.service;

import java.sql.SQLException;
import java.util.UUID;

import org.project.backend.constant.OauthLoginType;
import org.project.backend.member.dao.MemberDao;
import org.project.backend.member.dto.MemberDto;
import org.project.backend.member.dto.OauthLoginRequest;
import org.project.backend.member.dto.OauthLoginResponse;
import org.project.backend.util.JwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final OauthServiceContext oauthServiceContext;
	private final JwtUtil jwtUtil;
	private final MemberDao memberDao;

	@Transactional
	public OauthLoginResponse oauthLogin(OauthLoginType oauthLoginType, String code) throws SQLException {
		// 로그인 요청
		String loginId = oauthServiceContext.getUserInfoByOauthToken(oauthLoginType, code);
		MemberDto memberDto = memberDao.findByLoginIdAndOauthLoginType(loginId, oauthLoginType);
		long userId;

		// 회원가입 되어 있지 않으면 회원가입
		if (memberDto == null) {
			userId = register(
					MemberDto.builder()
							.loginId(loginId)
							.oauthLoginType(oauthLoginType)
							.name(UUID.randomUUID().toString())
							.build()

			);
		} else {
			userId = memberDto.getMemberId();
		}

		// 토큰 반환
		return new OauthLoginResponse(jwtUtil.generate(userId));
	}

	@Transactional
	public long register(MemberDto memberDto) throws SQLException {
		return memberDao.save(memberDto);
	}
}
