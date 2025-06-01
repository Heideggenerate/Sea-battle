package org.example.Ships;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

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
        verify(generatorSpy, times(4)).coordinatesGenerator(false);
    }

    @Test
    void testCoordinatesGenerator_shipOnTableGiveCall_verify() {
        boolean[][] table = new boolean[9][9];
        table[8][8] = true;
        Mockito.when(storageMock.tableGetter()).thenReturn(table);
        generatorSpy.coordinatesGenerator(false);
        verify(storageMock).tableGive(anyInt(), anyInt());
    }
}