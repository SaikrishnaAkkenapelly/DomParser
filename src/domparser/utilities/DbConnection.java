/**
 * 
 */
package domparser.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import domparser.dbcalls.DatabaseRetrival;

/**
 * @author sakkenapelly
 *
 */
public class DbConnection {

	private static Logger logger =Logger.getLogger(DatabaseRetrival.class);
	
	public static Connection connect() {

		Connection connection = null;
		String connectionUrl = "jdbc:sqlserver://INHYNSAKKENAPE1:1433;databaseName=SYNC_PURCHASE_ORDERS;"
				+ "user=auto;password=Infor2020";
		try {
			connection = DriverManager.getConnection(connectionUrl);
		} catch (SQLException e) {
			logger.error("exeception raised while establishing connection");
		}
		return connection;

	}

}
