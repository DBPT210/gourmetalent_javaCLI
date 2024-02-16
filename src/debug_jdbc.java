

import java.sql.*;

public class debug_jdbc {
    public debug_jdbc() throws SQLException {
        System.out.println("debug_jdbc");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
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
        System.out.println("CONNECTED TO DATABASE!");
        select(connection);
    }
    public void select(Connection connection) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int catg = 2;
        ResultSet resultSet = statement.executeQuery("SELECT * FROM receitas WHERE id_categoria = " + catg + " ;");
        while (resultSet.next()){

            //String result = resultSet.getString("id");
            //System.out.println(result);

            System.out.printf("%d %s\n",
                    resultSet.getInt(1),
                    resultSet.getString(2)
                    );

            //System.out.printf("Id: %d, Name: %s, Salary: %d, Age: %d\n", // select return
            //        resultSet.getInt(1), resultSet.getString("name"),
            //        resultSet.getInt(3), resultSet.getInt("age"));
        } // Tanto podemos utilizar o indice da coluna, como o nome que identifica a coluna
        connection.close();// Temos sempre que terminar a ligação
        System.exit(69);
    }
}
