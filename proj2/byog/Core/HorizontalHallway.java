package byog.Core;

import byog.TileEngine.TETile;

public class HorizontalHallway extends Room implements Hallway{
    public HorizontalHallway(int length, Position p, TETile wT, TETile fT) {
        super(length, 3, p, wT, fT);
    }
}
