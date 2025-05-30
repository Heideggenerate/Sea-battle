package org.example.Ships;

import org.example.User.FieldGenerator;

public class HeadRandom {

    private Storage storage = new Storage();
    private final LengthCounter shipLength = new LengthCounter();
    private final Checker checker = new Checker();
    private final BuildAuto shipBuilder = new BuildAuto();
    private final FieldGenerator field = new FieldGenerator();

    //Смена данных
    public void playerData(Storage playerInfo) {
        storage = playerInfo;
    }

    //Генерация всех кораблей
    public Storage fourShips() {
        boolean isPlaced = false;
        while (!isPlaced) {
            storage.fieldClean(storage);
            for (int i = 0; i < 4; i++) {
                coordinatesGenerator(false);
            }
            shipLength.storageChange(storage);
            shipLength.isCanPlace(storage.tableGetter());
            shipBuilder.dataChange(storage);
            isPlaced = shipBuilder.shipSize();
        }
        field.dataChange(storage);
        field.tempFieldPrint();
        return storage;
    }

    /**
     *
     * @param isPlaced Поставлен ли корабль
     */
    //Рандомная генерация головы корабля
    public void coordinatesGenerator(boolean isPlaced) {
        int x = (int) (Math.random() * storage.XSIZE);
        int y = (int) (Math.random() * storage.YSIZE);
        while (!isPlaced) {
            x = (int) (Math.random() * storage.XSIZE);
            y = (int) (Math.random() * storage.YSIZE);
            isPlaced = checker.coordinatesCheck(x, y, storage, x, y, true);
        }
        storage.tableGive(x, y);
    }

}