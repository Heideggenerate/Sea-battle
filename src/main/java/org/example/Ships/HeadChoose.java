package org.example.Ships;

import org.example.User.FieldGenerator;
import org.example.User.UserInput;
import org.example.User.UserOutput;

public class HeadChoose {

    private Storage storage = new Storage();
    private final UserInput input = new UserInput();
    private final Checker checker = new Checker();
    private final LengthCounter shipLength = new LengthCounter();
    private final BuildAuto shipBuilder = new BuildAuto();
    private final FieldGenerator field = new FieldGenerator();

    //Смена данных
    public void dataChange(Storage storage) {
        this.storage = storage;
    }

    //Выбор координат головы корабля
    public Storage headPlacer() {
        boolean isPlaced = false;
        field.arrayClear();
        field.tempFieldPrint();
        while (!isPlaced) {
            for (int i = 0; i < 4; i++) {
                int[] coordinates = new int[2];
                boolean canPlace = false;
                while (!canPlace) {
                    UserOutput.shipsCoordinates();
                    coordinates = input.shipsCoordinates();
                    canPlace = (checker.mergedCheck(coordinates[1], coordinates[0], storage, coordinates[1], coordinates[0], true));
                    if (!canPlace) {
                        UserOutput.coordinatesError();
                    }
                }
                int[][] fakeArr = new int[0][0];
                storage.tableGive(coordinates[1], coordinates[0]);
                field.tempField(coordinates[1], coordinates[0], i, true, fakeArr);
            }
                shipLength.storageChange(storage);
                shipLength.isCanPlace(storage.tableGetter());
                shipBuilder.dataChange(storage);
                isPlaced = shipBuilder.shipSize();
                if (!isPlaced) {
                    storage.fieldClean(storage);
                    field.arrayClear();
                    UserOutput.coordinatesError();
                }
        }
        field.dataChange(storage);
        field.tempFieldPrint();
        return storage;
    }
}