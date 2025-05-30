package org.example.Ships;

public class PlayerFirst {

    private final Storage playerFirst = new Storage();
    private final HeadRandom parameterPassingAuto = new HeadRandom();
    private final HeadChoose parameterPassingManualAuto = new HeadChoose();
    private final BuildManual parameterPassingManual = new BuildManual();


    //Автоматическая генерация
    public Storage passingFirstAuto() {
        parameterPassingAuto.playerData(playerFirst);
        return parameterPassingAuto.fourShips();
    }

    //Полу-автомат
    public Storage passingFirstManualAuto() {
        parameterPassingManualAuto.dataChange(playerFirst);
        return parameterPassingManualAuto.headPlacer();
    }
    //Ручная расстановка
    public Storage passingFirstManual() {
        parameterPassingManual.storageChange(playerFirst);
        return parameterPassingManual.partGenerate();
    }

    /**
     *
     * @return Возврат данных игрока
     */
    //Получение данных игрока
    public Storage getPlayerFirst() {
        return playerFirst;
    }
}
