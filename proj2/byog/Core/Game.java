package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 85;
    public static final int HEIGHT = 45;
    public MapGeneratorParameters mgp;
    public boolean started;
    public boolean gameOver;


    public static void main(String[] args) {
        Game game = new Game();
        game.playWithKeyboard();
    }

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
        ter.initialize(WIDTH, HEIGHT);

        startGame();



    }



    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().
        mgp = MapGeneratorParameters.getACustomParameters(input, WIDTH, HEIGHT-3);
        Generator g = new Generator(mgp);
        WorldState ws = g.generate();

        TETile[][] finalWorldFrame = ws.terrainGrid();
        return finalWorldFrame;
    }

    public void startGame() {
        started = false;
        gameOver = false;

        drawMenu("");
        StdDraw.pause(500);

        while (!started) {
            if (StdDraw.hasNextKeyTyped()) {
                if (StdDraw.nextKeyTyped() == 'n') {
                    drawMenu("Random Seed Please!");
                    StdDraw.pause(1000);

                    String seed = "N" + solicitSeed() + "S";
                    StdDraw.pause(1000);

                    mgp = MapGeneratorParameters.getACustomParameters(seed, WIDTH, HEIGHT);
                    started = true;
                }
            }
        }

        Generator g = new Generator(mgp);
        WorldState ws = g.generate();
        ter.renderFrame(ws.terrainGrid());

        while (!gameOver) {
            // solicit the move command
            if (!StdDraw.hasNextKeyTyped()) {
                continue;
            }
            char move = StdDraw.nextKeyTyped();
            System.out.print(move);
            // update the player position
            ws.playerMove(move);
            // redraw the map
            ter.renderFrame(ws.terrainGrid());
        }
    }

    public void drawMenu(String text) {
        int midWidth = WIDTH / 2;
        int midHeight = HEIGHT / 2;

        Font superBigFont = new Font("Monaco", Font.BOLD, 64);
        Font bigFont = new Font("Monaco", Font.BOLD, 32);
        Font smallFont = new Font("Monoca", Font.BOLD, 20);

        StdDraw.clear(Color.black);
        StdDraw.setPenColor(Color.white);


        StdDraw.setFont(superBigFont);
        StdDraw.text(midWidth, HEIGHT * 2 / 3, "CS61B GAME");
        StdDraw.setFont(bigFont);
        StdDraw.text(midWidth, midHeight, "New Game (N)");
        StdDraw.text(midWidth, midHeight - 3, "Load Game (L)");
        StdDraw.text(midWidth, midHeight - 6, "Quit (Q)");

        StdDraw.setFont(smallFont);
        StdDraw.text(midWidth, midHeight - 9, text);

        StdDraw.show();
    }

    /** Wait until user input n chars then return the string. */
    public String solicitNCharsInput(int n) {
        String input = "";
        while (input.length() < n) {
            if (!StdDraw.hasNextKeyTyped()) {
                continue;
            } else {
                char key = StdDraw.nextKeyTyped();
                input += String.valueOf(key);
            }
        }
        return input;
    }

    public String solicitSeed() {
        String seed = "";
        //loop until the next char is 'S"
        while (true) {
            if (!StdDraw.hasNextKeyTyped()) {
                continue;
            }
            char key = StdDraw.nextKeyTyped();

            if (key == 's') {
                System.out.println("\nWe get a seed, which is " + seed);
                return seed;
            } else {
                seed += String.valueOf(key);
                drawMenu(seed);
            }
        }
    }

}
