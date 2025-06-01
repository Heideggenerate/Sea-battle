package org.example.Ships;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.junit.jupiter.api.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
public class BuildAutoTest {

    @Mock
    private Storage storage;

    @Spy
    @InjectMocks
    private BuildAuto buildAuto;

    @Test
    void shipSizeTest_shipPlaced_assertTrue() {
        Mockito.doReturn(true).when(buildAuto).shipBuilder(anyInt());
        boolean isPlaced = buildAuto.shipSize();
        Assertions.assertTrue(isPlaced);
    }

    @Test
    void shipBuilderTest_shipPlaced_assertTrue() {
        Mockito.doReturn(true).when(buildAuto).coordinateCheck(anyInt());
        boolean isPlaced = buildAuto.shipBuilder(anyInt());
        Assertions.assertTrue(isPlaced);
    }

    @Test
    void shipBuilderTest_shipPlaced_assertFalse() {
        Mockito.doReturn(false).when(buildAuto).coordinateCheck(anyInt());
        boolean isPlaced = buildAuto.shipBuilder(anyInt());
        Assertions.assertFalse(isPlaced);
    }

    @Test
    void coordinateCheckTest_shipPlaced_assertTrue() {
        Mockito.doReturn(true).when(buildAuto).coordinateCheck(anyInt());
        boolean isPlaced = buildAuto.coordinateCheck(anyInt());
        Assertions.assertTrue(isPlaced);
    }

    @Test
    void coordinateCheckTest_shipPlaced_assertFalse() {
        Mockito.doReturn(false).when(buildAuto).coordinateCheck(anyInt());
        boolean isPlaced = buildAuto.coordinateCheck(anyInt());
        Assertions.assertFalse(isPlaced);
    }

    @Test
    void directionCoordinatesCorrect_calculateCorrectCoordinatesSame_assert() {
        Mockito.doReturn(5).when(buildAuto).endCoordinateCalculate(5);
        int coordinates = buildAuto.directionCoordinatesCorrect(5);
        Assertions.assertEquals(coordinates, 5);
    }

    @Test
    void directionCoordinatesCorrectTest_calculateCorrectCoordinatesDefault_assertEqual() {
        Mockito.doReturn(5).when(buildAuto).endCoordinateCalculate(anyInt());
        int coordinates = buildAuto.directionCoordinatesCorrect(7);
        Assertions.assertEquals(coordinates, 7);
    }

    @Test
    void directionCoordinatesCorrectTest_calculateCorrectCoordinatesSameY_assert() {
        Mockito.doReturn(50).when(buildAuto).endCoordinateCalculate(5);
        int coordinates = buildAuto.directionCoordinatesCorrect(5);
        Assertions.assertEquals(coordinates, 50);
    }

    @Test
    void directionCoordinatesCorrectTest_calculateCorrectCoordinatesDefaultY_assertEqualRightCoordinates() {
        Mockito.doReturn(15).when(buildAuto).endCoordinateCalculate(anyInt());
        int coordinates = buildAuto.directionCoordinatesCorrect(7);
        Assertions.assertEquals(coordinates, 70);
    }
}
