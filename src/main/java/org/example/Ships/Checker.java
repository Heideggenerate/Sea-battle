package org.example.Ships;

public class Checker {

    /**
     *
     * @param x Координаты части корабля по Х
     * @param y Координаты части корабля по У
     * @param data Данные игрока
     * @param headX Координаты головы корабля по Х
     * @param headY Координаты головы корабля по У
     * @param isCheckShipOnPlace ВКЛ/ВЫКЛ проверки, стоит ли на том же месте корабль
     * @return Возврат состояния проверки
     */
    //Проверка на то, стоит ли на этом месте корабль, в случае передачи true и проверка на то, занята ли область вокруг корабля
    public boolean coordinatesCheck(int x, int y, Storage data, int headX, int headY, boolean isCheckShipOnPlace) {
        if (!fieldNearShip(x, y, data, headX, headY) || !shipOnThisPlace(x, y, data, isCheckShipOnPlace)) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param x Координаты части корабля по Х
     * @param y Координаты части корабля по У
     * @param data Данные игрока
     * @param is ВКЛ/ВЫКЛ проверки, стоит ли на том же месте корабль
     * @return Возврат состояния проверки
     */
    //Проверка на то, стоит ли на этом месте корабль
    public boolean shipOnThisPlace(int x, int y, Storage data, boolean is) {
        if (is && data.tableGetter()[y][x]) return false;
        return true;
    }

    /**
     *
     * @param x Координаты части корабля по Х
     * @param y Координаты части корабля по У
     * @return Возврат состояния проверки
     */
    public boolean outOfFieldCheck(int x, int y) {
        if (x >= 9 || y >= 9 || x < 0 || y < 0) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param x Координаты части корабля по Х
     * @param y Координаты части корабля по У
     * @param data Данные игрока
     * @param headX Координаты головы корабля по Х
     * @param headY Координаты головы корабля по У
     * @param isCheckOnShipPlace ВКЛ/ВЫКЛ проверки, стоит ли на том же месте корабль
     * @return Возврат состояния проверки
     */
    //Проверка на то, стоит ли на этом месте корабль, в случае передачи true и проверка на то, занята ли область вокруг корабля, выход за поле
    public boolean mergedCheck(int x, int y, Storage data, int headX, int headY, boolean isCheckOnShipPlace) {
        if (!outOfFieldCheck(x, y) || !coordinatesCheck(x, y, data,  headX, headY, isCheckOnShipPlace)) return false;
        return true;
    }

    /**
     *
     * @param xS Координаты вокруг корабля по x
     * @param yS Координаты вокруг корабля по y
     * @param data Данные игрока
     * @param headX Координаты головы корабля по X
     * @param headY Координаты головы корабля по У
     * @return Возврат состояния проверки
     */
    //Проверка на то, попала ли новая часть корабля в область другого
    public boolean fieldNearShip(int xS, int yS, Storage data, int headX, int headY) {

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

    /**
     *
     * @param xS Координаты по X
     * @param yS Координаты по У
     * @param headX Координаты головы по Х
     * @param headY Координаты головы по Н
     * @return Возврат состояния проверки
     */
    //Проверка на то, находится ли второй блок корабля у головы, проверка на то, находится ли часть за полем
    public boolean correctShipSecondPlace(int xS, int yS, int headX, int headY) {
        if (((headY == yS - 1 || headY == yS + 1) && headX == xS) || (headX == xS - 1 || headX == xS + 1) && headY == yS) {
            if (!outOfFieldCheck(xS, yS)) return false;
            return true;
        }
        return false;
    }

    /**
     *
     * @param storage Данные игрока
     * @return Возврат состояния проверки
     */
    //Проверка на то, уничтожены ли все корабли
    public boolean isAllDestroyed(Storage storage) {
        if (storage.getDestroyedShips() == 10) return true;
        return false;
    }
}
