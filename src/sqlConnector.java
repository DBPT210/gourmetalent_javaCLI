import java.sql.*;

public class sqlConnector {
    public sqlConnector(Connection connection) {
    }

    public Connection sqlConnector(Connection connection) throws SQLException {
        // database connector
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbName = "gourmetalent";
            String user = "root";
            String password = "";
            String url = "jdbc:mysql://localhost:3306/" + dbName;
            try {
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        // end of database connector
        return connection;
    }
}
