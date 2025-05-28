package org.example.Ships;

import org.example.User.FieldGenerator;

public class FirstPlayerShips {

    private StorageShips playerFirst = new StorageShips();
    private HeadRandomGenerator parameterPassingAuto = new HeadRandomGenerator();
    private HeadChooseShipAutomatic parameterPassingManualAuto = new HeadChooseShipAutomatic();
    private FullManualBuilder parameterPassingManual = new FullManualBuilder();

    /**Передача ссылки первого игрока*/
    public void passingFirstAuto() {
        parameterPassingAuto.playerData(playerFirst);
        parameterPassingAuto.fourShips();
    }

    public void passingFirstManualAuto() {
        parameterPassingManualAuto.dataChange(playerFirst);
        parameterPassingManualAuto.headPlacer();
    }

    public void passingFirstManual() {
        parameterPassingManual.storageChange(playerFirst);
        parameterPassingManual.partGenerate();
    }

    public StorageShips getPlayerFirst() {
        return playerFirst;
    }
}
