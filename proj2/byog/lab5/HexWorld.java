package byog.lab5;

import javafx.geometry.Pos;
import org.junit.Test;
import static org.junit.Assert.*;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
//    private static final int WIDTH = 100;
//    private static final int HEIGHT = 100;
    private static final Random RANDOM = new Random(939);

    /** A nested class provide coordinate position information. */
    public static class Position implements Comparable<Position> {
        private int _x;
        private int _y;

        public Position(int x, int y) {
            _x = x;
            _y = y;
        }

        public int getX() {
            return _x;
        }

        public int getY() {
            return _y;
        }

        public int compareTo(Position p) {
            if (this.getY() == p.getY() && this.getX() == p.getX()) {
                return 0;
            } else {
                return 2;
            }
        }
    }

    // topRightNeighbor(current hexagon) and bottomRightNeighbor() which computes
    // the current hexagon's neighbor's bottom-left Position.

    // the World consists of 5 columns of 3, 4, 5, 4 and 3 hexagons, respectively

    // drawRandomVerticalHexes that draws a column of N hexes, each one with a
    // random biome

    // main method that calls drawRandomVerticalHexes five times, one for each of
    // the five columns of the world, consisting of 3, 4, 5, 4 and 3 hexagons. To
    // figure out the starting position for the top hex of each column, try to use
    // the topRightNeighbor or bottomRightNeighbor on the old top, as appropriate.

    /**
     * Draw a Hexagon World consisting of 19 random Hexagons.
     * @param s The size of each unit hexagon
     */
    public static void drawHexagonWorld(int s) {
        // set the size of the canvas
        int width = 11*s - 6;
        int height = 10*s;

        // initialize the engine
        TERenderer ter = new TERenderer();
        ter.initialize(width, height);
        TETile[][] canvas = new TETile[width][height];
        fillWithNothing(canvas);

        // set the parameters for the big hexagon
        int[] hexes = new int[]{3, 4, 5, 4, 3};
        Position p = new Position(s-1, height - 4*s); // start point

        // iterate over five columns of hexes
        for(int i = 0; i < 5; i++) {
            Position nextP;
            if (i < 2) {
                nextP = topRightNeighbor(p, s);
            } else {
                nextP = bottomRightNeighbor(p, s);
            }
            drawRandomVerticalHexes(canvas, p, hexes[i], s);
            p = nextP;
        }

        // draw the canvas to screen
        ter.renderFrame(canvas);
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

    /**
     * Draws a column of N hexes, each one with a random biome.
     * @param p left-bottom position of the top hexagon of the column
     * @param n the size of the column
     * @param s the size of each hexagon
     */
    public static void drawRandomVerticalHexes(TETile[][] canvas, Position p, int n, int s) {
        for (int i = 0; i < n; i++) {
            addHexagon(canvas, p, s, randomTile());
            p = bottomNeighbor(p, s);
        }
    }

    /**
     * Computes the left-bottom position of the bottom hexagon of the current one
     * @param p the left-bottom position of the current hexagon
     * @param s the size of the unit hexagon
     * @return
     */
    public static Position bottomNeighbor(Position p, int s){
        return new Position(p.getX(), p.getY() - 2*s);
    }

    /**
     * Computes the bottom-left position of the current hexagon's topRight neighbor.
     * @param currentP the bottom-left position of the current hexagon
     * @param s the size of each unit hexagon
     * @return the bottom-left position of the top right hexagon
     */
    public static Position topRightNeighbor(Position currentP, int s) {
        int x = currentP.getX() + 2 * s - 1;
        int y = currentP.getY() + s;
        return new Position(x, y);
    }

    /**
     * Computes the bottom-left position of the current hexagon's BottomRight neighbor
     * @param currentP the bottom-left position of the current hexagon
     * @param s the size of unit Hexagon
     * @return the bottom-left position of the bottom right hexagon
     */
    public static Position bottomRightNeighbor(Position currentP, int s) {
        int x =currentP.getX() + 2 * s - 1;
        int y = currentP.getY() - s;
        return new Position(x, y);
    }

    /**
     * Adds a single unit hexagon to the canvas.
     * @param canvas the world to draw on
     * @param p the bottom left coordinate of the hexagon
     * @param s the size of the hexagon
     * @param t the tile to draw
     */
    public static void addHexagon(TETile[][] canvas, Position p, int s, TETile t) {
        if (s < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }
        // hexagons have 2*s rows. This code iterates up from the bottom row,
        // which we call row 0.
        for (int yi = 0; yi < 2 * s; yi += 1) {
            int thisRowY = p.getY() + yi;
            int xRowStart = p.getX() + hexRowOffset(s, yi);
            Position rowStartP = new Position(xRowStart, thisRowY);
            int rowWidth = hexRowWidth(s, yi);
            drawHorizontalLine(canvas, t, rowWidth, rowStartP);
        }
    }

/*
    public static void addHexagon(TETile[][] canvas, int size, int x0, int y0) {

        // create a random little hexagon generator
        // create a position generator for each unit hexagon.

        // fill the whole paper by a loop code to place the little hexagon
        // in the right position
        int[] layer = new int[]{0, 1, 1, 2, 2, 2, 3, 3, 4, 4, 4, 5, 5, 6, 6, 6, 7, 7, 8};
        int[] index = new int[]{0, 0, 1, 0, 1, 2, 0, 1, 0, 1, 2, 0, 1, 0, 1, 2, 0, 1, 0};
        for (int i = 0; i < 19; i++) {
            int x = cmpX(size, layer[i], index[i], x0, y0);
            int y = cmpY(size, layer[i], index[i], x0, y0);
            addUnitHexagon(canvas, size, x, y);
        }
    }
*/

/*

    */
/**
     *  UnitHexagon generator!
     *  Creates a UnitHexagon with given size in the given position (x0, y0) of the canvas.
     *//*

    public static void addUnitHexagon(TETile[][] canvas, int size, int x0, int y0) {
        TETile tile = randomTile();
        drawLowerHalfHexagon(canvas, tile, size, x0, y0);
        drawUpperHalfHexagon(canvas, tile, size, x0 - size + 1, y0 + size);
    }
*/

    /** Draw one line of the given length from a starting point P.
     * @param canvas the world to draw on
     * @param tile the tile to draw
     * @param length the number of tiles wide to draw
     * @param p the leftmost position of the row
     */
    private static void drawHorizontalLine(TETile[][] canvas, TETile tile, int length, Position p) {
        for(int x = 0; x < length; x++) {
            int xCoord = p.getX() + x;
            int yCoord = p.getY();
            canvas[xCoord][yCoord] = tile;
        }
    }

 /*   *//** Draw the lower half of a Unit Hexagon. From layer 0 to layer size - 1 *//*
    private static void drawLowerHalfHexagon(TETile[][] canvas, TETile tile, int size, int x0, int y0) {
        // literate through row y0 to row y0 + n
        for (int y = y0, x = x0, length = size; y < y0 + size; length+=2, x--, y++) {
            drawHorizontalLine(canvas, tile, length, x, y);
        }
    }

    *//** Draw the upper half of a Unit Hexagon. From layer size to 2*size - 1 *//*
    private static void drawUpperHalfHexagon(TETile[][] canvas, TETile tile, int size, int x0, int y0) {
        // iterate through row y0 to row y0 + n
        for (int y = y0, x = x0, len = 3*size-2; y < y0 + size; len -=2, x++, y++) {
            drawHorizontalLine(canvas, tile, len, x, y);
        }
    }*/

    /** Random Tile generator! Pick the following tile with equal chance.*/
    public static TETile randomTile() {
        int tileNum = RANDOM.nextInt(6);
        switch (tileNum) {
            case 0: return Tileset.TREE;
            case 1: return Tileset.WATER;
            case 2: return Tileset.FLOWER;
            case 3: return Tileset.GRASS;
            case 4: return Tileset.MOUNTAIN;
            case 5: return Tileset.WALL;
            default: return Tileset.NOTHING;
        }
    }

/*
    */
/** Position generator! *//*

    public static int cmpX(int size, int layer, int index, int x0, int y0) {
        int step = (4 * size - 2);
        switch (layer) {
            case 0: return x0 + index * step;
            case 1: return x0 - size - (size - 1) + index * step;
            case 2: return x0 - 2 * (2 * size - 1) + index * step;
            case 3: return x0 - (2*size - 1) + index * step;
            case 4: return x0 - 2* (2 * size - 1) + index * step;
            case 5: return x0 - (2 * size - 1) + index * step;
            case 6: return x0 - 2* (2 * size - 1) + index * step;
            case 7: return x0 - (2 * size - 1) + index * step;
            case 8: return x0;
            default: return -1;
        }
    }

    public static int cmpY(int size, int layer, int index, int x0, int y0) {
        return y0 + layer * size;
    }
*/

    /**
     * Computes the width of row i for a size s hexagon.
     * @param s The size of the hex.
     * @param i The row number where i = 0 is the bottom row.
     */
    public static int hexRowWidth(int s, int i) {
        int effectiveI = i;
        if (i >= s) {
            effectiveI = 2 * s - 1 - effectiveI;
        }
        return s + 2 * effectiveI;
    }

    /**
     * Computes relative x coordinate of the leftmost tile in the ith row
     * of a hexagon, assuming that the bottom row has an x-coordinate of zero.
     * For example, if s = 3, and i = 2, this function returns -2, because
     * the row 2 up from the bottom starts 2 to the left of the start position.
     * @param s size of the hexagon
     * @param i row num of the hexagon, where i = 0 is the bottom.
     * @return
     */
    public static int hexRowOffset(int s, int i) {
        int effectiveI = i;
        if (i >= s) {
            effectiveI = 2*s - 1 - effectiveI;
        }
        return -effectiveI;
    }
}
