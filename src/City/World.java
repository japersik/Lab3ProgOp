package City;

import java.util.ArrayList;

public class World {
    ArrayList<Town> Towns = new ArrayList();
    ArrayList<Mine> Mines = new ArrayList();
    ArrayList<Wiseacre> Wiseacres = new ArrayList();
    ArrayList<Worker> Workers = new ArrayList();

    public World() {
        System.out.println("Создан новый мир");
        Town oldTown = new Town("Старый город", "Морское дно", 27, 90, 0);
        addTowns(oldTown);
        addWiseacres(new Wiseacre("Мастер", oldTown));
        addMines(new Mine("Каменоломня"));
    }

    public void go() throws InterruptedException {
        for (int i = 0; i < Wiseacres.size(); i++) {
            if (Towns.size() > 1) {
                Wiseacres.get(i).goToLocality(Towns.get(1 + (int) Math.floor(Math.random() * (Towns.size() - 1))));
            } else {
                Wiseacres.get(i).goToLocality(Towns.get(0));
            }
        }

        while (true) {
            for (int i = 0; i < Wiseacres.size(); i++) {
                wiseacresMoves(i);
            }
            for (int i = 0; i < Workers.size(); i++) {
                workersMoves(i);
            }

        }
    }

    private void wiseacresMoves(int i) {
        Locality hirLocality = Wiseacres.get(i).getLocality();
        //сюда впихнуть разделение на потоки
        if (hirLocality != null && hirLocality.getType() == TypeOfLocality.TOWN) {
            {
                Worker newWorker = Wiseacres.get(i).CreatingWorkers();
                if (newWorker != null) {
                    Workers.add(newWorker);
                } else if (findRecourcesInTowns((Town) hirLocality, Resources.PROTOPLASM, 5) != null) {
                    if (findRecourcesInTowns((Town) hirLocality, Resources.PROTOPLASM, 5) != hirLocality)
                        Wiseacres.get(i).resourceTransfer(findRecourcesInTowns((Town) hirLocality, Resources.PROTOPLASM, 5), (Town) hirLocality, Resources.PROTOPLASM, 5);
                    Wiseacres.get(i).takeResource((Town) hirLocality, Resources.PROTOPLASM, 5);
                    Wiseacres.get(i).goToLocality(hirLocality);
                    newWorker = Wiseacres.get(i).CreatingWorkers();
                    Workers.add(newWorker);
                }
            }
        } else {
            Wiseacres.get(i).goToLocality(Towns.get(0));
        }
    }

    private void workersMoves(int index) {
        Locality hisLocality = Workers.get(index).getLocality();
        Resources v1 = Resources.STONE;
        if (hisLocality != null && hisLocality.getType() == TypeOfLocality.TOWN) {
            if (findRecourcesInMines(v1) != null) {
                Workers.get(index).goMine(findRecourcesInMines(Resources.STONE, Workers.get(index)), (Town) hisLocality);
            }
        } else {
            Workers.get(index).goToLocality(Towns.get(0));
        }
    }

    public Town findRecourcesInTowns(Town toTown, Resources typeOfResource, int value) {
        int maxResValue = 0;
        int TownWithMaxRes = 0;
        if (value > 10) {
            value = 10;
        }
        if (toTown.getResourceValue(typeOfResource) >= value) {
            TownWithMaxRes = Towns.indexOf(toTown);
            maxResValue = toTown.getResourceValue(typeOfResource);
        } else {
            for (int i = 0; i < Towns.size(); i++) {
                if (Towns.indexOf(toTown) != i && Towns.get(i).getResourceValue(typeOfResource) > 20) {
                    maxResValue = Towns.get(i).getResourceValue(typeOfResource);
                    TownWithMaxRes = i;
                }
            }
        }
        if (maxResValue == 0) {
            return null;
        } else {
            return Towns.get(TownWithMaxRes);
        }
    }

    public Mine findRecourcesInMines(Resources typeOfResource, Worker worker) {
        Mine mine = null;
        double r = Double.MAX_VALUE;
        for (int i = 0; i < Mines.size(); i++) {
            if (Mines.get(i).getMineResValue() > 0 && Mines.get(i).getMineResType() == typeOfResource) {
                double newR = Math.sqrt(Math.pow(Mines.get(i).getPointX() - worker.getPointX(), 2) + Math.pow(Mines.get(i).getPointY() - worker.getPointY(), 2));
                if (r > newR) {
                    r = newR;
                    mine = Mines.get(i);
                }
            }
        }
        return mine;

    }

    public Mine findRecourcesInMines(Resources typeOfResource) {
        for (int i = 0; i < Mines.size(); i++) {
            if (Mines.get(i).getMineResValue() > 0 && Mines.get(i).getMineResType() == typeOfResource) {
                return Mines.get(i);
            }
        }
        return null;
    }

    public void helpFromTo(Town helpFromTown, Town helpToTown) {

    }

    public void addMines(Mine mine) {
        this.Mines.add(mine);
    }

    public void addTowns(Town town) {
        this.Towns.add(town);
    }

    public void addWiseacres(Wiseacre wiseacre) {
        this.Wiseacres.add(wiseacre);
    }
}