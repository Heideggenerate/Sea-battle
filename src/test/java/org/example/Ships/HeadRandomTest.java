package org.example.Ships;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
class HeadRandomTest {

    @Mock
    private Storage storageMock;

    @Mock
    private LengthCounter shipLengthMock;

    @Spy
    @InjectMocks
    private HeadRandom generatorSpy;

    @Test
    void testFourShips_isCanPlaceCall_verify() {
        boolean[][] table = new boolean[9][9];
        Mockito.when(storageMock.tableGetter()).thenReturn(table);
        generatorSpy.fourShips();
        verify(shipLengthMock).isCanPlace(table);
    }

    @Test
    void testFourShips_coordinatesGeneratorCall_verify() {
        generatorSpy.fourShips();
        verify(generatorSpy, times(4)).coordinatesGenerator(false, anyInt());
    }

    @Test
    void testCoordinatesGenerator_shipOnTableGiveCall_verify() {
        boolean[][] table = new boolean[9][9];
        table[8][8] = true;
        Mockito.when(storageMock.tableGetter()).thenReturn(table);
        generatorSpy.coordinatesGenerator(false, 0);
        verify(storageMock).shipOnTableGive(anyInt(), anyInt());
    }

    @Test
    void testCoordinatesGenerator_correctnessRandom_verify() {
        for (int i = 0; i < 50; i++) {
            generatorSpy.coordinatesGenerator(false, 0);
            verify(storageMock).shipOnTableGive(intThat(x -> x >= 0 && x < 9), intThat(y -> y >= 0 && y < 9));
            clearInvocations(storageMock);
        }
    }

    @Test
    void testCoordinatesCheck_onLastPosition_returnsFalse() {
        boolean[][] table = new boolean[9][9];
        table[8][8] = true;

        Mockito.when(storageMock.tableGetter()).thenReturn(table);
        Checker checker = new Checker();
        boolean coordinateCheck = checker.coordinatesCheck(8, 8, storageMock, 8, 8, true);
        Assertions.assertFalse(coordinateCheck);
    }

    @Test
    void testCoordinatesCheck_onEmptyPosition_returnsTrue() {
        boolean[][] table = new boolean[9][9];
        table[0][0] = false;
        Mockito.when(storageMock.tableGetter()).thenReturn(table);

        Checker checker = new Checker();
        boolean coordinateCheck = checker.coordinatesCheck(0, 0, storageMock, 0, 0, true);
        Assertions.assertTrue(coordinateCheck);
    }
}