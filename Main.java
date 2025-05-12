import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game.clearConsole();

        Scanner input = new Scanner(System.in);

        System.out.println("Enter a map seed:");
        System.out.print("> ");
        String seed = input.nextLine();

        Game game = new Game(seed);
        Player player = game.getPlayer();

        while (true) {
            System.out.println();
            System.out.println("LVL 1 ▓░░░░░░░░░");
            System.out.print("\n> ");

            String cmd = input.nextLine().toLowerCase();

            if (cmd.equals("")) {
                int[] coords = player.getCoords();
                player.moveTo(coords[0], coords[1]);
            } else if (cmd.equals("restart")) {
                game = new Game(seed);
                player = game.getPlayer();
            } else if (cmd.equals("exit")) {
                System.out.println("Exiting...");

                // Added because Visual Studio was giving warnings
                input.close();
                System.exit(0);
            } else {
                player.move(cmd.charAt(0));
            }
        }

    }
}