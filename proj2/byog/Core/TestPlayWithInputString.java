package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;

public class TestPlayWithInputString {

    public static void main(String[] args) {
        Game g = new Game();
        TETile[][] world = g.playWithInputString("1109");

        TERenderer t = new TERenderer();
        t.initialize(g.mgp.width, g.mgp.height);
        t.renderFrame(world);
    }

}
