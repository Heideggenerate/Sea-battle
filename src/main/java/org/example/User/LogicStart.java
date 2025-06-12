package org.example.User;

import org.example.Logic.GameTypeAi;
import org.example.Logic.GameTypeUsers;

public class LogicStart {
    public void start() {
        UserOutput output = new UserOutput();
        UserInput input = new UserInput();

        output.botOrUser();
        switch(input.input()) {
            case 1: GameTypeAi logicAI = new GameTypeAi();
            logicAI.gameTypeAi();
            break;

            case 2: GameTypeUsers logicUser = new GameTypeUsers();
            logicUser.gameType();
            break;

            default: break;
        }
    }

}
