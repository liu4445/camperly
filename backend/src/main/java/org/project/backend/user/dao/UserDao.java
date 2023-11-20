package org.project.backend.user.dao;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.project.backend.constant.OauthLoginType;
import org.project.backend.user.dto.UserDto;

@Mapper
public interface UserDao {
	UserDto findByLoginIdAndOauthLoginType(
			@Param("loginId") String loginId,
			@Param("oauthLoginType") OauthLoginType oauthLoginType
	) throws SQLException;

	long save(
			UserDto userDto
	) throws SQLException;
}
