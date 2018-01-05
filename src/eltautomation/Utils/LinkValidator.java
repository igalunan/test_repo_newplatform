package eltautomation.Utils;

import java.sql.Connection;
import java.sql.SQLException;

import snaq.db.ConnectionValidator;

public class LinkValidator implements ConnectionValidator {

	@Override
	public boolean isValid(Connection conn) throws SQLException {
		return conn.isValid(5);
	}
}
