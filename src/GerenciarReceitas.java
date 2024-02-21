import java.sql.*;
import java.util.Scanner;
public class GerenciarReceitas {
    public Scanner userInput = new Scanner(System.in);
    public String strInput;
    public GerenciarReceitas() throws SQLException {
        System.out.println("Bemvindo ao criador de receitas!");
        System.out.println("Faça login ou crie uma conta:");
        System.out.println("Comandos disponíveis:");
        System.out.println("login - para fazer login com uma conta existente;");
        System.out.println("criar - para criar uma nova conta;");
        strInput = userInput.next(); //input
        switch (strInput){
            case "login":
                Login();
                break;
            case "criar":
                CreateAcc();
                break;
            default:
                System.out.println("Por favor escolha uma opção dada.");
                new GerenciarReceitas();
        }
        //System.out.println("Aqui você pode criar a sua propria receita para todos os utilizadores");
    }
    public void Login() throws SQLException{
        System.out.println("Por favor introduza os seus detalhes de sua conta:");
        System.out.print("Username: ");
        strInput = userInput.next(); //input
        String loginUsername = strInput;
        System.out.printf("\nPassword: ");
        strInput = userInput.next(); //input
        String loginPassword = strInput;

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

        // start of [check if user exists]
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
        ResultSet resultSet = statement.executeQuery("SELECT * FROM `usuario` WHERE `nome` = '" + loginUsername + "' && `password` = '" + loginPassword + "';");
        if (!resultSet.next()){ //verificar se o user existe
            System.out.println("USER NOT FOUND!");
        }

        // end of [check if user exists]
        connection.close();

        System.out.println("Bemvindo, " + loginUsername + "!");
        System.out.println("O que deseja fazer?");
        System.out.println("1. Criar uma receita;");
        System.out.println("2. Editar uma receita;");
        System.out.println("3. Eliminar uma receita;");
        int intInput = userInput.nextInt(); //intInput
        switch (intInput){
            case 1:
                CriadorReceita();
                break;
            case 2:
                EditarReceita();
        }
    }

    public void CreateAcc () throws SQLException {
        System.out.println("Bemvindo ao criador de contas");
        System.out.println("Por favor insera os dados:");
        System.out.print("Username: ");
        strInput = userInput.next(); //input
        String loginUsername = strInput;
        System.out.print("\nEmail: ");
        strInput = userInput.next(); //input
        String loginEmail = strInput;
        System.out.print("\nPassword: ");
        strInput = userInput.next(); //input
        String loginPassword = strInput;

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

        // start of [create account]
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
        ResultSet resultSet = statement.executeQuery("SELECT * FROM `usuario` WHERE `nome` = '" + loginUsername + "';");
        if (!resultSet.next()){ //verificar se o user existe
            //System.out.println("USER NOT FOUND!");
            //resultSet = statement.executeQuery("INSERT INTO `usuario` (`id_usuario`, `nome`, `email`, `password`) VALUES (NULL, '" + loginUsername + "', '" + loginEmail + "', '" + loginPassword + "');");
            int intResultSet = statement.executeUpdate("INSERT INTO `usuario` (`nome`, `email`, `password`) VALUES ('" + loginUsername + "', '" + loginEmail + "', '" + loginPassword + "');"); //criar
            //int intResultSet = statement.executeUpdate("INSERT INTO `usuario` (`nome`, `email`, `password`) VALUES ('asdf', 'asdf@aaaa.aa', 'asdf');");
        }else {
            System.out.println("O username '" + loginUsername + "', já está a ser usado! Por favor escolha outro.");
            CreateAcc();
        }
        System.out.println("A sua conta foi criada! Agora você pode fazer login.");
        new GerenciarReceitas();
        // end of [create account]

    }

    public void CriadorReceita(){

    }
    public void EditarReceita(){

    }
    public void EliminarReceita(){

    }
}
