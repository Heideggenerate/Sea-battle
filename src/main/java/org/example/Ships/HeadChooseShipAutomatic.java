package org.example.Ships;

import org.example.User.FieldGenerator;
import org.example.User.UserInput;
import org.example.User.UserOutput;

public class HeadChooseShipAutomatic {

    private StorageShips storage = new StorageShips();
    private final UserOutput output = new UserOutput();
    private final UserInput input = new UserInput();
    private final ShipPlaceCheck checker = new ShipPlaceCheck();
    private final ShipLengthCounter shipLength = new ShipLengthCounter();
    private final FulAutomaticlShipBuilder shipBuilder = new FulAutomaticlShipBuilder();
    //TODO: проблема в том, что, при вызове этого объекта, в этом объекте создаётся этот же класс haedcooseshipautomatic
    private final FieldGenerator field = new FieldGenerator();

    public void dataChange(StorageShips storage) {
        this.storage = storage;
    }

    public void headPlacer() {
        boolean isPlaced = false;
        field.arrayClear();
        field.tempFieldPrint();
        while (!isPlaced) {
            for (int i = 0; i < 4; i++) {
                int[] coordinates = new int[2];
                boolean canPlace = false;
                while (!canPlace) {
                    output.shipsCoordinates();
                    coordinates = input.shipsCoordinates();
                    canPlace = (checker.mergedCheck(coordinates[1], coordinates[0], storage, coordinates[1], coordinates[0], true));
                    if (!canPlace) output.coordinatesError();
                }
                int[][] fakeArr = new int[0][0];
                storage.shipOnTableGive(coordinates[1], coordinates[0]);
                field.tempField(coordinates[1], coordinates[0], i, true, fakeArr);
            }
                shipLength.storageChange(storage);
                shipLength.isCanPlace(storage.tableGetter());
                shipBuilder.dataChange(storage);
                isPlaced = shipBuilder.shipSize();
                if (!isPlaced) {
                    storage.fieldClean(storage);
                    field.arrayClear();
                    output.coordinatesError();
                }
        }
    }
}