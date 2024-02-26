import java.sql.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
public class Main {
    public static void main(String[] args) throws InterruptedException, SQLException {
        System.out.println("'gourmet|alent'");
        choice();
    }

    private static void choice() throws InterruptedException {
        Scanner userInput = new Scanner(System.in);
        int catgChoise = 0;

        System.out.println(" ");
        System.out.println("O que você vai querer cozinhar hoje?");
        System.out.println("Por favor, selecione por número.");
        System.out.println("1. Carne;");
        System.out.println("2. Peixe");
        System.out.println("3. Sobremesas;");
        System.out.println("4. Sopas e Cozidos;");
        System.out.println("5. Criar receita;");

        try {
            catgChoise = userInput.nextInt();
            if (catgChoise == 0) {
                new debug_jdbc();
            }
            if (catgChoise >= 1 && catgChoise <= 4) {
                new menuCatg(catgChoise);

            } else {
                if (catgChoise == 5) {
                    new CriarReceita();
                }
                System.out.println("Valor inválido! Por favor use os números das opções.");
                choice();
            }
        } catch (Exception e) {
            System.out.println("Valor inválido! Por favor use um número.");
            // TimeUnit.SECONDS.sleep(3);
            System.out.print("\033[H\033[2J"); //mais ou menos um "clear screen"
            System.out.flush();
            choice();
        }
    }
}
