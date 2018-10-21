package com.kutar.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PreparedStatementSetter {

	void setParameters(PreparedStatement psmt) throws SQLException;

	
}
