package byog.Core;
import byog.TileEngine.Tileset;
import byog.TileEngine.TETile;
import java.util.Random;
public class MapGeneratorParameters {
    Random random;
    int roomNum;
    int width;
    int height;
    TETile wTile;
    TETile fTile;
    TETile outTile;


    /** Returns a mgp with default value. */
    static MapGeneratorParameters defaultParameters() {
        MapGeneratorParameters mgp =  new MapGeneratorParameters();
        mgp.random = new Random(System.currentTimeMillis());
        mgp.roomNum = mgp.random.nextInt(100) + 200;
        mgp.width = 100;
        mgp.height = 60;
        mgp.wTile = Tileset.WALL;
        mgp.fTile = Tileset.GRASS;
        mgp.outTile = Tileset.NOTHING;
        return mgp;
    }
}
