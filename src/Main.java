import City.Town;
import City.Wiseacre;
public class Main {
    public static void main(String[] args) {
        Wiseacre kaka = new Wiseacre("Хотабыч");
        Town c2 = new Town("Старая-Антантида", "Море", 0, 100, 100);
        kaka.setLocality(c2);
//        Town t1 = new Town("Старый город", "Поверхность");
//        Wiseacre st1 = new Wiseacre("Наставник", t1);
//        Wiseacre st2 = new Wiseacre("Ученик", t1);
//        Town c1 = new Town("Новая-Атлантида", "Дно моря");
//        st1.setLocality(c1);
//        st2.setLocality(c1);
//        Wiseacre st4 = new Wiseacre("Мастер", c2);
//        st4.setLocality(c1);
//        st4.resourceTransfer(c2, c1, Resources.PROTOPLASM);
//        st4.resourceTransfer(c2, c1, Resources.CELLMASS);
//        st1.Building();
////        st4.takeResource(c1, Resources.PROTOPLASM, 5);
//        Worker karl1 = st4.CreatingWorkers("Карл");
//        Mine mine = new Mine("Каменеломня", Resources.STONE, 100, c1.getLocation());
//        karl1.goMine(mine, c1);
//        karl1.goMine(mine, c1);
//        st4.takeResource(c1, Resources.STONE);
//        st4.Building();
//        st1.Building();
//        System.out.println(c1.toString());
    }
}
