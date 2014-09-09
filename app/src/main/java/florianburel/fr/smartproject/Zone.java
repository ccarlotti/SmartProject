package florianburel.fr.smartproject;

import java.util.ArrayList;

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
    private ArrayList<Heater> heaters;
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
        this.point = point;
    }

    public HeatingMode getMode() {
        return mode;
    }

    public void setMode(HeatingMode mode) {
        this.mode = mode;
    }


    // constructors

    public Zone(String name) {
        this.name = name;


        // TODO: Generer une liste de 10 radiateurs
        // TODO: definir une temp de consigne pour la zone
        // TODO: definir un mode pour la zone

    }

    // TODO : fwd la temp de consigne à tous les radiateurs
    // TODO : fwd le mode à tous les radiateurs

}
