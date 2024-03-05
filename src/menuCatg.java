import java.sql.*;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class menuCatg {
    Scanner scanner = new Scanner(System.in);
    public menuCatg(int catg) throws SQLException {
        switch (catg) {
            case 1:
                System.out.println("Categoria: Carne;");
                break;
            case 2:
                System.out.println("Categoria: Peixe;");
                break;
            case 3:
                System.out.println("Categoria: Sobremesas;");
                break;
            case 4:
                System.out.println("Categoria: Sopas e Cozidos;");
        }
        // database connector
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbName = "projetin";
            String user = "root";
            String password = "";
            String url = "jdbc:mysql://localhost:3306/" + dbName;
            try {
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            // end of database connector
            // start of [SQL SELECT receitas de categoria]
            Statement statement = connection.createStatement();
            ResultSet resultSet = null;
            try {
            String fistQuery = "SELECT * FROM receitas WHERE id_categoria = " + catg + " ;";
            resultSet = statement.executeQuery(fistQuery);
            while (resultSet.next()) {
                        int idc = resultSet.getInt(1);
                        String nome = resultSet.getString(2);
                System.out.println(idc +" "+ nome);

            }
            System.out.println("\nEscolha a receita.");
            int id = scanner.nextInt();
            String secondQuery = "SELECT * FROM receitas WHERE id_receita = " + id + ";";
            ResultSet seconresultSet = statement.executeQuery(secondQuery);
            while (seconresultSet.next()) {
                String ing = seconresultSet.getString(6);
                String prep = seconresultSet.getString(5);
                System.out.println("\n Ingredientes: \n"+ing);
                System.out.println("\n Modo de preparo: \n\n "+prep);
            }
            //seconresultSet.close();

            connection.close();
        } catch (Exception e){
                e.printStackTrace();
                }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
