package org.project.backend.member.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.project.backend.util.JwtUtil;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

	@InjectMocks
	private MemberService memberService;

	@Mock
	private OauthServiceContext oauthServiceContext;

	@Mock
	private JwtUtil jwtUtil;

	// @
	// private MemberDao userDao;
}