package org.example.User;

import java.util.Scanner;

public class UserInput {
    public int[] shipsCoordinates() {
        int[] coordinates = new int[2];
        Scanner scan = new Scanner(System.in);
        UserOutput.shipXY(0);
        coordinates[0] = scan.nextInt() - 1;
        UserOutput.shipXY(1);
        coordinates[1] = scan.nextInt() - 1;
        return coordinates;
    }

    public static int input() {
        Scanner scan = new Scanner(System.in);;
        int k = scan.nextInt();
        return k;
    }
}
