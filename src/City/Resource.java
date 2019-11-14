package City;

import java.util.Objects;

public class Resource {
    private Resources type;
    private int value;

    public Resource() {
        this(Resources.NONE);
    }

    public Resource(Resources type) {
        this(type, 0);
    }

    public Resource(Resources type, int value) {
        this.type = type;
        this.value = value;

    }

    public Resources getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    public void setType(Resources type) {
        this.type = type;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Resource resource = (Resource) obj;
        return getValue() == resource.getValue() &&
                getType() == resource.getType();
    }

    @Override
    public String toString() {
        return "Класс: " + getClass() +
                "\n:Ресурс " + type +
                "\nКоличество: " + value +
                "\nhashCode: " + hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value);
    }
}
