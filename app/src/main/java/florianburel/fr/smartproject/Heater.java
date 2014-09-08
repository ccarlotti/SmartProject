package florianburel.fr.smartproject;

/**
 * Created by fl0 on 08/09/2014.
 */
public class Heater {

    private String name;
    private HeaterPower power;
    private boolean online;
    private double point;
    private HeaterMode mode;
    private boolean localModeEnabled;
    private double offset;


    // Constructors

    public Heater() {
        name = "bedroom_1";
        power = HeaterPower.MIDDLE;
        online = false;
        point = 20;
        mode = HeaterMode.ECO;
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

    public HeaterMode getMode() {
        return mode;
    }

    public boolean isLocalModeEnabled() {
        return localModeEnabled;
    }

    public double getOffset() {
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

    public void setMode(HeaterMode mode) {
        this.mode = mode;
    }

    public void setLocalModeEnabled(boolean localModeEnabled) {
        this.localModeEnabled = localModeEnabled;
    }

    public void setOffset(double offset) {
        this.offset = offset;
    }
}
