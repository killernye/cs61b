package byog.Core;

import byog.TileEngine.TETile;

public class VerticalHallway extends Room implements Hallway{
    public VerticalHallway(int length, Position p, TETile wTile, TETile fTile) {
        super(3, length, p, wTile, fTile);
    }
}
