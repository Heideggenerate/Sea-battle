package org.example.User;

import java.util.Scanner;

public class UserInput {

    private static UserOutput out = new UserOutput();

    public int[] shipsCoordinates() {
        int[] coordinates = new int[2];
        Scanner scan = new Scanner(System.in);
        out.shipXY(0);
        coordinates[0] = scan.nextInt() - 1;
        out.shipXY(1);
        coordinates[1] = scan.nextInt() - 1;
        return coordinates;
    }

    public static void gameType() {
        Scanner scan = new Scanner(System.in);
        int k = scan.nextInt();
        switch (k) {
            case 1:
            case 2:
            case 3:
        }
    }

    public static int input() {
        Scanner scan = new Scanner(System.in);;
        int k = scan.nextInt();
        return k;
    }
}
