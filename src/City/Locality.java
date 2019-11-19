package City;

import java.util.Objects;

public abstract class Locality extends Point {

    protected String name ;
    protected String location;
    protected TypeOfLocality type;

    public Locality(String name,TypeOfLocality type) {
        this(name, type, " Неизвестная локация");
    }

    public Locality(String name, TypeOfLocality type, String location) {
        super();
        this.type = type;
        this.location = location;
        this.name = name;
        EventMessage.message("Заложено новое место типа " + this.type.getName() + " с названием " + this.name + " " + PointToString() + " в локации " + this.location);
    }


    public void setName(String newName) {
        if (this.getName() == "") {
            this.name = newName;

        } else {
            EventMessage.message(this.type + " изенил название  " + this.name + " на " + newName);
            this.name = newName;
        }

    }

    public String getName() {
        return name;
    }

    private void setLocation(String location) {
        this.location = location;
    }

    public TypeOfLocality getType() {
        return type;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Locality locality = (Locality) obj;
        return getLocation().equals(locality.getLocation()) &&
                getName().equals(locality.getName());
    }

    @Override
    public String toString() {
        return "Класс: " + getClass().getName() +
                "\nТип: " + type.getName() +
                "\nНазвание: " + name +
                "\nМестность: " + location +
                "\nhashCode: " + hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, location, type.getName());
    }
    //    public abstract void resourcesList();

}
