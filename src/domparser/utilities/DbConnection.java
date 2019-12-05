/**
 * 
 */
package domparser.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author sakkenapelly
 *
 */
public class DbConnection {

	public static Connection connect() {

		Connection connection = null;
		String connectionUrl = "jdbc:sqlserver://INHYNSAKKENAPE1:1433;databaseName=SYNC_PURCHASE_ORDERS;"
				+ "user=auto;password=Infor2020";
		try {
			connection = DriverManager.getConnection(connectionUrl);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;

	}

}
