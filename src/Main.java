import City.*;

public class Main {
    public static void main(String[] args) {
        World mir = new World();
        mir.addWiseacres(new Wiseacre("Старый Старец"));
        mir.addWiseacres(new Wiseacre("Старый Старец2"));
        mir.addWiseacres(new Wiseacre("Старый Старец3 "));
        mir.addTowns(new Town("Новый город", "Моское дно"));
        mir.addTowns(new Town("Новая Атлантида", "Моское дно"));
        mir.addMines(new Mine("Лужа протоплазмы", Resources.PROTOPLASM, 1000, "Морское дно"));
        mir.addMines(new Mine("Залежи клеточной массы", Resources.CELLMASS, 1000, "Морское дно"));
        mir.go();
    }
}
