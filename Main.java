import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game.clearConsole();

        Scanner input = new Scanner(System.in);

        System.out.println("Enter number of levels (Leave blank for random)");
        System.out.print("> ");
        String lvls = input.nextLine();

        Game game;
        Player player;

        if (lvls.equals("")) {
            game = new Game();
        } else {
            game = new Game(Integer.parseInt(lvls));
        }
        lvls = Integer.toString(game.totalLevels);

        while (true) {
            if (game.getCurrentLevel() > Integer.parseInt(lvls)) {
                Game.clearConsole();
                System.out.println("You win!");
                break;
            }
            player = game.getPlayer();

            System.out.println();
            System.out.println(game.toString());
            System.out.print("\n> ");

            String cmd = input.nextLine().toLowerCase();

            if (cmd.equals("")) {
                int[] coords = player.getCoords();
                player.moveTo(coords[0], coords[1]);
            } else if (cmd.equals("restart")) {
                game = new Game(Integer.parseInt(lvls));
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