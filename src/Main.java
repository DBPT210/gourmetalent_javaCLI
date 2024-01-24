import java.sql.Time;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner userInput = new Scanner(System.in);
        int catgChoise = 0;
        System.out.println("'gourmet|alent'");
        System.out.println(" ");
        System.out.println("O que você vai querer cozinhar hoje?");
        System.out.println("Por favor, selecione por número.");
        System.out.println("1. Sopas e Cozidos;");
        System.out.println("2. Carne");
        System.out.println("3. Peixe;");
        System.out.println("4. Sobremesas;");
        try {
            catgChoise = userInput.nextInt();
        } catch (Exception e){
            System.out.println("Valor inválido! Por favor use um número.");
            TimeUnit.SECONDS.sleep(3);
            System.out.print("\033[H\033[2J");
            System.out.flush();
            main(new String[]{""});
        }
        switch (catgChoise){
            case 1:
                //menu catg de sopas
                new menuCatg(1);
                break;
            case 2:
                //menu catg de carne
                new menuCatg(2);
                break;
            case 3:
                //menu catg de peixe
                new menuCatg(3);
                break;
            case 4:
                //menu catg sobremesas
                new menuCatg(4);
                break;
            default:
                System.out.println("Por favor  introduza um número de seleção válido");
                TimeUnit.SECONDS.sleep(3);
                System.out.print("\033[H\033[2J");
                System.out.flush();
                main(new String[]{""});
                break;
        }
    }
}