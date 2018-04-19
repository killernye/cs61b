package byog.Core;

import byog.TileEngine.TETile;

import java.util.LinkedList;

public interface Hallway {
    /** Check if the room out of the border of the world. */
    public Boolean outOfBorder(int maxX, int maxY) {
        if (position.x + width - 1 > maxX || position.x - width + 1 < 0) {
            return true;
        } else if (position.y + height - 1> maxY || position.y - height + 1 < 0) {
            return true;
        } else {
            return false;
        }
    }

    /** Check if the room overlapping existing rooms. */
    public Boolean overlap(LinkedList<Room> rooms) {
        for (Room r: rooms) {
            for (Position p = position; p.y < position.y + height; p = p.yOffsetPosition(1)) {
                for(Position q = p; q.x < position.x + width; q = q.xOffsetPosition(1)) {
                    if (q.inRoom(r)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }



    /**
     * Add a single room to the given position.
     * @param map The dungeon world.
     */
    public void addRoom(TETile[][] map) {
        Position floorP = position.xyOffsetPosition(1, 1);
        Position topLeft = position.yOffsetPosition(height - 1);
        Position leftVWall = position.yOffsetPosition(1);
        Position rightVWall = position.xyOffsetPosition(width - 1, 1);
        addHWall(map, position, width, wTile);
        addHWall(map, topLeft, width, wTile);
        addVWall(map, leftVWall, height - 2, wTile);
        addVWall(map, rightVWall, height - 2, wTile);
        addFloor(map, floorP, width - 2, height - 2, fTile);
    }


    /**
     * The lowest level drawing method.
     * @param map The world
     * @param p Left-bottom position
     * @param length size
     * @param tile draw what?
     */
    private static void drawHorizontal(TETile[][] map, Position p, int length, TETile tile) {
        for(int x = 0; x < length; x++) {
            int xCoord = p.x + x;
            int yCoord = p.y;
            map[xCoord][yCoord] = tile;
        }
    }

    public static void drawVertical(TETile[][] map, Position p, int length, TETile tile) {
        for (int y = 0; y < length; y++) {
            int xCoord = p.x;
            int yCoord = p.y + y;
            map[xCoord][yCoord] = tile;
        }
    }

    /**
     * Add a horizontal wall to the given position.
     * @param map The world of the game.
     * @param p Left-bottom position of the wall.
     * @param length The length of the wall.
     * @param tile The text to draw.
     */
    public static void addHWall(TETile[][] map, Position p, int length, TETile tile) {
        drawHorizontal(map, p, length, tile);
    }

    /**
     * Add a vertical wall to the given position.
     * @param map The world of the game.
     * @param p Left-bottom position of the wall.
     * @param length The length of the wall.
     * @param tile The tile for the wall.
     */
    public static void addVWall(TETile[][] map, Position p, int length, TETile tile) {
        drawVertical(map, p, length, tile);
    }

    /**
     * Add a rectangular floor.
     * @param map The world
     * @param p  Position
     * @param width horizontal size
     * @param height vertical size
     * @param tile Drawing material
     */
    public static void addFloor(TETile[][] map, Position p, int width, int height, TETile tile) {
        for (int i = 0; i < height; i++) {
            drawHorizontal(map, p, width, tile);
            p.y += 1;
        }
    }

    public Position leftBottomCorner() {
        return position;
    }
    public Position leftTopCorner() {
        return position.yOffsetPosition(height - 1);
    }
    public Position rightBottomCorner() {
        return position.xOffsetPosition(width -1 );
    }
    public Position rightTopCorner() {
        return position.xyOffsetPosition(width-1, height-1);
    }

}
