package City;

public final class EventMessage {

    public static void message(String info, double s){
        System.out.println(info);
        if((Math.random()<s)&&(!info.contains("Создана новая скульптура"))){
            new Sculpture(info);
        }
    }

    public static void message(String info){
        double s = Math.random()/1.8;
//        double s = 0;
        message(info,s);
        }

    }
//    public static void message(String info, boolean makeInfo){
//        System.out.println(info);
//
//    }

