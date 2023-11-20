package org.project.backend.member.dao;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.project.backend.constant.OauthLoginType;
import org.project.backend.member.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MemberDaoTest {

	private final MemberDao memberDao;

	@Autowired
	public MemberDaoTest(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	@ParameterizedTest
	@EnumSource(value = OauthLoginType.class, names = {"KAKAO", "GOOGLE"})
	@DisplayName("로그인 ID와 OauthLoginType을 통한 조회를 테스트한다")
	public void 로그인_ID와_OauthLoginType을_통한_조회를_테스트한다(OauthLoginType oauthLoginType) throws Exception {
		// when
		MemberDto findMember = memberDao.findByLoginIdAndOauthLoginType(
				"test",
				oauthLoginType
		);

		// then
		assertThat(findMember).isNotNull();
	}

	@Test
	@DisplayName("사용자 저장을 테스트한다")
	public void 사용자_저장을_테스트한다() throws Exception {
		// given
		MemberDto member = MemberDto.builder()
				.loginId("test")
				.oauthLoginType(OauthLoginType.KAKAO)
				.name("test")
				.build();

		// when
		long id = memberDao.save(member);

		// then
		assertThat(id).isNotNull();
	}
}