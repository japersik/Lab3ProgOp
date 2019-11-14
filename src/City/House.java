package City;

import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        House house = (House) obj;
        return number == house.getNumber();
    }

    @Override
    public String toString() {
        return "Класс: " + getClass().getName() +
                "\nПорядковый номер : " + number +
                "\nhashCode: " + hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
