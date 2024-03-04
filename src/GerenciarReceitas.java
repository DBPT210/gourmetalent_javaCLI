import java.sql.*;
import java.util.Scanner;
public class GerenciarReceitas {
    public Scanner userInput = new Scanner(System.in);
    public String strInput;
    boolean exception;
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
            String dbName = "projetin";
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
        ResultSet resultSet = statement.executeQuery("SELECT `id_usuario` FROM `usuario` WHERE `nome` = '" + loginUsername + "' && `password` = '" + loginPassword + "';");
        System.out.printf("\nId: %d\n", resultSet.getInt("id_usuario"));
        if (!resultSet.next()){ //verificar se o user existe
            System.out.println("USER NOT FOUND!");
            new GerenciarReceitas();
        }

        // end of [check if user exists]
        connection.close();

        GerenciarMenu(loginUsername);
    }
    public void GerenciarMenu(String loginUsername) throws SQLException {
        int intInput = 0;
        userInput = new Scanner(System.in);

        System.out.println("Bemvindo, " + loginUsername + "!");
        System.out.println("O que deseja fazer?");
        System.out.println("1. Criar uma receita;");
        System.out.println("2. Editar uma receita;");
        System.out.println("3. Eliminar uma receita;");
        try{
            intInput = userInput.nextInt(); //intInput
            switch (intInput){
                case 1:
                    CriadorReceita(loginUsername);
                    break;
                case 2:
                    EditarReceita();
                    break;
                case 3:
                    EliminarReceita();
                    break;
                default:
                    System.out.println("Input inválido. Por favor introduza um valor válido.");
                    GerenciarMenu(loginUsername);
            }
        }catch (Exception e){
            System.out.println("Input inválido. Por favor introduza um valor válido.");
            GerenciarMenu(loginUsername);
        }
    }
    public void CriadorReceita(String loginUsername) throws SQLException {
        System.out.println("Criador de Receitas");
        System.out.println("Nome da Receita: ");
        String NomeReceita = userInput.next(); //input
        System.out.println("Categoria da receita (minusculo): ");
        String StrCatgReceita = userInput.next(); //input
        int catgReceita=0;
        switch (StrCatgReceita){ //para selecionar o ID correto das categorias
            case "carne":
                catgReceita = 1;
                break;
            case "peixe":
                catgReceita = 2;
                break;
            case "sobremesa":
            case "sobremesas":
                catgReceita = 3;
                break;
            case "sopa":
            case "sopas":
            case "cozido":
            case "cozidos":
            case "sopa e cozido":
            case "sopas e cozidos":
            case "sopa e cozidos":
            case "sopas e cozido":
                catgReceita = 4;
                break;
            default: System.out.println("A Categoria inserida não é váliida. Por favor escolha apenas entre: 'carne', 'peixe', 'sobremesas', ou 'sopas e cozidos'.");
            CriadorReceita(loginUsername);
        }
        System.out.println("Insira a lista de ingredientes (separe cada ingrediente com um ';'): ");
        String ingredientes = userInput.next();
        System.out.println("Insira os passos da preparação (separe cada passo com um ';'): ");
        String preparacao = userInput.next();
        System.out.println("Tempo de preparação (hh:mm:ss): ");
        String tempo = userInput.next();
        // database connector
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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
                throw new RuntimeException(e);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        // end of database connector
        // start of [CREATE receita]
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
        int intResultSet = statement.executeUpdate("INSERT INTO `receitas` VALUES (NULL, '" + NomeReceita + "', '" + catgReceita + ", '" + loginUsername); //criar
        System.out.println("A sua conta foi criada! Agora você pode fazer login.");
        // end of [CREATE receita]

        /*System.out.println("Insira os ingredientes (separe-os com ';'):");
        String ingredientes = userInput.next(); //input
        System.out.println("Introduza os passos de preparo (separe-os com ';'):");*/
    }
    public void EditarReceita(){
        System.out.println("Editor de Receitas");
    }
    public void EliminarReceita(){
        System.out.println("Eliminador de Receitas");
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
            String dbName = "projetin";
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
        // end of [create account]
        new GerenciarReceitas();
    }
}
