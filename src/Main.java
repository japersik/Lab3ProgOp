import City.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        World mir = new World();
        mir.addWiseacres(new Wiseacre("Старый Старец"));
        mir.addWiseacres(new Wiseacre("Старый Старец2"));
        mir.addWiseacres(new Wiseacre("Старый Старец3 "));
        mir.addTowns(new Town("Новый город", "Моское дно", 0, 17, 0));
        mir.addTowns(new Town("Новая Атлантида", "Моское дно", 0, 0, 10));
        mir.addMines(new Mine("А", Resources.PROTOPLASM, 1000, "Морское дно"));
        mir.addMines(new Mine("B", Resources.CELLMASS, 1000, "Морское дно"));
        mir.go();
//        mir.addMines(new Mine("C"));

//
//        mir.addTowns(kak);
//
//        System.out.println(mir.findRecourcesInMines(Resources.STONE));
//        System.out.println(mir.findRecourcesInMines(Resources.CELLMASS));
//
//        System.out.println(mir.findRecourcesInTowns(kak, Resources.PROTOPLASM).getName() + mir.findRecourcesInTowns(kak, Resources.PROTOPLASM).getResourceValue(Resources.PROTOPLASM));
//        System.out.println(mir.findRecourcesInTowns(kak, Resources.CELLMASS).getName() + mir.findRecourcesInTowns(kak, Resources.CELLMASS).getResourceValue(Resources.CELLMASS));

//        Wiseacre kaka = new Wiseacre("Хотабыч");
//        Wiseacre kaka2 = new Wiseacre("Хотабыч2");
//        Town c2 = new Town("Старая-Антантида", "Море", 0, 100, 100);
//        kaka.takeResource(c2, Resources.PROTOPLASM);
//        kaka.CreatingWorkers();
//        kaka2.goToLocality(c2);
//        kaka.goToLocality(c2);
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
//        System.out.println(c1.toString()); ge
    }
}
