package byog.Core;
import byog.TileEngine.Tileset;
import byog.TileEngine.TETile;
import edu.princeton.cs.algs4.In;

import java.util.Random;

public class MapGeneratorParameters {
    Random random;
    int width;
    int height;
    TETile wTile = Tileset.WALL;
    TETile fTile = Tileset.FLOOR;
    TETile outTile = Tileset.NOTHING;
    TETile pTile = Tileset.PLAYER;
    TETile door = Tileset.LOCKED_DOOR;


    /** Returns a mgp with default value. */
    static MapGeneratorParameters defaultParameters() {
        MapGeneratorParameters mgp =  new MapGeneratorParameters();
        mgp.height = 40;
        mgp.width = 100;
        mgp.random = new Random(System.currentTimeMillis());
        return mgp;
    }

    public static MapGeneratorParameters getACustomParameters(String seed, int w, int h) {
        MapGeneratorParameters mgp =  new MapGeneratorParameters();
        mgp.width = w;
        mgp.height = h - 5;
        String seedNum = seed.substring(1, seed.length() - 1);
        int num = Integer.parseInt(seedNum);
        mgp.random = new Random(num);
        return mgp;
    }

}
