package byog.Core;

/*
Coordinate position for a map.
 */
public class Position {
    int x;
    int y;

    Position(int xCoord, int yCoord) {
        x = xCoord;
        y = yCoord;
    }


    public Boolean inRoom(Room r) {
        int minX = r.leftBottomCorner().x;
        int maxX = r.rightBottomCorner().x;
        int minY = r.leftBottomCorner().y;
        int maxY = r.leftTopCorner().y;
        if (x >= minX && x <= maxX && y >= minY && y <= maxY) {
            return true;
        }
        return false;
    }

    /**
     * Computes the position which is xOffset units horizontally away from the current P.
     * @param xOffset horizontal relative distance from the current P.
     * @return Target position.
     */
    public Position xOffsetPosition (int xOffset) {
        int x = this.x + xOffset;
        int y = this.y;
        return new Position(x, y);
    }


    /**
     * Computes the position which is offset units vertically away from the current Position.
     * @param yOffset horizontal relative distance from the current P.
     * @return Target position.
     */
    public Position yOffsetPosition(int yOffset) {
        int x = this.x;
        int y = this.y + yOffset;
        return new Position(x, y);
    }

    /**
     * Computes the position which is xOffset units horizontally
     * and yOffset units vertically away from the current Position.
     * @param xOffset horizontal relative distance from the current Position.
     * @param yOffset vertical relative distance from the current Position.
     * @return Target position.
     */
    public Position xyOffsetPosition(int xOffset, int yOffset) {
        int x = this.x + xOffset;
        int y = this.y + yOffset;
        return new Position(x, y);
    }
}
