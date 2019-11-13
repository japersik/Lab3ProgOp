package City.place;

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
}
