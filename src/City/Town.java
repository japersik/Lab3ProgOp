package City;

import java.util.ArrayList;
import java.util.Objects;

public class Town extends Locality {


    private ArrayList<House> Houses = new ArrayList<>();
    private ArrayList<LuminousCreature> LuminousCreatures = new ArrayList<>();
    private Resource myStone = new Resource(Resources.STONE);
    private Resource myProtoplasm = new Resource(Resources.PROTOPLASM);
    private Resource myCellMass = new Resource(Resources.CELLMASS);

    public Town(String name, String location) {
        super(name, TypeOfLocality.TOWN, location);
    }

    public Town(String name, String location, int Stone, int Protoplasm, int CellMass) {
        super(name, TypeOfLocality.TOWN, location);
        this.myStone.setValue(Stone);
        this.myProtoplasm.setValue(Protoplasm);
        this.myCellMass.setValue(CellMass);
    }

    public void addHouses(House house) {
        this.Houses.add(house);
        EventMessage.message("В населённом пункте " + this.name + " зарегистрирован новый дом ");
    }

    public ArrayList<House> getHouses() {
        return Houses;
    }

    public void addLuminousCreature(LuminousCreature luminousCreature) {
        this.LuminousCreatures.add(luminousCreature);
    }

    public int getResourceValue(Resources typeOfResource) {
        int value = 0;
        switch (typeOfResource) {
            case STONE:
                value = myStone.getValue();
                break;
            case CELLMASS:
                value = myCellMass.getValue();
                break;
            case PROTOPLASM:
                value = myProtoplasm.getValue();
                break;
        }
        return value;
    }

    public void setResourceValue(Resources typeOfResource, int value) {
        switch (typeOfResource) {
            case STONE:
                myStone.setValue(value);
                break;
            case CELLMASS:
                myCellMass.setValue(value);
                break;
            case PROTOPLASM:
                myProtoplasm.setValue(value);
                break;
        }
    }

    public Resources getMinResType() {
        Resources minType = Resources.STONE;
        if (getResourceValue(Resources.STONE) > getResourceValue(Resources.PROTOPLASM)) {
            minType = Resources.PROTOPLASM;
        }
        if (getResourceValue(minType) > getResourceValue(Resources.CELLMASS)) {
            minType = Resources.CELLMASS;
        }
        return minType;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Town town = (Town) obj;
        return getLocation().equals(town.getLocation()) &&
                getName().equals(town.getName()) &&
                getHouses().size() == town.getHouses().size();
    }

    @Override
    public String toString() {
        return "Класс: " + getClass().getName() +
                "\nТип: " + type.getName() +
                "\nНазвание: " + name +
                "\nМестность: " + location +
                "\nСклад ресурсов: " + myStone.getType().getName() + " " + myStone.getValue() + ", " + myProtoplasm.getType().getName() + " " + myProtoplasm.getValue() + ", " + myCellMass.getType().getName() + " " + myCellMass.getValue() +
                "\nКоличество построенных домов: " + Houses.size() +
                "\nhashCode: " + hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, location, type.getName());
    }
}
