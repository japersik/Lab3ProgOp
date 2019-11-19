package City;

import java.util.Objects;

public abstract class Being extends Point implements InfoI, ResourceMove {
    protected Locality locality;
    protected String name;
    protected Resource myRes = new Resource();
    protected static final int maxResourceValues = 10;
    protected static final double maxSpeed = 10.2;

    public Being(String name) {
        this(name, null);
    }

    public Being(String name, Locality p) {
        super();
        this.name = name;
        this.locality = p;
    }

    public Being(String name, Locality p, double pointX, double pointY) {
        super(pointX, pointY);
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
            goToLocality(locality);
            this.locality = locality;
            EventMessage.message(this.name + " переместился в  " + locality.getName());
        }
    }

    public void goToLocality(Locality finalLovality) {
        double xDist = Math.abs(finalLovality.getPointX() - this.pointX);

        double yDist = Math.abs(finalLovality.getPointY() - this.pointY);
        double diagonal = Math.sqrt(xDist * xDist + yDist * yDist);
        System.out.println(xDist);
        System.out.println(yDist);
        System.out.println(diagonal);

        long time = System.currentTimeMillis();
        while (finalLovality.getPointX() != this.getPointX() || finalLovality.getPointY() != this.getPointY()) {
            if (System.currentTimeMillis() - time >= 500) {
                if (Math.abs(finalLovality.getPointX() - this.getPointX()) >= (maxSpeed * xDist / diagonal)) {
                    this.pointX = this.pointX + maxSpeed * (xDist / diagonal) * Math.signum(finalLovality.getPointX() - this.pointX);
                } else this.pointX = finalLovality.getPointX();
                if (Math.abs(finalLovality.getPointY() - this.getPointY()) >= (maxSpeed * yDist / diagonal)) {
                    this.pointY = this.pointY + maxSpeed * (yDist / diagonal) * Math.signum(finalLovality.getPointY() - this.pointY);
                } else this.pointY = finalLovality.getPointY();
                System.out.println(this.PointToString());
                time = System.currentTimeMillis();
            }
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
        EventMessage.message(this.name + " отправился на перенос " + value + " единиц ресурса " + typeOfResource.getName() + " из " + fromTown.getName() + " в " + toTown.getName());
        takeResource(fromTown, typeOfResource, value);
        giveResource(toTown);
    }

    public void giveResource(Town townLocality) {
        this.setLocality(townLocality);
        if (getResValue() != 0) {
            townLocality.setResourceValue(myRes.getType(), townLocality.getResourceValue(myRes.getType()) + myRes.getValue());
            EventMessage.message(this.name + " отдёт " + myRes.getValue() + " единиц ресурса " + myRes.getType().getName() + " в место " + townLocality.getName());
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
                EventMessage.message(this.name + " забрал " + this.myRes.getValue() + " единиц ресурса " + myRes.getType().getName() + " в месте " + townLocality.getName(), 0);
            } else {
                EventMessage.message(this.name + " не смог забрать требуемые ресурсы в " + townLocality.getName() + ", так как они закончились", 0);
            }
        } else {
            EventMessage.message(this.name + " имеет в инвентаре " + this.myRes.getValue() + " единиц ресурса " + this.myRes.getType().getName() + ", поэтому он не может забрать ресурсы", 0);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Being being = (Being) obj;
        return getLocality() == being.getLocality() &&
                getName().equals(being.getName());
    }

    @Override
    public String toString() {
        return "Класс: " + getClass().getName() +
                "\nИмя: " + name +
                "\nМестоположение: " + locality.getName() +
                "\nИнвентарь: " + myRes.getType().getName() + " " + myRes.getValue() +
                "\nКоординаты: " + PointToString() +
                "\nhashCode: " + hashCode();

    }

    @Override
    public int hashCode() {
        return Objects.hash(name, locality);
    }
}

