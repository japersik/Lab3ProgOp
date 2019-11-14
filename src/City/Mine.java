package City;

import java.util.Objects;

public class Mine extends Locality {
    private Resource mineRes = new Resource();

    public Mine(String name) {
        this(name, Resources.STONE);
    }

    public Mine(String name, Resources typeOfResources) {
        this(name, typeOfResources, (int) (Math.random() * 10000), "Неизвестная местность");
    }

    public Mine(String name, Resources typeOfResources, int value, String location) {
        super(name, TypeOfLocality.MINE, location);
        this.mineRes.setValue(value);
        this.mineRes.setType(typeOfResources);
    }

    public int getMineResValue() {
        return this.mineRes.getValue();
    }

    public Resources getMineResType() {
        return this.mineRes.getType();
    }

    public void setMineResValue(int value) {
        this.mineRes.setValue(value);
    }

    private void setMineResType(Resources type) {
        this.mineRes.setType(type);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Mine mine = (Mine) obj;
        return getLocation().equals(mine.getLocation()) &&
                getName().equals(mine.getName()) &&
                getMineResValue() == mine.getMineResValue() &&
                getMineResType().getName().equals(mine.getMineResType().getName());
    }

    @Override
    public String toString() {
        return "Класс: " + getClass().getName() +
                "\nТип: " + type.getName() +
                "\nНазвание: " + name +
                "\nМестность: " + location +
                "\nОстаток ресурсов: " + mineRes.getType().getName() + " " + mineRes.getValue() +
                "\nhashCode: " + hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, location, type.getName(), mineRes.getValue(), mineRes.getType().getName());
    }
}
