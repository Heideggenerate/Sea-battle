package org.example.Ships;

public class HeadRandom {

    private Storage data = new Storage();
    private LengthCounter shipLength = new LengthCounter();
    private Checker checker = new Checker();
    private BuildAuto shipBuilder = new BuildAuto();

    public void playerData(Storage playerInfo) {
        data = playerInfo;
    }

    public Storage fourShips() {
        boolean isPlaced = false;
        while (!isPlaced) {
            data.fieldClean(data);
            for (int i = 0; i < 4; i++) {
                coordinatesGenerator(false, i);
            }
            shipLength.storageChange(data);
            shipLength.isCanPlace(data.tableGetter());
            shipBuilder.dataChange(data);
            isPlaced = shipBuilder.shipSize();
        }
        return data;
    }

    public void coordinatesGenerator(boolean isPlaced, int k) {

        int x = (int) (Math.random() * data.XSIZE);
        int y = (int) (Math.random() * data.YSIZE);
        while (!isPlaced) {
            x = (int) (Math.random() * data.XSIZE);
            y = (int) (Math.random() * data.YSIZE);
            isPlaced = checker.coordinatesCheck(x, y, data, x, y, true);
        }
        data.shipOnTableGive(x, y);
    }

}