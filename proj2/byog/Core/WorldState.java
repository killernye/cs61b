package byog.Core;
import java.util.LinkedList;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class WorldState {
    // all the detail i can think of
    MapGeneratorParameters mgp;
    LinkedList<Room> rooms;
    LinkedList<Position> cuts;
    Player p1;
    TETile[][] map;
    public WorldState(MapGeneratorParameters m, LinkedList<Room> r, LinkedList<Position> c, Player p) {
        mgp = m;
        rooms = r;
        cuts = c;
        p1 = p;
        map = null;
    }

    public TETile[][] terrainGrid() {
        TETile[][] map = new TETile[mgp.width][mgp.height];
        fillOutLand(map);
        for (Room room: rooms) {
            room.addRoom(map);
        }
        for (Position cut: cuts) {
            drawASpot(map, cut, mgp.fTile);
        }
        p1.addPlayer(map);
        this.map = map;
        return map;
    }

    /**
     * Initialize a canvas with nothing.
     * @param tiles the canvas
     */
    public void fillOutLand(TETile[][] tiles) {
        int height = tiles[0].length;
        int width = tiles.length;
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                tiles[x][y] = mgp.outTile;
            }
        }
    }

    /** Draw a single position on a map. */
    public static void drawASpot(TETile[][] map, Position p, TETile tile) {
        map[p.x][p.y] = tile;
    }

    public void playerMove(char move) {
        p1.movePosition(map, move, mgp.fTile);
    }

}
