import java.sql.*;
public class menuCatg {
    public menuCatg(int catg) throws SQLException{
        switch (catg){
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
        // end of database connector

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
        ResultSet resultSet = statement.executeQuery("SELECT * FROM receitas WHERE id_categoria = " + catg + " ;");
        while (resultSet.next()){

            System.out.printf("%d %s\n",
                    resultSet.getInt(1), //id
                    resultSet.getString(2) //nome de receita
            ); //loop para mostrar todos os resultados

        }
        connection.close();

/*        switch (catg){
            case 1:
                catgCarne();
                break;
            case 2:
                catgPeixe();
                break;
            case 3:
                catgSobrem();
                break;
            case 4:
                catgSopa();
                break;
        }
    }
    public void catgCarne(){
        System.out.println("Categoria: Carne;");

    }
    public void catgPeixe(){
        System.out.println("Categoria: Peixe;");
    }
    public void catgSobrem(){
        System.out.println("Categoria: Sobremesas;");
    }
    public void catgSopa(){
        System.out.println("Categoria: Sopas e Cozidos;"); */
    }
}
