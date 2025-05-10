import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Map.clearConsole();

        Scanner input = new Scanner(System.in);

        System.out.println("Enter a map seed:");
        System.out.print("> ");
        String seed = input.nextLine();

        Map map = new Map(seed);
        Player player = new Player();

        while (true) {
            System.out.println();
            System.out.println("LVL 1 ▓░░░░░░░░░");
            System.out.print("\n> ");

            String cmd = input.nextLine().toLowerCase();

            if (cmd.equals("")) {
                int[] coords = player.getCoords();
                player.moveTo(coords[0], coords[1]);
            } else if (cmd.equals("exit")) {
                System.out.println("Exiting...");
                break;
            } else {
                player.move(cmd.charAt(0));
            }
        }

        input.close();

    }
}