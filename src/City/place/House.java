package City.place;

import City.Being.*;
import City.EventMessage;

import java.awt.event.WindowFocusListener;

public class House {
    private static int amount;
    private int number;

    public House(Wiseacre maker) {
        amount = amount + 1;
        this.number = amount;
        EventMessage.message(  maker.getName() + " строит дом номер " + number);
        if (!(maker.getLocality() == null) && ((maker.getLocality().getType()) == TypeOfLocality.TOWN)) {
            ((Town)maker.getLocality()).addHouses(this);
        } else {
            EventMessage.message("Дом номер " + number + " находится за пределами какого-либо населённого пункта", 0);
        }
    }

    public static int getAmount() {
        return amount;
    }

    public int getNumber() {
        return number;
    }
}
