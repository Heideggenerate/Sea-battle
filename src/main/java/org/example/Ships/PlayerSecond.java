package org.example.Ships;

public class PlayerSecond {

    private final Storage playerSecond = new Storage();
    private final HeadRandom parameterPassing = new HeadRandom();
    private final HeadChoose parameterPassingManualAuto = new HeadChoose();
    private final BuildManual parameterPassingManual = new BuildManual();

    //Автоматическая генерация
    public Storage passingSecond() {
        parameterPassing.playerData(playerSecond);
        return parameterPassing.fourShips();
    }
    //Полу-автомат
    public Storage passingSecondManualAuto() {
        parameterPassingManualAuto.dataChange(playerSecond);
        return parameterPassingManualAuto.headPlacer();
    }
    //Ручная расстановка
    public Storage passingSecondManual() {
        parameterPassingManual.storageChange(playerSecond);
        return parameterPassingManual.partGenerate();
    }

    /**
     *
     * @return Возврат данных игрока
     */
    //Получение данных игрока
    public Storage getPlayerSecond() {
        return playerSecond;
    }
}
