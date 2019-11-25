package City;

public class Worker extends Being {
    private static int number;
    private Town home;

    {
        number = number + 1;
        System.out.println("Появился Штоггот с именем " + this.name);
    }
    public Worker(Wiseacre maker) {
        this(maker, "Безымянный Штоггот № " + number);
    }

    public Worker(Wiseacre maker, String nameOfWorker) {
        super(nameOfWorker, maker.getLocality(), maker.getPointX(), maker.pointY);

    }

    public void goMine(Mine mineLocality, Town townLocality) {
        EventMessage.message(this.name + " отправился на добычу ресурсов в  " + mineLocality.getName());
        goToLocality(mineLocality);
        if (this.myRes.getValue() == 0) {
            if (mineLocality.getMineResValue() >= maxResourceValues) {
                this.myRes.setValue(maxResourceValues);
                mineLocality.setMineResValue(mineLocality.getMineResValue() - maxResourceValues);
            } else {
                this.myRes.setValue(mineLocality.getMineResValue());
                mineLocality.setMineResValue(0);

                EventMessage.message("В " + mineLocality.getName() + " закончились ресурсы.");
            }
            if (this.myRes.getValue() > 0) {
                this.myRes.setType(mineLocality.getMineResType());
                EventMessage.message("Штоггот " + this.name + " добыл " + this.myRes.getValue() + " единиц " + this.myRes.getType().getName(), 0);
            }
        } else {
            EventMessage.message("В инвентаре штоггота " + this.name + " есть " + this.myRes.getValue() + " единиц " + this.myRes.getType() + ", поэтому он не может работать в шахте", 0);
        }
        this.giveResource(townLocality);
    }


}
