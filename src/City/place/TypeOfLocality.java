package City.place;

public enum TypeOfLocality {
    TOWN("Город"),
    MINE("Шахта");
    private String  name;
    TypeOfLocality(String name) {
        this.name = name;
    }
    public String data() {
        return name;
    }
}
