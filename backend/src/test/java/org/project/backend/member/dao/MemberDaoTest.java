package org.project.backend.member.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
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

	@Test
	@DisplayName("로그인 ID와 OauthLoginType을 통한 조회를 테스트한다")
	public void 로그인_ID와_OauthLoginType을_통한_조회를_테스트한다() {
		// given
		while (true) {

		}

		// when

		// then
	}
}