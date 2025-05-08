import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("\n\nEnter a map seed:");
        System.out.print("> ");
        String seed = input.nextLine();

        Map map = new Map(seed);
        Map player = new Player();


    }
}