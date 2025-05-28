package org.example.Ships;

import org.example.User.*;

import java.util.Arrays;

public class FullManualBuilder {

    StorageShips storage = new StorageShips();
    UserOutput output = new UserOutput();
    UserInput input = new UserInput();
    FieldGenerator field = new FieldGenerator();
    ShipPlaceCheck checker = new ShipPlaceCheck();

    private boolean[] usedShips = new boolean[4];
    private int[] lastCoordinates = new int[2];
    private int[][] tempStorage = new int[4][2];
    public void storageChange(StorageShips storage) {
        this.storage = storage;
    }

    public void partGenerate() {
        tempStorageClear();
        field.arrayClear();
        output.shipsType(0);
        output.shipsType(1);
        field.dataChange(storage);
        for (int i = 0; i < 4; i++) {
            partPlacer();
            output.shipsType(0);
            for (int j = 0; j < usedShips.length; j++) if (!usedShips[j]) output.shipsType(j + 2);
        }
    }

    public boolean partPlacer() {
        int in = input.input();
        for (int i = 0; i < usedShips.length; i++) if ((usedShips[i] && in == i + 1) || (in < 1 || in > 4)) {
            output.typeError();
            return false;
        }
       
        switch (in) {
            case 1: onePart(); usedShips[0] = true; break;
            case 2: twoParts(); usedShips[1] = true; break;
            case 3: threeParts(); usedShips[2] = true; break;
            case 4: fourParts(); usedShips[3] = true; break;
        }
        return true;
    }

    public void onePart() {
        boolean isPlaced = true;
        do {
            if (!isPlaced) {
                output.coordinatesError();
                field.tempField(0, 0, 0, false, tempStorage);
            }
            isPlaced = partGenerateFunction(0, 0);
        }
        while (!isPlaced);
        tempStorageClear();
    }

    public void twoParts() {
        boolean isPlaced = true;
        do {
            if (!isPlaced) {
                output.coordinatesError();
                field.tempField(0, 0, 0, false, tempStorage);
            }
            for (int i = 0; i < 2; i++) {
                isPlaced = partGenerateFunction(1, i);
                if (!isPlaced) break;
            }
        }
        while(!isPlaced);
        tempStorageClear();
    }

    public void threeParts() {
        boolean isPlaced = true;
        do {
            if (!isPlaced) {
                output.coordinatesError();
                field.tempField(0, 0, 0, false, tempStorage);
            }
            for (int i = 0; i < 3; i++) {
                isPlaced = partGenerateFunction(2, i);
                if (!isPlaced) break;
            }
        }
        while (!isPlaced);
        tempStorageClear();
    }

    public void fourParts() {
        boolean isPlaced = true;
       do {
           if (!isPlaced) {
               output.coordinatesError();
               field.tempField(0, 0, 0, false, tempStorage);
           }
           for (int i = 0; i < 3; i++) {
               isPlaced = partGenerateFunction(3, i);
               if (!isPlaced) break;
           }
       }
       while (!isPlaced);
       tempStorageClear();
    }

    public boolean partGenerateFunction(int number, int count) {
        int[] coordinates = new int[2];
        int[][] fakeArr = new int[0][0];
        boolean isPlaced = true;
        if (count < 2) {
            output.shipsCoordinates();
            coordinates = input.shipsCoordinates();
        }
        if (count == 0 && checker.mergedCheck(coordinates[1], coordinates[0], storage, -2, -2, true)) {
            storage.shipHeadGive(number, 0, coordinates[0]);
            storage.shipHeadGive(number, 1, coordinates[1]);
            field.tempField(coordinates[1], coordinates[0], number, true, fakeArr);
            tempStorage[0][0] = coordinates[0]; tempStorage[0][1] = coordinates[1];
            lastCoordinates[0] = coordinates[0];
            lastCoordinates[1] = coordinates[1];
        }
        else if (count == 1 && checker.mergedCheck(coordinates[1], coordinates[0], storage, storage.shipHeadGetter()[number][1],storage.shipHeadGetter()[number][0] , true)) {
            if (checker.correctShipSecondPlace(coordinates[1], coordinates[0], storage.shipHeadGetter()[number][1], storage.shipHeadGetter()[number][0])) {
                field.tempField(coordinates[1], coordinates[0], number, true, fakeArr);
                tempStorage[1][0] = coordinates[0]; tempStorage[1][1] = coordinates[1];
                lastCoordinates[0] = coordinates[0];
                lastCoordinates[1] = coordinates[1];
            }
            else {
                return false;
            }
        }

        else if (!checker.mergedCheck(lastCoordinates[1], lastCoordinates[0], storage, storage.shipHeadGetter()[number][1], storage.shipHeadGetter()[number][0], true) && count <= 1) return false;
        else if (count > 1) {
            if (number - 2 >= 0) isPlaced = shipCompleter(number, lastCoordinates[1], lastCoordinates[0]);
        }
        return isPlaced;
    }

    public boolean shipCompleter(int size, int x, int y) {
        boolean xLeft = directionCalculate(storage.shipHeadGetter()[size][1], storage.shipHeadGetter()[size][0], x, y)[1];
        boolean xRight = directionCalculate(storage.shipHeadGetter()[size][1], storage.shipHeadGetter()[size][0], x, y)[2];
        boolean yUp = directionCalculate(storage.shipHeadGetter()[size][1], storage.shipHeadGetter()[size][0], x, y)[0];
        int previousX = lastCoordinates[1];
        int previousY = lastCoordinates[0];
        int[][] fakeArr = new int[0][0];
        for (int i = 1; i < size; i++) {
            if (xLeft) {
                if (!checker.mergedCheck(x - i, y, storage, previousX, y, false))
                    return false;
                field.tempField(x - i, y, size, true, fakeArr);
                tempStorage[i][0] = y; tempStorage[size][1] = x - i;
                 previousX = x - i;
            }
            else if (yUp) {
                if (!checker.mergedCheck(x, y - i, storage, x, previousY, false))
                    return false;
                field.tempField(x, y - i, size, true, fakeArr);
                tempStorage[i][0] = y - i; tempStorage[size][1] = x;
                 previousY = y - i;
            }
            else if (xRight) {
                if (!checker.mergedCheck(x + i, y, storage, previousX, y, false))
                    return false;
                field.tempField(x + i, y, size, true, fakeArr);
                tempStorage[i][0] = y; tempStorage[size][1] = x + i;
                previousX = x + i;
            }
            else if (!xRight && !yUp && !xLeft){
                //TODO: исправить проблему с построением вниз
                if (!checker.mergedCheck(x, y + i, storage, x, previousY, false))
                    return false;
                field.tempField(x, y + i, size, true, fakeArr);
                tempStorage[i][1] = x; tempStorage[size][0] = y + i;
                 previousY = y + i;
            }
        }
        return true;    
    }

    public boolean[] directionCalculate(int headX, int headY, int x, int y) {
        boolean[] direction = new boolean[3];
        if (headX > x) direction[1] = true;
        else if (headY > y) direction[0] = true;
        else if (headX < x) direction[2] = true;
        return direction;
    }

    public void tempStorageClear () {
        for (int i = 0; i < tempStorage.length; i++) {
            Arrays.fill(tempStorage[i], -1);
        }
    }
}
