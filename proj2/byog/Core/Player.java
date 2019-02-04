package byog.Core;

import byog.TileEngine.TETile;

public class Player {
    Position p;
    TETile pTile;

    public Player(Position p, TETile pTile) {
        this.p = p;
        this.pTile = pTile;
    }

    public void addPlayer(TETile[][] map) {
        map[p.x][p.y] = pTile;
    }

    public void setPosition(Position p) {
        this.p = p;
    }

    public boolean mayIgoThere(TETile[][] map, Position there, TETile safe) {
        if (map[there.x][there.y].equals(safe)) {
            return true;
        }
        return false;
    }

    public void movePosition(TETile[][] map, char move, TETile mayWalk) {
        Position destP;
        if (move == 'w') {
            destP = p.yOffsetPosition(1);
        } else if (move == 's') {
            destP = p.yOffsetPosition(-1);
        } else if (move == 'a') {
            destP = p.xOffsetPosition(-1);
        } else if (move == 'd') {
            destP = p.xOffsetPosition(1);
        } else {
            return;
        }
        if (mayIgoThere(map, destP, mayWalk)) {
            setPosition(destP);
        }
    }
}
