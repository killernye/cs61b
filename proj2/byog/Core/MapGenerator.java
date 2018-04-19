package byog.Core;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.LinkedList;
import java.util.Random;

public class MapGenerator {


    /** Uses the given parameters to build a random dungeon. */
    public static TETile[][] generate(MapGeneratorParameters mgp) {
        TETile[][] canvas = new TETile[mgp.width][mgp.height];
        LinkedList<Room> rooms = new LinkedList<>();
        fillWithNothing(canvas);

        for (int n = 0; n < mgp.roomNum; n++) {
            Room r = generateRandomRoom(mgp);
            if (roomSafetyCheck(mgp, rooms, r)) {
                r.addRoom(canvas);
                rooms.add(r);

            }
        }
        return canvas;
    }

    public static Boolean hallwaySafetyCheck(MapGeneratorParameters mgp, Room h) {
        if (!h.outOfBorder(mgp.width - 1, mgp.height - 1)) {
            return true;
        }
        return false;
    }
    public static Boolean roomSafetyCheck(MapGeneratorParameters mgp, LinkedList<Room> rooms, Room r) {
        if (!r.outOfBorder(mgp.width - 1, mgp.height - 1) && !r.overlap(rooms)) {
            return true;
        } else {
            return false;
        }
    }
    /** For each room, randomly generate neighbor rooms that branch off of the current room. */
    public static LinkedList<Room> generateRandomHallways(MapGeneratorParameters mgp, Room room) {
        LinkedList<Room> hallways = new LinkedList<>();
        // iterate four sides randomly decide branch off a hallway or not.
        for (int i = 0; i < 4; i++) {
            // 0, 1, 2, 3  up, down, left, right

            if (i == 0 && mgp.random.nextBoolean()) {
                int minIndex = room.leftTopCorner().x;
                int maxIndex = minIndex + room.width - 3;
                int x = mgp.random.nextInt(room.width - 2) + minIndex;
                int y = room.leftTopCorner().y + 1;
                Position hallway = new Position(x, y);
                int length = mgp.random.nextInt(29);
                hallways.add(new VerticalHallway(length, hallway, mgp.wTile, mgp.fTile));
            }
        }
        return hallways;
    }
    /** Generates a random room. **/
    public static Room generateRandomRoom(MapGeneratorParameters mgp) {
        int w = mgp.random.nextInt(10) + 3;
        int h = mgp.random.nextInt(10) + 3;
        int x = mgp.random.nextInt(mgp.width);
        int y = mgp.random.nextInt(mgp.height);
        Position p = new Position(x, y);
        return new Room(w, h, p, mgp.wTile, mgp.fTile);
    }

    /**
     * Initialize a canvas with nothing.
     * @param tiles the canvas
     */
    public static void fillWithNothing(TETile[][] tiles) {
        int height = tiles[0].length;
        int width = tiles.length;
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                tiles[x][y] = Tileset.NOTHING;
            }
        }
    }



}
