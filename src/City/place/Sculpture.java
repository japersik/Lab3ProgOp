package City.place;

import City.EventMessage;

public class Sculpture {
    private String info;


    public Sculpture(String info) {
        this.info = info;
        EventMessage.message("Создана новая скульптура, посвящённая событию: " + this.info);
    }

    public String getInfo() {
        return info;
    }

}
