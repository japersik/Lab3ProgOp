package City;

import java.util.Objects;

public class Sculpture {
    private String info;


    public Sculpture(String info) {
        this.info = info;
        EventMessage.message("Создана новая скульптура, посвящённая событию: " + this.info);
    }

    public String getInfo() {
        return info;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Sculpture sculpture = (Sculpture) obj;
        return info.equals(sculpture.getInfo());
    }

    @Override
    public String toString() {
        return "Класс: " + getClass().getName() +
                "\nИнформация: " + info +
                "\nhashCode: " + hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(info);
    }
}
