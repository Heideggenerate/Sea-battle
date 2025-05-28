package org.example.Ships;

public class SecondPlayerShips {

    private StorageShips playerSecond = new StorageShips();
    private HeadRandomGenerator parameterPassing = new HeadRandomGenerator();
    private HeadChooseShipAutomatic parameterPassingManualAuto = new HeadChooseShipAutomatic();
    private FullManualBuilder parameterPassingManual = new FullManualBuilder();

    /**Передача ссылки второго игрока*/
    public void passingSecond() {
        parameterPassing.playerData(playerSecond);
        parameterPassing.fourShips();
    }

    public void passingSecondManualAuto() {
//        parameterPassingManualAuto.dataChange(playerSecond);
//        parameterPassingManualAuto.headPlacer();
    }

    public void  passingSecondManual() {
//        parameterPassingManual.partGenerate();
//        parameterPassingManual.storageChange(playerSecond);
    }

    public StorageShips getPlayerSecond() {
        return playerSecond;
    }
}
