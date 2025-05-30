package org.example.Ships;

import org.example.User.FieldGenerator;
import org.example.User.UserInput;
import org.example.User.UserOutput;

public class ShipDestroy {
    private Storage storage = new Storage();
    private final UserInput input = new UserInput();
    private final Checker checker = new Checker();
    private final FieldGenerator field = new FieldGenerator();

    private int[][] fieldAttack = new int[9][9];

    //Смена данных
    public void storageChange(Storage storage) {
        this.storage = storage;
    }

    /**
     *
     * @param fieldAttackCoordinates Координаты атаки
     * @return Возврат координат атаки
     */
    //Выбор координат атаки
    public int[][] coordinatesAttack(int[][] fieldAttackCoordinates) {
        fieldAttack = fieldAttackCoordinates;
        field.attackField(fieldAttack, storage);
        for (int i = 0; i < 5; i++) {
            int[] coordinate = input.shipsCoordinates();
            if (!checker.outOfFieldCheck(coordinate[1], coordinate[0])) continue;
            else if (storage.tableGetter()[coordinate[0]][coordinate[1]]) {
                fieldAttack[coordinate[0]][coordinate[1]] = 2;
                storage.destroyedShipsIncrement();
                i++;
            }
            else if (!storage.tableGetter()[coordinate[0]][coordinate[1]]) fieldAttack[coordinate[0]][coordinate[1]] = 1;
            field.attackField(fieldAttack, storage);
        }
        return fieldAttack;
    }

}
