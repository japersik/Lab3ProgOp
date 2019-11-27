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
        this.locality = locality;
    }

    public void goToLocality(Locality finalLocality) {
        if (finalLocality == this.locality) {
            EventMessage.message(this.name + " находится в " + locality.getName(), 0);
        } else {
            EventMessage.message(this.name + " начал движение в пункт " + finalLocality.getName());
            goMove(finalLocality.getPointX(), finalLocality.getPointY());
            this.locality = finalLocality;
            EventMessage.message(this.name + " переместился в " + locality.getName());
        }
    }

    public void goMove(double goPointX, double goPointY) {
        this.locality = null;
        double xDist = Math.abs(goPointX - this.pointX);
        double yDist = Math.abs(goPointY - this.pointY);
        double diagonal = Math.sqrt(xDist * xDist + yDist * yDist);
        long time = System.currentTimeMillis();
        while (goPointX != this.getPointX() || goPointY != this.getPointY()) {

            if (System.currentTimeMillis() - time >= 1) {
                if (Math.abs(goPointX - this.getPointX()) >= (maxSpeed * xDist / diagonal)) {
                    this.pointX = this.pointX + maxSpeed * (xDist / diagonal) * Math.signum(goPointX - this.pointX);
                } else this.pointX = goPointX;
                if (Math.abs(goPointY - this.getPointY()) >= (maxSpeed * yDist / diagonal)) {
                    this.pointY = this.pointY + maxSpeed * (yDist / diagonal) * Math.signum(goPointY - this.pointY);
                } else this.pointY = goPointY;
                System.out.println(this.name + "  " + this.PointToString());//Проверка координат
                time = System.currentTimeMillis();
            }
        }

    }

    public void goToForAWalk() {
        Locality hisLocality = this.locality;
        EventMessage.message(this.name + " пошёл погулять");
        goMove(Math.random() * 200 - 100, Math.random() * 200 - 100);
        if (hisLocality != null) {
            goToLocality(hisLocality);
        }
    }

    public void Leave() {
        EventMessage.message(this.name + " ушёл в неизвестном направлении");
        goMove(Math.random() * 200 - 100, Math.random() * 200 - 100);
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
        goToLocality(townLocality);
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
        this.goToLocality(townLocality);
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

