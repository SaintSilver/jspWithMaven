package com.kutar.support;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PreparedStatementSetter {

	void setParameters(PreparedStatement psmt) throws SQLException;

	
}
