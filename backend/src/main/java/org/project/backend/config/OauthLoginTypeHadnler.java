package org.project.backend.config;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.project.backend.constant.OauthLoginType;

public class OauthLoginTypeHadnler extends BaseTypeHandler<OauthLoginType> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, OauthLoginType parameter, JdbcType jdbcType) throws
			SQLException {
		ps.setString(i, parameter.name());
	}

	@Override
	public OauthLoginType getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return OauthLoginType.valueOf(rs.getString("oauth_login_type"));
	}

	@Override
	public OauthLoginType getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return OauthLoginType.valueOf(rs.getString("oauth_login_type"));
	}

	@Override
	public OauthLoginType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return OauthLoginType.valueOf(cs.getString("oauth_login_type"));
	}
}
