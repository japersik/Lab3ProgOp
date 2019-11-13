package City.place;

import City.EventMessage;

import java.util.ArrayList;

public class Town extends Locality {


    private ArrayList<House> Houses = new ArrayList<>();
    private Resource myStone = new Resource(Resources.STONE);
    private Resource myProtoplasm = new Resource(Resources.PROTOPLASM);
    private Resource myCellMass = new Resource(Resources.CELLMASS);

    public Town(String name, String location) {
        super(name, TypeOfLocality.TOWN, location);
    }
    public Town(String name, String location, int Stone,int Protoplasm, int CellMass) {
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


    public int getResourceValue(Resources typeOfResource) {
        int value =0;
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
        };
        return value;
    }

    public void setResourceValue(Resources typeOfResource, int value){
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
}
