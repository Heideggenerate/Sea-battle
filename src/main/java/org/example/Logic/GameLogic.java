package org.example.Logic;

import org.example.Ships.*;
import org.example.User.FieldGenerator;
import org.example.User.UserInput;
import org.example.User.UserOutput;


public class GameLogic {

    private UserInput input = new UserInput();
    private UserOutput output = new UserOutput();
    private Storage data = new Storage();
    private PlayerFirst firstPlayer = new PlayerFirst();
    private PlayerSecond secondPlayer = new PlayerSecond();
    private BuildManual manualBuilder = new BuildManual();
    private final FieldGenerator field = new FieldGenerator();
    private Storage[] playerData = new Storage[2];
    private ShipDestroy destroy = new ShipDestroy();
    private Checker checker = new Checker();

    public void fieldDataGetter() {
        boolean[][][] playersInfo = new boolean[2][data.YSIZE][data.XSIZE];
        output.menuTypeGame();
        switch(input.input()) {
            case 2:
                playerData[0] = firstPlayer.passingFirstAuto();
                playersInfo[0] = firstPlayer.getPlayerFirst().tableGetter();
                field.generator(playersInfo, false);

                sleeperEnters(false);

                playerData[1] = secondPlayer.passingSecond();
                playersInfo[1] = secondPlayer.getPlayerSecond().tableGetter();
                field.generator(playersInfo, true);

                sleeperEnters(true);
                break;
            case 1:
                playerData[0] = firstPlayer.passingFirstManualAuto();
                playersInfo[0] = firstPlayer.getPlayerFirst().tableGetter();
                field.generator(playersInfo, false);

                sleeperEnters(false);

                playerData[1] = secondPlayer.passingSecondManualAuto();
                playersInfo[1] = secondPlayer.getPlayerSecond().tableGetter();
                field.generator(playersInfo, true);

                sleeperEnters(true);
                break;
            case 3:
                playerData[0] = firstPlayer.passingFirstManual();
                playersInfo[0] = firstPlayer.getPlayerFirst().tableGetter();

                sleeperEnters(false);

                playerData[1] = secondPlayer.passingSecondManual();
                playersInfo[1] = secondPlayer.getPlayerSecond().tableGetter();

                sleeperEnters(true);
                break;
        }
        fightLogicUse();
    }

    public void fightLogicUse() {
        int[][] fieldAttackFirst = new int[9][9];
        int[][] fieldAttackSecond = new int[9][9];
        while (fightLogic(fieldAttackFirst, fieldAttackSecond));
    }

    public boolean fightLogic(int[][] first, int[][] second) {
        output.playerReady();
        sleeperEnters(true);
        if (!checker.isAllDestroyed(playerData[0])) {
            output.queue(0);
            destroy.storageChange(playerData[1]);
            first = destroy.coordinatesAttack(first);
        }
        else if (checker.isAllDestroyed(playerData[0])) {
            output.gameOver(1);
            return false;
        }

        output.enters();

        if (!checker.isAllDestroyed(playerData[1])) {
            output.queue(0);
            destroy.storageChange(playerData[0]);
            second = destroy.coordinatesAttack(second);
        }
        else if (checker.isAllDestroyed(playerData[1])) {
            output.gameOver(1);
            return false;
        }
        return true;
    }

    public void sleeperEnters(boolean is) {
        int k = 0;
        if (is) k = 2000;
        try {Thread.sleep(6000 - k);} catch (Exception ex) {}
        output.enters();
        try {Thread.sleep(2500 - k);} catch (Exception ex) {}
    }

    public static void general_node() {

    }

    static void generators() {

    }

    static void user() {

    }

}
