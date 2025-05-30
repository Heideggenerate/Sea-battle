package org.example.Logic;

import org.example.Ships.Checker;
import org.example.Ships.ShipDestroy;
import org.example.Ships.Storage;
import org.example.User.UserOutput;

public class Fight {

    private ShipDestroy destroy = new ShipDestroy();
    private Checker checker = new Checker();

    /**
     *
     * @param playerData Хранилище обоих игроков
     */
    //Использование логики боя
    public void fightLogicUse(Storage[] playerData) {
        int[][] fieldAttackFirst = new int[9][9];
        int[][] fieldAttackSecond = new int[9][9];
        while (fightLogic(fieldAttackFirst, fieldAttackSecond, playerData));
    }

    /**
     *
     * @param first Координаты атакованных позиций. Первый игрок
     * @param second Координаты атакованных позиций. Второй игрок
     * @param playerData Хранилище обоих игроков
     * @return
     */
    //Логика боя
    public boolean fightLogic(int[][] first, int[][] second, Storage[] playerData) {
        UserOutput.playerReady();
        UserOutput.sleeperEnters(true);
        if (!checker.isAllDestroyed(playerData[0])) {
            UserOutput.queue(0);
            destroy.storageChange(playerData[1]);
            first = destroy.coordinatesAttack(first);
        }
        else if (checker.isAllDestroyed(playerData[0])) {
            UserOutput.gameOver(1);
            return false;
        }

        UserOutput.enters();

        if (!checker.isAllDestroyed(playerData[1])) {
            UserOutput.queue(0);
            destroy.storageChange(playerData[0]);
            second = destroy.coordinatesAttack(second);
        }
        else if (checker.isAllDestroyed(playerData[1])) {
            UserOutput.gameOver(1);
            return false;
        }
        UserOutput.enters();
        return true;
    }

}
