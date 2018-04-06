package byog.lab5;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestHexWorld {

    @Test
    public void testTopRightNeighour() {
        HexWorld.Position p = new HexWorld.Position(0, 0);
        HexWorld.Position expected1 = new HexWorld.Position(3, 2);
        HexWorld.Position actual1 = HexWorld.topRightNeighbor(p, 2);
        assertEquals(0, expected1.compareTo(actual1));
        HexWorld.Position expected2 = new HexWorld.Position(3, -2);
        HexWorld.Position actual2 = HexWorld.bottomRightNeighbor(p, 2);
        assertEquals(0, expected2.compareTo(actual2));
    }


    public static void fillWithNothing(TETile[][] tiles) {
        int height = tiles[0].length;
        int width = tiles.length;
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                tiles[x][y] = Tileset.NOTHING;
            }
        }
    }

    public static void main(String[] args) {
//        int width = 60;
//        int height = 50;
//        TERenderer ter = new TERenderer();
//        ter.initialize(width, height);
//        TETile[][] canvas = new TETile[width][height];
//        fillWithNothing(canvas);
//        HexWorld.Position topP = new HexWorld.Position(10, 30);
//        HexWorld.drawRandomVerticalHexes(canvas, topP, 3, 4);
//        ter.renderFrame(canvas);
        HexWorld.drawHexagonWorld(5);
    }
}
