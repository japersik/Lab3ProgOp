package City;

public class Wiseacre extends Being {

    {
        if (this.locality == null) {
            EventMessage.message("В неизвестном месте с координатами " + this.PointToString() + " объявился новый старец " + this.name);
        } else {
            EventMessage.message("В месте " + this.locality.getName() + "с координатами " + this.PointToString() + " объявился новый старец " + this.name);
        }
    }

    public Wiseacre(String name) {
        super(name, null);
    }

    public Wiseacre(String name, Locality p) {
        super(name, p, p.getPointX(), p.getPointY());
    }

    public Wiseacre(String name, double x, double y) {
        super(name, null, x, y);
    }

    public Worker CreatingWorkers() {
        return this.CreatingWorkers(null);
    }

    public Worker CreatingWorkers(String nameOfWorker) {

        if (myRes.getType() == Resources.CELLMASS & myRes.getValue() >= 5) {
            EventMessage.message(this.name + " создаёт штоггота");
            myRes.setValue(myRes.getValue() - 5);
            if (myRes.getValue() == 0) {
                myRes.setType(Resources.NONE);
            }
            if (nameOfWorker == null) {
                return new Worker(this);
            } else {
                return new Worker(this, nameOfWorker);
            }
        } else {
            EventMessage.message(this.name + " не смог создать штоготта , т.к. не имеит нужных ресурсов(5 клеточной массы).Его инвентарь: " + myRes.getValue() + " единиц ресурса " + myRes.getType().getName());
            return null;
        }

    }

    public LuminousCreature CreatingLuminousCreature() {
        if (myRes.getType() == Resources.PROTOPLASM & myRes.getValue() >= 3) {
            myRes.setValue(myRes.getValue() - 3);
            return new LuminousCreature(this);
        } else {
            EventMessage.message(this.name + " не смог создать светящееся существо, т.к. не имеет нужных ресурсов(3 протоплазмы).Его инвентарь: " + myRes.getValue() + " единиц ресурса " + myRes.getType().getName());
            return null;
        }
    }


    public House Building() {
        if (myRes.getType() == Resources.STONE & myRes.getValue() >= 5) {
            myRes.setValue(myRes.getValue() - 5);
            return new House(this);
        } else {
            EventMessage.message(this.name + " не смог построить дом, т.к. не имеет нужных ресурсов(5 камней).Его инвентарь: " + myRes.getValue() + " единиц ресурса " + myRes.getType().getName());
            return null;
        }
    }
}

