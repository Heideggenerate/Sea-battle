package org.example.Logic;

import org.example.Ships.Checker;
import org.example.Ships.ShipDestroy;
import org.example.Ships.Storage;
import org.example.User.FieldGenerator;
import org.example.User.UserInput;
import org.example.User.UserOutput;

import java.util.Arrays;


public class FightAI {

    private Storage storage = new Storage();
    private UserInput input = new UserInput();
    private UserOutput output = new UserOutput();
    private Checker check = new Checker();
    private FieldGenerator field = new FieldGenerator();
    private ShipDestroy destroy = new ShipDestroy();

    private int[][] directions = new int[4][2];
    int[][] fieldAttackUser = new int[9][9];
    int[][] fieldAttackAI = new int[9][9];
    private int[] startCoordinates = new int[2];

    //Очистка направлений
    public void directionsReset() {
        for (int i = 0; i < directions.length; i++) {
            Arrays.fill(directions[i], 10);
        }
    }

    public void fieldsReset() {
        for (int i = 0; i < storage.XSIZE; i++) {
            Arrays.fill(fieldAttackAI[i], 0); Arrays.fill(fieldAttackUser[i], 0);
        }
    }

    public boolean directionsCheck(int n, Storage info) {
        if (check.outOfFieldCheck(directions[n][1], directions[n][0]) && !check.shipOnThisPlace(directions[n][1], directions[n][0], info, true)) return true;
        return false;
    }

    public void fillNearCoordinates(int x, int y) {
        directions[0][0] = y + 1; directions[0][1] = x;
        directions[1][0] = y - 1; directions[1][1] = x;
        directions[2][0] = y; directions[2][1] = x - 1;
        directions[3][0] = y; directions[3][1] = x + 1;
    }

    public void randomAttack() {
        int[] coordinates = new int[2];
        boolean randomed = false;
        while (!randomed) {
            coordinates[1] = (int) (Math.random() * storage.XSIZE);
            coordinates[0] = (int) (Math.random() * storage.YSIZE);
            if (fieldAttackAI[coordinates[0]][coordinates[1]] != 1) randomed = true;
        }
        startCoordinates = coordinates;
    }

    public void fight(Storage[] playerInfo) {
        directionsReset();
        fieldsReset();
        destroy.storageChange(playerInfo[0]);
        while (!check.isAllDestroyed(playerInfo[0]) && !check.isAllDestroyed(playerInfo[1])) {
            fightUser(playerInfo[1]);
            fightAI(playerInfo[0]);
            output.sleeperEnters(false);
            field.attackField(fieldAttackAI, playerInfo[0]);
        }
    }

    public void fightUser(Storage playerSecond) {
        destroy.coordinatesAttack(fieldAttackUser);
    }

    //Логика боя ИИ
    public void fightAI(Storage playerFirst) {
        boolean firstAttack = false;
        boolean secondAttack = false;
        for (int i = 0, j = 0; i < 5; i++) {
            if (!firstAttack) {
                randomAttack();
                if (check.outOfFieldCheck(startCoordinates[1], startCoordinates[0]) && !check.shipOnThisPlace(startCoordinates[1], startCoordinates[0], playerFirst, true)) {
                    firstAttack = true;
                    if (check.outOfFieldCheck(startCoordinates[1], startCoordinates[0]) && fieldAttackAI[startCoordinates[0]][startCoordinates[1]] != 2) {
                        playerFirst.destroyedShipsIncrement();
                        fieldAttackAI[startCoordinates[0]][startCoordinates[1]] = 2;
                    }
                    fillNearCoordinates(startCoordinates[1], startCoordinates[0]);
                }
                else continue;
            }
            else {
                if (!secondAttack && directionsCheck(j, playerFirst)) {
                    secondAttack = true;
                    if (check.outOfFieldCheck(directions[j][1], directions[j][0]) && fieldAttackAI[directions[j][0]][directions[j][1]] != 2) {
                        playerFirst.destroyedShipsIncrement();
                        fieldAttackAI[directions[j][0]][directions[j][1]] = 2;
                    }
                    j++;
                }
                if (secondAttack) {
                    calculateDirection(directions[j], playerFirst);
                }
            }
        }
    }

    //TODO: Будет бить сначала влево на 4 клетки, потом вправо на 4 клетки (Вертикально по аналогии). В случае, если будет промах по этим клеткам, заканчиваем ход и переходим к рандому
    //TODO: доделать просчёт добивания
    //TODO: Добавить то, что бот не попал, directions = 1
    public void calculateDirection(int[] direction, Storage info) {
        boolean vertical = false;
        if (direction[0] - startCoordinates[0] != 0) {
            vertical = true;
        }
        move(vertical, true);
        for (int i = 0; i < 3; i++) {
            if (directionsCheck(i, info)) {
                if ((check.outOfFieldCheck(directions[i][1], directions[i][0]) && fieldAttackAI[directions[i][0]][directions[i][1]] != 2)) {
                    info.destroyedShipsIncrement();
                    fieldAttackAI[directions[i][0]][directions[i][1]] = 2;
                }
            }
            else  {
                if (check.outOfFieldCheck(directions[i][1], directions[i][0])) fieldAttackAI[directions[i][0]][directions[i][1]] = 1;
                break;
            }
        }
        move(vertical, false);
        for (int i = 0; i < 3; i++) {
            if (directionsCheck(i, info) && (check.outOfFieldCheck(directions[i][1], directions[i][0]))) {
                if (fieldAttackAI[directions[i][0]][directions[i][1]] != 2) {
                    info.destroyedShipsIncrement();
                    fieldAttackAI[directions[i][0]][directions[i][1]] = 2;
                }
            }
            else {
                if (check.outOfFieldCheck(directions[i][1], directions[i][0])) fieldAttackAI[directions[i][0]][directions[i][1]] = 1;
                break;
            }
        }
    }

    public void move(boolean vertical, boolean plus) {
        if (vertical) {
            for (int i = 0; i < directions.length; i++) {
                if (plus) {
                    directions[i][0] = startCoordinates[0] + i;
                    directions[i][1] = startCoordinates[1];
                }
                else {
                    directions[i][0] = startCoordinates[0] - i;
                    directions[i][1] = startCoordinates[1];
                }
            }
        }
        else {
            for (int i = 0; i < directions.length; i++) {
                if (plus) {
                    directions[i][0] = startCoordinates[0];
                    directions[i][1] = startCoordinates[1] + i;
                }
                else {
                    directions[i][0] = startCoordinates[0];
                    directions[i][1] = startCoordinates[1] - i;
                }
            }
        }
    }

}
