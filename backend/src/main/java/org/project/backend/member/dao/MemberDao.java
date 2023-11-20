package org.project.backend.member.dao;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.project.backend.constant.OauthLoginType;
import org.project.backend.member.dto.MemberDto;

@Mapper
public interface MemberDao {
	MemberDto findByLoginIdAndOauthLoginType(
			@Param("loginId") String loginId,
			@Param("oauthLoginType") OauthLoginType oauthLoginType
	) throws SQLException;

	long save(
			MemberDto memberDto
	) throws SQLException;
}
