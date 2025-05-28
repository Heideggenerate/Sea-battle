package org.example.Ships;

public class ShipLengthCounter {

    private StorageShips storage = new StorageShips();

    public void storageChange(StorageShips storage) {
        this.storage = storage;
    }

    public void isCanPlace(boolean[][] data) {
        int[][] shipsHeads = storage.shipHeadGetter();

        int k = 0;
        int r = 0;
        int d = 0;
        int u = 3;

        for (int i = 0; i < storage.YSIZE; i++) {
            boolean right = false;
            int tempLeft = 0;
            int tempRight = 0;
            for (int j = 0; j < storage.XSIZE; j++) {
                if (data[i][j]) {
                    storage.shipHeadGive(k, 0, i);
                    storage.shipHeadGive(k, 1, j);
                    storage.shipsSizeGive(k, 2, j - tempLeft);
                    if (right) {
                        storage.shipsSizeGive(r, 3, j - tempRight - 1);
                        r++;
                        right = false;
                    }
                    tempLeft = j;
                    tempRight = j;
                    right = true;
                    k++;
                }
                if (j == 8) {
                    if (right) {
                        storage.shipsSizeGive(r, 3, j - tempRight);
                        r++;
                        right = false;
                    }
                }
            }
        }

        for (int i = shipsHeads.length - 1; i >= 0; i--) {
            boolean isPlaced = false;
            for (int j = 0; j < i; j++) {
                if (shipsHeads[i][1] == shipsHeads[j][1] && i != j) {
                    storage.shipsSizeGive(u--, 0, shipsHeads[i][0] - shipsHeads[j][0] - 1);
                    isPlaced = true;
                    break;
                }
            }
            if (!isPlaced) {
                storage.shipsSizeGive(u--, 0, shipsHeads[i][0]);
            }
        }

        for (int i = 0; i < shipsHeads.length; i++) {
            boolean isPlaced = false;
            for (int j = i; j < shipsHeads.length; j++) {
                if (shipsHeads[i][1] == shipsHeads[j][1] && i != j) {
                    storage.shipsSizeGive(d++, 1, shipsHeads[j][0] - shipsHeads[i][0] - 1);
                    isPlaced = true;
                    break;
                }
            }
            if (!isPlaced) {
                storage.shipsSizeGive(d++, 1, 8 - shipsHeads[i][0]);
            }
        }
    }
}