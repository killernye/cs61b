package byog.lab5;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestHexWorld {

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        int width = 100;
        int height = 100;
        ter.initialize(width, height);
        TETile[][] canvas = new TETile[width][height];
        // fill the whole canvas with nothing
        for (int x = 0; x < width; x++) {
            for (int y = 0; y <  height; y++) {
                canvas[x][y] = Tileset.NOTHING;
            }
        }
        TETile t = HexWorld.randomTile();
        HexWorld.Position p = new HexWorld.Position(50, 50);
        HexWorld.addHexagon(canvas, p, 25, t);
//        HexWorld.fillWithUnitHexagon(unitHexagon, SIZE, SIZE - 1, 0);
//        HexWorld.drawLowerHalfHexagon(unitHexagon, SIZE, SIZE - 1, 0);
//        HexWorld.drawUpperHalfHexagon(unitHexagon, SIZE, 0, SIZE);
        ter.renderFrame(canvas);
    }
}
