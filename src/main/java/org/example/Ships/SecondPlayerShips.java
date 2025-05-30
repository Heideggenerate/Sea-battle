package org.example.Ships;

public class SecondPlayerShips {

    private StorageShips playerSecond = new StorageShips();
    private HeadRandomGenerator parameterPassing = new HeadRandomGenerator();
    private HeadChooseShipAutomatic parameterPassingManualAuto = new HeadChooseShipAutomatic();
    private FullManualBuilder parameterPassingManual = new FullManualBuilder();

    /**Передача ссылки второго игрока*/
    public StorageShips passingSecond() {
        parameterPassing.playerData(playerSecond);
        return parameterPassing.fourShips();
    }

    public StorageShips passingSecondManualAuto() {
        parameterPassingManualAuto.dataChange(playerSecond);
        return parameterPassingManualAuto.headPlacer();
    }

    public StorageShips passingSecondManual() {
        parameterPassingManual.storageChange(playerSecond);
        return parameterPassingManual.partGenerate();
    }

    public StorageShips getPlayerSecond() {
        return playerSecond;
    }
}
