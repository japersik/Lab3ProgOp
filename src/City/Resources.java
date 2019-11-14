package City;

public enum Resources {
    STONE("Камень"),
    PROTOPLASM("Протоплазма"),
    CELLMASS("Клеточная масса"),
    NONE("---");
    private String  name;
    Resources(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
