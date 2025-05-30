package org.example.Ships;

import org.example.User.FieldGenerator;

public class FirstPlayerShips {

    private StorageShips playerFirst = new StorageShips();
    private HeadRandomGenerator parameterPassingAuto = new HeadRandomGenerator();
    private HeadChooseShipAutomatic parameterPassingManualAuto = new HeadChooseShipAutomatic();
    private FullManualBuilder parameterPassingManual = new FullManualBuilder();


    /**Передача ссылки первого игрока*/
    public StorageShips passingFirstAuto() {
        parameterPassingAuto.playerData(playerFirst);
        return parameterPassingAuto.fourShips();
    }

    public StorageShips passingFirstManualAuto() {
        parameterPassingManualAuto.dataChange(playerFirst);
        return parameterPassingManualAuto.headPlacer();
    }

    public StorageShips passingFirstManual() {
        parameterPassingManual.storageChange(playerFirst);
        return parameterPassingManual.partGenerate();
    }
    public StorageShips getPlayerFirst() {
        return playerFirst;
    }
}
