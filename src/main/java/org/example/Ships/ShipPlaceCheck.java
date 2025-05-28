package org.example.Ships;

public class ShipPlaceCheck {

    public boolean coordinatesCheck(int x, int y, StorageShips data, int headX, int headY, boolean isCheckShipOnPlace) {
        if (!fieldNearShip(x, y, data, headX, headY) || !shipOnThisPlace(x, y, data, isCheckShipOnPlace)) {
            return false;
        }
        return true;
    }

    public boolean shipOnThisPlace(int x, int y, StorageShips data, boolean is) {
        if (is && data.tableGetter()[y][x]) return false;
        return true;
    }

    public boolean outOfFieldCheck(int x, int y) {
        if (x >= 9 || y >= 9 || x < 0 || y < 0) {
            return false;
        }
        return true;
    }

    public boolean mergedCheck(int x, int y,StorageShips data, int headX, int headY, boolean isCheckOnShipPlace) {
        if (!outOfFieldCheck(x, y) || !coordinatesCheck(x, y, data,  headX, headY, isCheckOnShipPlace)) return false;
        return true;
    }

    public boolean fieldNearShip(int xS, int yS, StorageShips data, int headX, int headY) {

        for (int dy = yS - 1; dy <= yS + 1; dy++) {
            for (int dx = xS - 1; dx <= xS + 1; dx++) {
                if (dx == xS && dy == yS || dx == headX && dy == headY) continue;
                else if (dx < 0 || dx >= data.XSIZE || dy < 0 || dy >= data.YSIZE) continue;
                if (data.tableGetter()[dy][dx]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean correctShipSecondPlace(int xS, int yS, int headX, int headY) {
        if (((headY == yS - 1 || headY == yS + 1) && headX == xS) || (headX == xS - 1 || headX == xS + 1) && headY == yS) {
            if (!outOfFieldCheck(xS, yS)) return false;
            return true;
        }
        return false;
    }
}
