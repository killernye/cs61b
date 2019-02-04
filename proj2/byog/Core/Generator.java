package byog.Core;

import javax.swing.*;
import java.util.LinkedList;
import java.util.Random;
import java.util.Arrays;

// Generates every detail of the dungeon before drawing it.
public class Generator {
    // seems don't need any instance variable
    MapGeneratorParameters mgp;
    LinkedList<Room> rooms = new LinkedList<>();
    LinkedList<Position> cuts = new LinkedList<>();
    Player p1;
    // generate random dungeon

    public Generator(MapGeneratorParameters m) {
        mgp = m;
    }

    public WorldState generate() {
        // create a random room
        Room start = generateStartRoom();
        rooms.add(start);
        // branch off a room from the previous room recursively until ..
        explode(start);

//        try {

//        } catch (RuntimeException e) {
//            System.out.println(e);
//        }
        // return the whole detail in a world state instance
        return new WorldState(mgp, rooms, cuts, p1);
    }

    /** Generates a random room. **/
    public Room generateStartRoom() {
        int w = 8;
        int h = 8;
        int x = 40;
        int y = 15;
        Position p = new Position(x, y);
        Room r = new Room(w, h, p, mgp.wTile, mgp.fTile);
        p1 = new Player(p.xyOffsetPosition(1, 1), mgp.pTile);
        return r;
    }

    public Room generateRandomRoom(int side, Position cut) {
        int w = RandomUtils.uniform(mgp.random, 3, 8);
        int h = RandomUtils.uniform(mgp.random, 3, 8);
        int xOffset, yOffset;
        Position p;
        switch(side) {
            case 0 : {
                xOffset = 1 - w;
                yOffset = -RandomUtils.uniform(mgp.random, 1, h - 1);
                p = cut.xyOffsetPosition(xOffset, yOffset);
                break;
            }
            case 1: {
                xOffset = -RandomUtils.uniform(mgp.random, 1, w - 1);
                p = cut.xOffsetPosition(xOffset);
                break;
            }
            case 2: {
                yOffset = -RandomUtils.uniform(mgp.random, 1, h - 1);
                p = cut.yOffsetPosition(yOffset);
                break;
            }
            case 3: {
                xOffset = -RandomUtils.uniform(mgp.random, 1, w - 1);
                yOffset = 1 - h;
                p = cut.xyOffsetPosition(xOffset, yOffset);
                break;
            }
            default: p = null;
        }

        Room r = new Room(w, h, p, mgp.wTile, mgp.fTile);
        return r;
    }

    /** Explode from a seed!
     *  Extend at every possible direction of room.
     */
    public void explode(Room seed) {
        LinkedList<Room> branches = branchOff(seed);
        rooms.addAll(branches);
        for (Room branch: branches) {
            explode(branch);
        }
    }

    /** Branch off a new room from a room. */
    public LinkedList<Room> branchOff(Room r) {
        LinkedList<Room> returnBranches = new LinkedList<>();
        LinkedList<Integer> sides = new LinkedList<>();
        sides.addAll(Arrays.asList(0, 1, 2, 3));

        for (int side : sides) {
            Room branch = branchOff(r, side);
            if (branch != null) {
                returnBranches.add(branch);
            }
        }

//        throw new RuntimeException("There is no space for another room.");
        return returnBranches;
    }




    /** Branch off a new room from a existing room on a certain side. */
    private Room branchOff(Room r, int side) {
//        int side = RandomUtils.uniform(mgp.random, 4);
        Position cut, newCut;
        Room returnRoom;
        int cutX, cutY;
        switch(side) {
            // left side
            case 0: {
                cutX = r.leftTopCorner().x;
                cutY = RandomUtils.uniform(mgp.random, r.leftBottomCorner().y + 1, r.leftTopCorner().y);
                cut = new Position(cutX, cutY);
                newCut = cut.xOffsetPosition(-1);
                returnRoom = generateRandomRoom(0, newCut);
                break;
            }
            // up side
            case 1: {
                cutX = RandomUtils.uniform(mgp.random, r.leftTopCorner().x + 1, r.rightTopCorner().x);
                cutY = r.leftTopCorner().y;
                cut = new Position(cutX, cutY);
                newCut = cut.yOffsetPosition(1);
                returnRoom = generateRandomRoom(1, newCut);
                break;
            }
            // right side
            case 2: {
                cutX = r.rightBottomCorner().x;
                cutY = RandomUtils.uniform(mgp.random, r.rightBottomCorner().y + 1, r.rightTopCorner().y);
                cut = new Position(cutX, cutY);
                newCut = cut.xOffsetPosition(1);
                returnRoom = generateRandomRoom(2, newCut);
                break;
            }
            // down side
            case 3: {
                cutX = RandomUtils.uniform(mgp.random, r.leftBottomCorner().x + 1, r.rightBottomCorner().x);
                cutY = r.leftBottomCorner().y;
                cut = new Position(cutX, cutY);
                newCut = cut.yOffsetPosition(-1);
                returnRoom = generateRandomRoom(3, newCut);
                break;
            }
            default: returnRoom = null;
                     newCut = null;
                     cut = null;
                     break;
        }
        if (returnRoom.overlap(rooms) || returnRoom.outOfBorder(mgp)) {
            returnRoom = null;
        } else {
            cuts.add(newCut);
            cuts.add(cut);
        }
        return returnRoom;
    }


}
