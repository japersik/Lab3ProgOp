package City;

import java.util.Objects;

public class LuminousCreature {
    private static int amount;
    private int number;

    public LuminousCreature(Wiseacre maker) {
        amount = amount + 1;
        this.number = amount;
        EventMessage.message(maker.getName() + " создаёт светящееся существо номер " + number);
        if (!(maker.getLocality() == null) && ((maker.getLocality().getType()) == TypeOfLocality.TOWN)) {
            ((Town) maker.getLocality()).addLuminousCreature(this);
        } else {
            EventMessage.message("Светящееся существо номер " + number + " находится за пределами какого-либо населённого пункта", 0);
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
        LuminousCreature house = (LuminousCreature) obj;
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
