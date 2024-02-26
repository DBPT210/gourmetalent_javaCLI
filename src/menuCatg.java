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
        /*try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
        Connection connection = null;
        try {
            /*try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }*/
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
            /*try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }*/
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
            System.out.println("\nEscolha o id da receita.");
            int id = scanner.nextInt();
            String secondQuery = "SELECT * FROM receitas WHERE id_receita = " + id + ";";
            ResultSet seconresultSet = statement.executeQuery(secondQuery);
            while (seconresultSet.next()) {
                String ing = seconresultSet.getString(6);
                String prep = seconresultSet.getString(5);
                System.out.println(ing);
                System.out.println(prep);
            }
            seconresultSet.close();

            connection.close();
        } catch (Exception e){
                e.printStackTrace();
                }
            } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
