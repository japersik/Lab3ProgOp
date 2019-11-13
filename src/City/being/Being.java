package City.being;

import City.EventMessage;
import City.place.Locality;
import City.place.Resource;
import City.place.Resources;
import City.place.Town;

public abstract class Being implements InfoI, ResourceMove {
    protected Locality locality;
    protected String name;
    protected Resource myRes = new Resource();
    protected static final int maxResourceValues = 10;

    public Being(String name) {
        this(name, null);
    }

    public Being(String name, Locality p) {
        this.name = name;
        this.locality = p;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        EventMessage.message(this.name + " изменил своё имя на " + name);
        this.name = name;
    }

    public void setLocality(Locality locality) {
        if (locality == this.locality) {
            EventMessage.message(this.name + " находится в " + locality.getName(), 0);
        } else {
            this.locality = locality;
            EventMessage.message(this.name + " переместился в  " + locality.getName());
        }
    }

    public Locality getLocality() {
        return locality;
    }

    public int getResValue() {
        return this.myRes.getValue();
    }

    public Resources getResType() {
        return this.myRes.getType();
    }

    public void resourceTransfer(Town fromTown, Town toTown, Resources typeOfResource) {
        resourceTransfer(fromTown, toTown, typeOfResource, maxResourceValues);
    }

    public void resourceTransfer(Town fromTown, Town toTown, Resources typeOfResource, int value) {
        if (value > maxResourceValues) value = maxResourceValues;
        EventMessage.message(this.name + " отправился на перенос " + value + " единиц ресурса " + typeOfResource.data() + " из " + fromTown.getName() + " в " + toTown.getName());
        takeResource(fromTown, typeOfResource, value);
        giveResource(toTown);
    }

    public void giveResource(Town townLocality) {
        this.setLocality(townLocality);
        if (getResValue() != 0) {
            townLocality.setResourceValue(myRes.getType(), townLocality.getResourceValue(myRes.getType()) + myRes.getValue());
            EventMessage.message(this.name + " отдёт " + myRes.getValue() + " единиц ресурса " + myRes.getType().data() + " в место " + townLocality.getName());
            myRes.setValue(0);
            myRes.setType(Resources.NONE);
        } else {
            EventMessage.message(this.name + " не смог отдать ресурсы в " + townLocality.getName() + ", так как его инвентарь пуст");
        }
    }

    public void takeResource(Town townLocality, Resources typeOfResource) {
        takeResource(townLocality, typeOfResource, maxResourceValues);
    }

    public void takeResource(Town townLocality, Resources typeOfResource, int value) {
        if (value > maxResourceValues) value = maxResourceValues;
        this.setLocality(townLocality);
        if (this.myRes.getValue() == 0) {
            if (townLocality.getResourceValue(typeOfResource) >= value) {
                this.myRes.setValue(value);
                townLocality.setResourceValue(typeOfResource, townLocality.getResourceValue(typeOfResource) - value);
            } else {
                this.myRes.setValue(townLocality.getResourceValue(typeOfResource));
                townLocality.setResourceValue(typeOfResource, 0);
            }
            if (this.myRes.getValue() > 0) {
                this.myRes.setType(typeOfResource);
                EventMessage.message(this.name + " забрал " + this.myRes.getValue() + " единиц ресурса " + myRes.getType().data() + " в месте " + townLocality.getName(), 0);
            } else {
                EventMessage.message(this.name + " не смог забрать требуемые ресурсы в " + townLocality.getName() + ", так как они закончились", 0);
            }
        } else {
            EventMessage.message(this.name + " имеет в инвентаре " + this.myRes.getValue() + " единиц ресурса " + this.myRes.getType().data() + ", поэтому он не может забрать ресурсы", 0);
        }
    }
}
