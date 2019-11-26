package City;

import java.util.ArrayList;

public class World {
    ArrayList<Town> Towns = new ArrayList();
    ArrayList<Mine> Mines = new ArrayList();
    ArrayList<Wiseacre> Wiseacres = new ArrayList();
    ArrayList<Worker> Workers = new ArrayList();
    private boolean endingResources = false;

    public World() {
        System.out.println("Создан новый мир");
        Town oldTown = new Town("Старый город", "Морское дно", 27, 10, 60);
        addTowns(oldTown);
        addWiseacres(new Wiseacre("Мастер", oldTown));
        addMines(new Mine("Каменоломня", Resources.STONE, 100, "Глубокая глубина"));
    }

    public void go() {
        for (int i = 0; i < Wiseacres.size(); i++) {
            if (Towns.size() > 1) {
                Wiseacres.get(i).goToLocality(Towns.get((int) Math.floor(1 + Math.random() * (Towns.size() - 1))));
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

    private void wiseacresMoves(int index) {

//        Runnable task = () -> {
//            if (Wiseacres.get(index).status) {
//                Wiseacres.get(index).status = false;
        Locality hirLocality = Wiseacres.get(index).getLocality();
        double v1 = Math.random();
        if (hirLocality != null && hirLocality.getType() == TypeOfLocality.TOWN) {
            if (v1 > 0.85) {
                LuminousCreature newLuminousCreature = Wiseacres.get(index).CreatingLuminousCreature();
                if (newLuminousCreature != null) {
                    ((Town) (Wiseacres.get(index).getLocality())).addLuminousCreature(newLuminousCreature);
                } else if (findRecourcesInTowns((Town) hirLocality, Resources.PROTOPLASM, 3) != null) {
                    if (findRecourcesInTowns((Town) hirLocality, Resources.PROTOPLASM, 3) != hirLocality)
                        Wiseacres.get(index).resourceTransfer(findRecourcesInTowns((Town) hirLocality, Resources.PROTOPLASM, 3), (Town) hirLocality, Resources.PROTOPLASM, 3);
                    Wiseacres.get(index).takeResource((Town) hirLocality, Resources.PROTOPLASM, 3);
                    Wiseacres.get(index).goToLocality(hirLocality);
                    Wiseacres.get(index).CreatingLuminousCreature();
                }
            } else if (v1 > 0.5) {
                House newHouse = Wiseacres.get(index).Building();
                if (newHouse != null) {
                    ((Town) (Wiseacres.get(index).getLocality())).addHouses(newHouse);
                } else if (findRecourcesInTowns((Town) hirLocality, Resources.STONE, 5) != null) {
                    if (findRecourcesInTowns((Town) hirLocality, Resources.STONE, 5) != hirLocality)
                        Wiseacres.get(index).resourceTransfer(findRecourcesInTowns((Town) hirLocality, Resources.STONE, 5), (Town) hirLocality, Resources.STONE, 5);
                    Wiseacres.get(index).takeResource((Town) hirLocality, Resources.STONE, 5);
                    Wiseacres.get(index).goToLocality(hirLocality);
                    newHouse = Wiseacres.get(index).Building();

                }
            } else if (v1 > 0.2) {
                Worker newWorker = Wiseacres.get(index).CreatingWorkers();
                if (newWorker != null) {
                    Workers.add(newWorker);
                } else if (findRecourcesInTowns((Town) hirLocality, Resources.CELLMASS, 5) != null) {
                    if (findRecourcesInTowns((Town) hirLocality, Resources.CELLMASS, 5) != hirLocality)
                        Wiseacres.get(index).resourceTransfer(findRecourcesInTowns((Town) hirLocality, Resources.CELLMASS, 5), (Town) hirLocality, Resources.CELLMASS, 5);
                    Wiseacres.get(index).takeResource((Town) hirLocality, Resources.CELLMASS, 5);
                    Wiseacres.get(index).goToLocality(hirLocality);
                    newWorker = Wiseacres.get(index).CreatingWorkers();
                    Workers.add(newWorker);
//                    this.workersMoves(newWorker);
                }
            }
        } else {
            Wiseacres.get(index).goToLocality(Towns.get((int) Math.floor(Math.random() * (Towns.size()))));
        }

//            }
//
//        };
//        Thread thread = new Thread(task);
//        thread.start();


    }


    private void workersMoves(int index) {
//        Runnable task = () -> {if (Workers.get(index).status) {
//            Wiseacres.get(index).status = false;
        if (!endingResources) {
            Locality hisLocality = Workers.get(index).getLocality();
            Resources v1 = ((Town) hisLocality).getMinResType();

            if (hisLocality != null && hisLocality.getType() == TypeOfLocality.TOWN) {
                if (findRecourcesInMines(v1) != null) {
                    Workers.get(index).goMine(findRecourcesInMines(v1, Workers.get(index)), (Town) hisLocality);
                } else if (findRecourcesInMines() != null) {
                    Workers.get(index).goMine(findRecourcesInMines(), (Town) hisLocality);
                } else {
                    EventMessage.message("Во всех мировых шахтах закончиличь ресурсы", 1);
                    this.endingResources = true;
                }
            } else {
                Workers.get(index).goToLocality(Towns.get((int) Math.floor(Math.random() * (Towns.size()))));
            }
//            }
//        };
//        Thread thread = new Thread(task);
//        thread.start();

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
            if (Mines.get(i).getMineResValue() > 0 && Mines.get(i).getMineResType() == typeOfResource && Mines.get(i).getMineResValue() > 0) {
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
            if (Mines.get(i).getMineResValue() > 0 && Mines.get(i).getMineResType() == typeOfResource && Mines.get(i).getMineResValue() > 0) {
                return Mines.get(i);
            }
        }
        return null;
    }

    public Mine findRecourcesInMines() {
        for (int i = 0; i < Mines.size(); i++) {
            if (Mines.get(i).getMineResValue() > 0 && Mines.get(i).getMineResValue() > 0) {
                return Mines.get(i);
            }
        }
        return null;
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