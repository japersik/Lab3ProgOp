package City.place;

public enum Resources {
    STONE("Камень"),
    PROTOPLASM("Протоплазма"),
    CELLMASS("Клеточная масса"),
    NONE("---");
    private String  name;
    Resources(String name) {
        this.name = name;
    }
    public String data() {
        return name;
    }
}
