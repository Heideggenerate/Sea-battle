package org.example.User;

import org.example.Ships.*;

import java.util.Arrays;

public class FieldGenerator {

    private int[][] tempField = new int[4][2];
    private Storage data = new Storage();

    public void dataChange(Storage storage) {
        data = storage;
    }
    //TODO: сделать вывод универсальнее

    public void arrayClear() {
        for (int[] arr : tempField) {
            Arrays.fill(arr, -1);
        }
    }


    public void attackField(int[][] coordinates, Storage storage) {
        for (int i = 0; i < storage.YSIZE; i++) {
            for (int j = 0; j < storage.XSIZE; j++) {
                if (storage.tableGetter()[i][j] == true) {
                    if (coordinates[i][j] == 2) {
                        System.out.print('⊞');
                    }
                    else System.out.print('□');
                } else {
                    if (coordinates[i][j] == 1) System.out.print('⊡');
                    else if (coordinates[i][j] == 0) System.out.print('□');
                }
            }
            System.out.println();
        }
     }

    public void tempField (int x, int y, int number, boolean add, int[][] coordinates) {
        tempField[number][0] = y;
        tempField[number][1] = x;
        if (add) {
            for (int i = 0; i < tempField.length; i++) {
                for (int j = 0; j < tempField[i].length; j++) {
                    if (tempField[i][j] != -1) data.tableGive(tempField[i][1], tempField[i][0]);
                }
            }
        }
        else {
            for (int i = 0; i < coordinates.length; i++) {
                    if (coordinates[i][0] != -1 && coordinates[i][1] != -1)
                        data.tableGetter()[coordinates[i][0]][coordinates[i][1]] = false;
            }
        }
        tempFieldPrint();
        }

     //TODO: убрать, после универсализации поля
     public void tempFieldPrint() {
         for (int i = 0; i < data.YSIZE; i++) {
             for (int j = 0; j < data.XSIZE; j++) {
                 if (data.tableGetter()[i][j]) {
                     System.out.print('■');
                 }
                 else {
                     System.out.print('□');
                 }
             }
             System.out.println();
         }
         System.out.println("\n\n\n\n\n");
     }
}
