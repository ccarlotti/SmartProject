package florianburel.fr.smartproject.model.modelobjets;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by fl0 on 09/09/2014.
 */
public class Zone
{

    public enum HeatingMode
    {
        ECO, CONFORT, STOP, HORS_GEL, PROG,
    }

    private String name;
    private ArrayList<Heater> heaters = new ArrayList<Heater>();
    private double point;
    private HeatingMode mode;

    // accessors

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Heater> getHeaters() {
        return heaters;
    }

    public void setHeaters(ArrayList<Heater> heaters) {
        this.heaters = heaters;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        for(Heater h : heaters)
        {
            h.setPoint(point);
        }
        this.point = point;
    }

    public HeatingMode getMode() {
        return mode;
    }

    public void setMode(HeatingMode mode) {
        for(Heater h : heaters)
        {
            h.setMode(mode);
        }
        this.mode = mode;
    }


    // constructors

    public Zone(String name) {
        this.name = name;

        for(int i = 0 ; i < 10 ; i++)
        {
            String heaterName = name + "_" + (i+1);
            Heater h = new Heater();
            h.setName(heaterName);
            heaters.add(h);
        }
        Random r = new Random();
        this.setPoint(Math.abs(r.nextInt() % 8) + 16);

        this.setMode(HeatingMode.STOP);
    }

}
