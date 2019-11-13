package City.place;

public class Resource {
    private Resources type;
    private int value;
    public Resource(){
        this(Resources.NONE);
    }
    public Resource(Resources type) {
        this(type,0);
    }
    public Resource(Resources type, int value){
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
}
