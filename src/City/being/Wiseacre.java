package City.being;

import City.EventMessage;
import City.place.House;
import City.place.Locality;
import City.place.Resources;

public class Wiseacre extends Being {

    public Wiseacre(String name) {
        this(name, null);

    }

    public Wiseacre(String name, Locality p) {
        super(name, p);
        if (this.locality == null) {
            EventMessage.message("В неизвестном месте объявился новый старец " + this.name);
        } else {
            EventMessage.message("В месте " + this.locality.getName() + " объявился новый старец " + this.name);
        }
    }

    public Worker CreatingWorkers(String nameOfWorker) {

        if (myRes.getType() == Resources.PROTOPLASM & myRes.getValue() >= 5) {
            EventMessage.message(this.name + " создаёт штоггота " + nameOfWorker);
            myRes.setValue(myRes.getValue() - 5);
            return new Worker(this, nameOfWorker);

        } else {
            EventMessage.message(this.name + " не смог создать штоготта , т.к. не имеит нужных ресурсов(5 протоплазмы).Его инвентарь: " + myRes.getValue() + " единиц ресурса " + myRes.getType().data());
            return null;
        }

    }

    public void Building() {
        if (myRes.getType() == Resources.STONE & myRes.getValue() >= 5) {
            new House(this);
            myRes.setValue(myRes.getValue() - 5);
        } else {
            EventMessage.message(this.name + " не смог построить дом, т.к. не имеет нужных ресурсов(5 камней).Его инвентарь: " + myRes.getValue() + " единиц ресурса " + myRes.getType().data());
        }
    }
}

