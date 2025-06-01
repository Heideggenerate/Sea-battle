package org.example.Ships;

import java.util.Arrays;

public class BuildAuto {
    private Storage storage = new Storage();
    private final Checker checker = new Checker();

    private int[][] sizerGet = storage.shipsSizeGetter();
    private int[][] headsGet = storage.shipHeadGetter();

    boolean check = true;
    private int randomIndex = 0;
    private int[][] coordinatesArray = new int[4][2];
    private boolean[] availableDirections = new boolean[4];

    //Замена данных
    public void dataChange(Storage data) {
        this.storage = data;
        this.sizerGet = data.shipsSizeGetter();
        this.headsGet = data.shipHeadGetter();
    }

    //Стартовая точка вычисления размера корабля
    public boolean shipSize() {
        for (int i = 1; i < headsGet.length; i++) {
            if (!shipBuilder(i)){
                return false;
            }
        }
        return true;
    }

    //Постройка корабля
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
            if(arr[0] != -1 && arr[1] != -1) storage.tableGive(arr[1], arr[0]);
            count++;
        }
        return true;
    }

    //Проверка возможности размещения
    public boolean coordinateCheck (int size) {
        int shift = directionCoordinatesCorrect(size);
        //TODO:Вынести в отдельную функцию
        boolean isY = false;
        if (Math.abs(shift) >= 10) {
            isY = true;
            shift /= 10;
        }int[][] arr = new int[4][2];
        for (int i = 0; i < arr.length; i++) Arrays.fill(arr[i], -1);
        int[] count = new int[1];
        if (isY) {
            if (shift < 0) for (int i = headsGet[size][0]; i <= headsGet[size][0] - shift; i++) {
                if (!coordinateChecker(headsGet[size][1], i, storage, headsGet[size][1], headsGet[size][0], arr, count)) return false;
            }
            else for (int i = headsGet[size][0] - shift; i < headsGet[size][0]; i++) {
                if (!coordinateChecker(headsGet[size][1], i, storage, headsGet[size][1], headsGet[size][0], arr, count)) return false;
            }
        }
        else {
            if (shift < 0) for (int i = headsGet[size][1] + shift; i < headsGet[size][1]; i++) {
                if (!coordinateChecker(i, headsGet[size][0], storage, headsGet[size][1], headsGet[size][0], arr, count)) return false;
            }
            else for (int i = headsGet[size][1]; i <= shift + headsGet[size][1]; i++) {
                if (!coordinateChecker(i, headsGet[size][0], storage, headsGet[size][1], headsGet[size][0], arr, count)) return false;
            }
        }
        coordinatesArray = arr;
        return true;
    }

    //TODO: Разгрузить логику coordinateCheck
    public boolean coordinateChecker(int x, int y, Storage storage, int headX, int headY, int[][] arr, int[] count) {
        if (!checker.mergedCheck(x, y, storage, headX, headY, false)) return false;
        else {
            arr[count[0]][0] = y;
            arr[count[0]][1] = x;
            count[0]++;
        }
        return true;
    }

    //Подгон расчётных координат под размеры корабля
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
            else end = size;
        }
        if (isY) return end *= 10;
        else return end;
    }

    //Подсчёт конечной точки
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

    //Рандомный выбор направления построения
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

    //Просмотр доступных направлений для постройки
    public boolean[] directionAvailable(int size) {
        for (int i = 0; i < availableDirections.length; i++) availableDirections[i] = false;
        for (int i = 0; i < sizerGet[size].length; i++) {
            if (sizerGet[size][i] >= size) availableDirections[i] = true;
        }
        if (!check) availableDirections[randomIndex] = false;
        return availableDirections;
    }
}
