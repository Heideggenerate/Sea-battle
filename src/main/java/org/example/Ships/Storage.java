package org.example.Ships;

public class Storage {

    public final int XSIZE = 9;
    public final int YSIZE = 9;

    private boolean[][] shipsOnTable = new boolean[YSIZE][XSIZE];

    /**
     *
     * @return Получение поля
     */
    //Получение поля
    public boolean[][] tableGetter() {
        return shipsOnTable;
    }

    /**
     *
     * @param x Координаты по Х
     * @param y Координаты по У
     */
    //Передача координат на поле
    public void tableGive(int x, int y) {
        shipsOnTable[y][x] = true;
    }

    /**
     *
     * @param data Данные игрока
     */
    //Очистка поля
    public void fieldClean(Storage data) {
        for (int i = 0; i < YSIZE; i++) {
            for (int j = 0; j < XSIZE; j++) {
                data.tableGetter()[i][j] = false;
            }
        }
    }


    private int[][] shipsSize = new int[4][4];

    /**
     *
     * @return Возврат размеров корабля
     */
    //Получение размеров кораблей
    public int[][] shipsSizeGetter() {return shipsSize;}

    /**
     *
     * @param num Номер корабля
     * @param direction Направление
     * @param value Значение
     */
    //Передача размера корабля
    public void shipsSizeGive(int num, int direction, int value) {shipsSize[num][direction] = value;}

    private int[][] shipsHeads = new int[4][2];

    /**
     *
     * @return Возврат голов кораблей
     */
    //Получение голов кораблей
    public int[][] shipHeadGetter() {return shipsHeads;}

    /**
     *
     * @param num Номер корабля
     * @param typeCoor Тип координаты (y, x)
     * @param value Значение
     */
    //Передача головы корабля
    public void shipHeadGive(int num, int typeCoor, int value) {
        shipsHeads[num][typeCoor] = value;
    }

    private int destroyedShipsCount = 0;

    //Количество уничтоженных кораблей
    public void destroyedShipsIncrement() {
        destroyedShipsCount += 1;
    }

    //получение количества уничтоженных кораблей
    public int getDestroyedShips() {
        return destroyedShipsCount;
    }

}
