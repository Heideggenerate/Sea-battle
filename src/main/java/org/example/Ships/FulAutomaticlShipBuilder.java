package org.example.Ships;

import java.util.Arrays;

public class FulAutomaticlShipBuilder {
    private StorageShips data = new StorageShips();
    private ShipPlaceCheck checker = new ShipPlaceCheck();

    private int[][] sizerGet = data.shipsSizeGetter();
    private int[][] headsGet = data.shipHeadGetter();

    boolean check = true;
    private int randomIndex = 0;
    private int[][] coordinatesArray = new int[4][2];
    private boolean[] availableDirections = new boolean[4];

    public void dataChange(StorageShips data) {
        this.data = data;
        this.sizerGet = data.shipsSizeGetter();
        this.headsGet = data.shipHeadGetter();
    }

    public boolean shipSize() {
        for (int i = 1; i < headsGet.length; i++) {
            if (!shipBuilder(i)){
                return false;
            }
        }
        return true;
    }

    public boolean shipBuilder(int size) {
        check = true;
        boolean isPlaced = false;
        int attempts = 0;
        while (!isPlaced) {
            isPlaced = coordinateCheck(size);
            if (!isPlaced) {
                check = false;
                attempts++;
                availableDirections[randomIndex] = false;
                if (attempts == 100) return false;
            }
        }
      int count = 0;
        for (int[] arr : coordinatesArray) {
            if(arr[0] != -1 && arr[1] != -1) data.shipOnTableGive(arr[1], arr[0]);
            count++;
        }
        return true;
    }

    public boolean coordinateCheck (int size) {
        int shift = directionCoordinatesCorrect(size);
        //TODO:Вынести в отдельную функцию
        boolean isY = false;
        if (Math.abs(shift) >= 10) {
            isY = true;
            shift /= 10;
        }
        int[][] arr = new int[4][2];
        for (int i = 0; i < arr.length; i++) Arrays.fill(arr[i], -1);
        int count = 0;
        if (isY) {
            if (shift < 0) for (int i = headsGet[size][0]; i <= headsGet[size][0] - shift; i++) {
                if (!checker.mergedCheck(headsGet[size][1], i, data, headsGet[size][1], headsGet[size][0], false)) return false;
                else {
                    arr[count][0] = i;
                    arr[count][1] = headsGet[size][1];
                    count++;
                }
            }
            else for (int i = headsGet[size][0] - shift; i < headsGet[size][0]; i++) {
                if (!checker.mergedCheck(headsGet[size][1], i, data, headsGet[size][1], headsGet[size][0], false)) return false;
                else {
                    arr[count][0] = i;
                    arr[count][1] = headsGet[size][1];
                    count++;
                }
            }
        }
        else {
            if (shift < 0) for (int i = headsGet[size][1] + shift; i < headsGet[size][1]; i++) {
                if (!checker.mergedCheck(i, headsGet[size][0], data, headsGet[size][1], headsGet[size][0], false)) return false;
                else {
                    arr[count][0] = headsGet[size][0];
                    arr[count][1] = i;
                    count++;
                }
            }
            else for (int i = headsGet[size][1]; i <= shift + headsGet[size][1]; i++) {
                if (!checker.mergedCheck(i, headsGet[size][0], data, headsGet[size][1], headsGet[size][0], false)) return false;
                else {
                    arr[count][0] = headsGet[size][0];
                    arr[count][1] = i;
                    count++;
                }
            }
        }
        coordinatesArray = arr;
        return true;
    }


    public int directionCoordinatesCorrect(int size) {
        //TODO:Вынести в отдельную функцию
        boolean isY = false;
        int end = endCoordinateCalculate(size);
        if (Math.abs(end) >= 10) {
            isY = true;
            end /= 10;
        }
        if (end == -404) System.out.println("Проблема с подсчётом конечной координаты");

        if (Math.abs(end) != size) {
            if (end < 0) end += Math.abs(end) - size;
            else end -= end - size;
        }
        if (isY) return end *= 10;
        else return end;
    }

    public int endCoordinateCalculate(int size) {
        int headY = headsGet[size][0];
        int headX = headsGet[size][1];
        int tempY = 0;
        int tempX = 0;
        int index = randomDirection(size);
        switch(index) {
            case -1: System.out.println("Ошибка с рандомайзером"); break;
            case 0: tempY += sizerGet[size][index]; break;
            case 1: tempY -= sizerGet[size][index]; break;
            case 2: tempX -= sizerGet[size][index]; break;
            case 3: tempX += sizerGet[size][index]; break;
        }
        if (tempY != 0) return tempY * 10;
        else if (tempX != 0) return tempX;
        return -404;
    }

    public int randomDirection(int size) {
        int count = 0;
        int total = 0;
        boolean[] direction = directionAvailable(size);
        for (boolean n : direction) if (n) count++;
        //Проблема с рандомом
        int random = (int) (Math.random() * count);
        if (count == 1 && !direction[0] && !direction[1] && !direction[2] && direction[3]) {
            return 3;
        };
        for (int i = 0; i < direction.length; i++) {
            if (total == random) {
                randomIndex = i;
                return i;
                //
            }
            if (direction[i]) {
                total++;
                if (total == random) {
                    randomIndex = i;
                    return i;
                }
            }
        }
        return -1;
    }

    public boolean[] directionAvailable(int size) {
        for (int i = 0; i < availableDirections.length; i++) availableDirections[i] = false;
        for (int i = 0; i < sizerGet[size].length; i++) {
            if (sizerGet[size][i] >= size) availableDirections[i] = true;
        }
        if (!check) availableDirections[randomIndex] = false;
        return availableDirections;
    }
}
