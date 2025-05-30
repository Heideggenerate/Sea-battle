package org.example.Ships;

import org.example.User.FieldGenerator;
import org.example.User.UserInput;
import org.example.User.UserOutput;

public class ShipDestroy {
    private Storage storage = new Storage();
    private UserInput input = new UserInput();
    private UserOutput output = new UserOutput();
    private Checker checker = new Checker();
    private FieldGenerator field = new FieldGenerator();

    private int[][] fieldAttack = new int[9][9];

    public void storageChange(Storage storage) {
        this.storage = storage;
    }

    public int[][] coordinatesAttack(int[][] fieldAttackCoordinates) {
        fieldAttack = fieldAttackCoordinates;
        field.attackField(fieldAttack, storage);
        for (int i = 0; i < 5; i++) {
            int[] coordinate = input.shipsCoordinates();
            if (!checker.outOfFieldCheck(coordinate[1], coordinate[0])) continue;
            else if (storage.tableGetter()[coordinate[0]][coordinate[1]]) {
                fieldAttack[coordinate[0]][coordinate[1]] = 2;
                storage.destroyedShipsIncrement();
            }
            else if (!storage.tableGetter()[coordinate[0]][coordinate[1]]) fieldAttack[coordinate[0]][coordinate[1]] = 1;
            field.attackField(fieldAttack, storage);
        }
        return fieldAttack;
    }

}
