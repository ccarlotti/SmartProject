package florianburel.fr.smartproject;

/**
 * Created by fl0 on 08/09/2014.
 */



public class Heater {

    public enum HeaterPower
    {
        HIGH, MEDIUM, LOW

    }

    private String name;
    private HeaterPower power;
    private boolean online;
    private double point;
    private Zone.HeatingMode mode;
    private boolean localModeEnabled;
    private int offset;


    // Constructors

    public Heater() {
        name = "bedroom_1";
        power = HeaterPower.MEDIUM;
        online = true;
        point = 20;
        mode = Zone.HeatingMode.ECO;
        localModeEnabled = true;
        offset = 2;
    }

    // Accessors


    public String getName() {
        return name;
    }

    public HeaterPower getPower() {
        return power;
    }

    public boolean isOnline() {
        return online;
    }

    public double getPoint() {
        return point;
    }

    public Zone.HeatingMode getMode() {
        return mode;
    }

    public boolean isLocalModeEnabled() {
        return localModeEnabled;
    }

    public int getOffset() {
        return offset;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPower(HeaterPower power) {
        this.power = power;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public void setMode(Zone.HeatingMode mode) {
        this.mode = mode;
    }

    public void setLocalModeEnabled(boolean localModeEnabled) {
        this.localModeEnabled = localModeEnabled;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
