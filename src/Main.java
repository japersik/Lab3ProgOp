import City.Being.Wiseacre;
import City.Being.Worker;
import City.place.Mine;
import City.place.Resources;
import City.place.Town;

public class Main {
    public static void main(String[] args) {
        Town c2 = new Town("Старая-Антантида", "Море", 0, 100, 100);
        Wiseacre st1 = new Wiseacre("Наставник");
        Wiseacre st2 = new Wiseacre("Ученик");
//        Wiseacre st3 = new Wiseacre("Подаван");
        Town c1 = new Town("Новая-Атлантида", "Море");
        st1.setLocality(c1);
//        st2.setLocality(c1);
//        st3.setLocality(c1);
        Wiseacre st4 = new Wiseacre("Мастер", c2);
        st4.setLocality(c1);
        st4.resourceTransfer(c2, c1, Resources.PROTOPLASM);
        st4.resourceTransfer(c2, c1, Resources.CELLMASS);
        st1.Building();
        Worker karl1 = st4.CreatingWorkers("Карл");
        Mine mine = new Mine("Каменеломня", Resources.STONE, 100, c1.getLocation());
        karl1.goMine(mine, c1);
        karl1.goMine(mine, c1);
        st1.takeResource(c1, Resources.STONE, 10);
        st1.Building();
        st1.Building();
    }
}
