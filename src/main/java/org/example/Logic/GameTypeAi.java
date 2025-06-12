package org.example.Logic;

import org.example.Ships.*;
import org.example.User.FieldGenerator;
import org.example.User.UserInput;
import org.example.User.UserOutput;

public class GameTypeAi {

    private final Storage data = new Storage();
    private final PlayerFirst firstPlayer = new PlayerFirst();
    private final PlayerSecond secondPlayer = new PlayerSecond();
    private final FieldGenerator field = new FieldGenerator();
    private final Storage[] playerData = new Storage[2];
    private final FightAI fight = new FightAI();
    private final UserOutput output = new UserOutput();


    public void gameTypeAi() {
        boolean playersInfo[][][] = new boolean[2][data.YSIZE][data.XSIZE];
        playerData[0] = firstPlayer.passingFirstAuto();
        playersInfo[0] = firstPlayer.getPlayerFirst().tableGetter();

        output.sleeperEnters(false);

        playerData[1] = secondPlayer.passingSecond();
        playersInfo[1] = secondPlayer.getPlayerSecond().tableGetter();

        output.sleeperEnters(true);

        fight.fight(playerData);
    }
}
