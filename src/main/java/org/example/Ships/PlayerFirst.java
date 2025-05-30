package org.example.Ships;

public class PlayerFirst {

    private Storage playerFirst = new Storage();
    private HeadRandom parameterPassingAuto = new HeadRandom();
    private HeadChoose parameterPassingManualAuto = new HeadChoose();
    private BuildManual parameterPassingManual = new BuildManual();


    /**Передача ссылки первого игрока*/
    public Storage passingFirstAuto() {
        parameterPassingAuto.playerData(playerFirst);
        return parameterPassingAuto.fourShips();
    }

    public Storage passingFirstManualAuto() {
        parameterPassingManualAuto.dataChange(playerFirst);
        return parameterPassingManualAuto.headPlacer();
    }

    public Storage passingFirstManual() {
        parameterPassingManual.storageChange(playerFirst);
        return parameterPassingManual.partGenerate();
    }
    public Storage getPlayerFirst() {
        return playerFirst;
    }
}
