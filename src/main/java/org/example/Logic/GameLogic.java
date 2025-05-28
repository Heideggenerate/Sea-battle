package org.example.Logic;

import org.example.Ships.FirstPlayerShips;
import org.example.Ships.FullManualBuilder;
import org.example.Ships.SecondPlayerShips;
import org.example.Ships.StorageShips;
import org.example.User.FieldGenerator;
import org.example.User.UserInput;
import org.example.User.UserOutput;


public class GameLogic {

    private UserInput input = new UserInput();
    private UserOutput output = new UserOutput();
    private StorageShips data = new StorageShips();
    private FirstPlayerShips firstPlayer = new FirstPlayerShips();
    private SecondPlayerShips secondPlayer = new SecondPlayerShips();
    private FullManualBuilder manualBuilder = new FullManualBuilder();
    private final FieldGenerator field = new FieldGenerator();

    public void fieldDataGetter() {
        boolean[][][] playersInfo = new boolean[2][data.YSIZE][data.XSIZE];
        output.menuTypeGame();
        switch(input.input()) {
            case 2:
                firstPlayer.passingFirstAuto();
                playersInfo[0] = firstPlayer.getPlayerFirst().tableGetter();

                secondPlayer.passingSecond();
                playersInfo[1] = secondPlayer.getPlayerSecond().tableGetter();
                break;
            case 1:
                firstPlayer.passingFirstManualAuto();
                playersInfo[0] = firstPlayer.getPlayerFirst().tableGetter();

                secondPlayer.passingSecondManualAuto();
                playersInfo[1] = secondPlayer.getPlayerSecond().tableGetter();
                break;
            case 3:
                firstPlayer.passingFirstManual();
                playersInfo[0] = firstPlayer.getPlayerFirst().tableGetter();

                secondPlayer.passingSecondManual();
                playersInfo[1] = secondPlayer.getPlayerSecond().tableGetter();
                break;
        }
        field.generator(playersInfo);
    }

    public static void general_node() {

    }

    static void generators() {

    }

    static void user() {

    }

}
