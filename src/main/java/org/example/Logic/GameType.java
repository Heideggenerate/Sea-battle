package org.example.Logic;

import org.example.Ships.*;
import org.example.User.FieldGenerator;
import org.example.User.UserInput;
import org.example.User.UserOutput;


public class GameType {

    private final Storage data = new Storage();
    private final PlayerFirst firstPlayer = new PlayerFirst();
    private final PlayerSecond secondPlayer = new PlayerSecond();
    private final FieldGenerator field = new FieldGenerator();
    private final Storage[] playerData = new Storage[2];
    private final Fight fight = new Fight();

    //Выбор режима игры
    public void gameType() {
        boolean[][][] playersInfo = new boolean[2][data.YSIZE][data.XSIZE];
        UserOutput.menuTypeGame();
        switch(UserInput.input()) {
            case 1:
                playerData[0] = firstPlayer.passingFirstManual();
                playersInfo[0] = firstPlayer.getPlayerFirst().tableGetter();

                UserOutput.sleeperEnters(false);

                playerData[1] = secondPlayer.passingSecondManual();
                playersInfo[1] = secondPlayer.getPlayerSecond().tableGetter();

                UserOutput.sleeperEnters(true);
                break;
            case 2:
                playerData[0] = firstPlayer.passingFirstManualAuto();
                playersInfo[0] = firstPlayer.getPlayerFirst().tableGetter();

                UserOutput.sleeperEnters(false);

                playerData[1] = secondPlayer.passingSecondManualAuto();
                playersInfo[1] = secondPlayer.getPlayerSecond().tableGetter();

                UserOutput.sleeperEnters(true);
                break;
            case 3:
                playerData[0] = firstPlayer.passingFirstAuto();
                playersInfo[0] = firstPlayer.getPlayerFirst().tableGetter();

                UserOutput.sleeperEnters(false);

                playerData[1] = secondPlayer.passingSecond();
                playersInfo[1] = secondPlayer.getPlayerSecond().tableGetter();

                UserOutput.sleeperEnters(true);
                break;
        }
        fight.fightLogicUse(playerData);
    }

}
