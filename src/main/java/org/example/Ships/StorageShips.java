package org.example.Ships;

public class StorageShips {

    public final int XSIZE = 9;
    public final int YSIZE = 9;

    private boolean[][] shipsOnTable = new boolean[YSIZE][XSIZE];

    public boolean[][] tableGetter() {
        return shipsOnTable;
    }

    public void shipOnTableGive(int x, int y) {
        shipsOnTable[y][x] = true;
    }

    public void fieldClean(StorageShips data) {
        for (int i = 0; i < YSIZE; i++) {
            for (int j = 0; j < XSIZE; j++) {
                data.tableGetter()[i][j] = false;
            }
        }
    }


    private int[][] shipsSize = new int[4][4];

    public int[][] shipsSizeGetter() {return shipsSize;}

    public void shipsSizeGive(int num, int direction, int value) {shipsSize[num][direction] = value;}

    private int[][] shipsHeads = new int[4][2];

    public int[][] shipHeadGetter() {return shipsHeads;}

    public void shipHeadGive(int num, int typeCoor, int value) {
        shipsHeads[num][typeCoor] = value;
    }

}
