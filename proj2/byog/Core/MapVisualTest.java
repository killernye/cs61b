package byog.Core;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;

public class MapVisualTest {

//    private static final int STARTX = 30;
//    private static final int STARTY = 6;
//    private static final int MAXW = 8;
//    private static final int MAXH = 8;
//    private static final int MAX_OFFSET  = 3;
//    private static final Room.side ENTRY_SIDE = Room.side.BOTTOM;
//    private static final TETile ENTRY_TILE = Tileset.LOCKED_DOOR;
//    private static final TETile EXIT_TILE = Tileset.FLOOR;
//
//    public static final TERenderer ter = new TERenderer();
//
    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        MapGeneratorParameters mgp = MapGeneratorParameters.defaultParameters();

        ter.initialize(mgp.width, mgp.height);


//        Generator g = new Generator(mgp);
//        WorldState ws = g.generate();
//        TETile[][] canvas = ws.terrainGrid();
//        ter.renderFrame(canvas);
//        StdDraw.setPenColor(Color.white);
//        StdDraw.textLeft(1, mgp.height - 1, "hello!");

    }
}
