package org.example.Ships;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CheckerTest {

    @Mock
    private Storage storageMock;

    @Spy
    @InjectMocks
    private Checker checkerInject;

    @Test
    void testIsAllDestroyed_isShipsDestroyedCheck_verify() {
        int destroyedShipsCount = 10;
        Mockito.when(storageMock.getDestroyedShips()).thenReturn(destroyedShipsCount);
        boolean isDestroyed = checkerInject.isAllDestroyed(storageMock);
        Assertions.assertTrue(isDestroyed);
    }

    @Test
    void testIsAllDestroyed_isShipsDestroyedCheck_verifyFall() {
        int destroyedShipsCount = 4;
        Mockito.when(storageMock.getDestroyedShips()).thenReturn(destroyedShipsCount);
        boolean isDestroyed = checkerInject.isAllDestroyed(storageMock);
        Assertions.assertFalse(isDestroyed);
    }

    @Test
    void testIsAllDestroyed_isShipsDestroyedCheckTwo_verifyFall() {
        int destroyedShipsCount = -2;
        Mockito.when(storageMock.getDestroyedShips()).thenReturn(destroyedShipsCount);
        boolean isDestroyed = checkerInject.isAllDestroyed(storageMock);
        Assertions.assertFalse(isDestroyed);
    }

    @Test
    void testCorrectShipSecondPlace_secondPartOnRightPlace_assertTrue() {

        int headY = 2;
        int headX = 2;
        int x = 3;
        int y = 2;
        Mockito.when(checkerInject.outOfFieldCheck(x, y)).thenReturn(true);
        boolean isDestroyed = checkerInject.correctShipSecondPlace(x, y, headX, headY);
        Assertions.assertTrue(isDestroyed);
    }

    @Test
    void testCorrectShipSecondPlace_secondPartOnWrongPlace_assertFalse() {
        int headY = 2;
        int headX = 2;
        int x = 3;
        int y = 3;

        boolean isDestroyed = checkerInject.correctShipSecondPlace(x, y, headX, headY);
        Assertions.assertFalse(isDestroyed);
    }

    @Test
    void testOutOfFieldCheck_shipOutOfField_assertFalse() {
        int x = 10;
        int y = 2;
        boolean outOfField = checkerInject.outOfFieldCheck(x, y);
        Assertions.assertFalse(outOfField);
    }

    @Test
    void testOutOfFieldCheckTwo_shipOutOfField_assertFalse() {
        int x = 5;
        int y = -2;
        boolean outOfField = checkerInject.outOfFieldCheck(x, y);
        Assertions.assertFalse(outOfField);
    }

    @Test
    void testOutOfFieldCheckTwo_shipInOfField_assertTrue() {
        int x = 5;
        int y = 7;
        boolean outOfField = checkerInject.outOfFieldCheck(x, y);
        Assertions.assertTrue(outOfField);
    }

    @Test
    void  testFieldNearShip_shipNearAnother_assertFalse() {
        boolean[][] table = new boolean[9][9];
        table[5][5] = true;
        table[5][6] = true;
        Mockito.when(storageMock.tableGetter()).thenReturn(table);
        int x = 5; int y = 5;
        int headX = 5; int headY = 5;
        boolean isShipNear = checkerInject.fieldNearShip(x, y, storageMock, headX, headY);
        Assertions.assertFalse(isShipNear);
    }

    @Test
    void testFieldNearShip_shipNearAnother_assertTrue() {
        boolean[][] table = new boolean[9][9];
        table[3][3] = true;
        table[5][5] = true;
        Mockito.when(storageMock.tableGetter()).thenReturn(table);
        int x = 5; int y = 5;
        int headX = 5; int headY = 5;
        boolean isShipNear = checkerInject.fieldNearShip(x, y, storageMock, headX, headY);
        Assertions.assertTrue(isShipNear);
    }

}
