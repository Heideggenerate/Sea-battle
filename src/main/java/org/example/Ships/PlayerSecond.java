package org.example.Ships;

public class PlayerSecond {

    private Storage playerSecond = new Storage();
    private HeadRandom parameterPassing = new HeadRandom();
    private HeadChoose parameterPassingManualAuto = new HeadChoose();
    private BuildManual parameterPassingManual = new BuildManual();

    /**Передача ссылки второго игрока*/
    public Storage passingSecond() {
        parameterPassing.playerData(playerSecond);
        return parameterPassing.fourShips();
    }

    public Storage passingSecondManualAuto() {
        parameterPassingManualAuto.dataChange(playerSecond);
        return parameterPassingManualAuto.headPlacer();
    }

    public Storage passingSecondManual() {
        parameterPassingManual.storageChange(playerSecond);
        return parameterPassingManual.partGenerate();
    }

    public Storage getPlayerSecond() {
        return playerSecond;
    }
}
