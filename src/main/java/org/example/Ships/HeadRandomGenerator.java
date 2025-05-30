package org.example.Ships;

public class HeadRandomGenerator {

    private StorageShips data = new StorageShips();
    private ShipLengthCounter shipLength = new ShipLengthCounter();
    private ShipPlaceCheck checker = new ShipPlaceCheck();
    private FullAutomaticlShipBuilder shipBuilder = new FullAutomaticlShipBuilder();

    public void playerData(StorageShips playerInfo) {
        data = playerInfo;
    }

    public StorageShips fourShips() {
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