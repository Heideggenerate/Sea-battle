package org.example.User;

public class UserOutput {
    public static void menu() {System.out.println("====Выберите режим игры====\n1.Классика\n2.Туман войны\n3.Салво");}

    //TODO: переставить варианты местами
    public static void menuTypeGame() {System.out.println("====Выберите тип расположения кораблей====\n1.Полностью ручное\n2.Частично ручное\n3.Автоматическое\n");}

    public static void shipsCoordinates() {System.out.println("\n====Введите координаты====");}

    public static void shipXY(int coorType) {
        switch (coorType) {
            case 1: System.out.println("\n\t\tX\n"); break;
            case 0: System.out.println("\n\t\tY\n"); break;
        }
    }

    public static void shipsType(int n) {
        switch (n) {
            case 0:
                System.out.println("\n====Выберите тип корабля===="); break;
            case 1:
                System.out.println("\n1.Однопалубный\n2.Двухпалубный\n3.Трёхпалубный\n4.Четырёхпалубный"); break;
            case 2:
                System.out.println("1.Однопалубный"); break;
            case 3:
                System.out.println("2.Двухпалубный"); break;
            case 4:
                System.out.println("3.Трёхпалубный"); break;
            case 5:
                System.out.println("4.Четырёхпалубный"); break;
        }
    }

    public static void field() {}

    public static void playerReady() {
        System.out.println("\nГотовы?\n");
    }

    public static void coordinatesError() {
        System.out.println("\n\n\nНеверные координаты!\n\n\n");
    }

    public static void typeError() {
        System.out.println("\n\n\nНеверный выбор корабля!\n\n\n");
    }

    public static void enters() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    public static void gameOver(int k) {
        System.out.print("\n\nИгра окончена! Победил игрок №");
        switch (k) {
            case 0: System.out.println('1'); break;
            case 1: System.out.println('2'); break;
        }
        System.out.println("\n\n");
    }

    public static void queue(int k) {
        enters();
        System.out.print("Очередь игрока №");
        switch(k) {
            case 0: System.out.println('1'); break;
            case 1: System.out.println('2'); break;
        }
        System.out.println("\n\n");
    }

    public static void sleeperEnters(boolean is) {
        int k = 0;
        if (is) k = 2000;
        try {Thread.sleep(6000 - k);} catch (Exception ex) {}
        enters();
        try {Thread.sleep(2500 - k);} catch (Exception ex) {}
    }
}
