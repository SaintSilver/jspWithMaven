package com.kutar.support;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;

import org.junit.Test;

public class ConnectionTests {

	@Test
	public void connection() {
		Connection conn = ConnectionManager.getConnection();
		assertNotNull(conn);
	}
}
