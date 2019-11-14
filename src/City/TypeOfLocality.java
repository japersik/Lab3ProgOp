package City;

public enum TypeOfLocality {
    TOWN("Город"),
    MINE("Шахта");
    private String name;

    TypeOfLocality(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
