package City.place;

import City.EventMessage;

public abstract class Locality {

    protected String name ;
    protected String location;
    protected TypeOfLocality type;

    public Locality(String name,TypeOfLocality type) {
        this(name, type, " Неизвестная локация");
    }

    public Locality(String name, TypeOfLocality type, String location) {
        this.type = type;
        this.location = location;
        this.name = name;
        EventMessage.message("Заложено новое место типа " + this.type.data() + " с названием " + this.name + " в локации " + this.location);
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

//    public abstract void resourcesList();

}
