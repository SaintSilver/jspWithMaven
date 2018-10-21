package core.support;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;

import org.junit.Test;

import core.jdbc.ConnectionManager;

public class ConnectionTests {

	@Test
	public void connection() {
		Connection conn = ConnectionManager.getConnection();
		assertNotNull(conn);
	}
}
