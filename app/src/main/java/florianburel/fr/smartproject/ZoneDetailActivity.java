package florianburel.fr.smartproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.LinkedHashMap;


public class ZoneDetailActivity extends Activity {

    private Zone zone = new Zone("Cuisine");

    private TextView zoneModeTextView;
    private TextView zoneSetPointTextView;
    private ListView heatersListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone_detail);

        bind();

        setDisplay();

    }

    private void setDisplay() {
        setTitle(zone.getName());

        // Mode
        switch (zone.getMode())
        {

            case ECO:
                zoneModeTextView.setText(getString(R.string.eco));
                break;
            case CONFORT:
                zoneModeTextView.setText(getString(R.string.confort));
                break;
            case STOP:
                zoneModeTextView.setText(getString(R.string.stopped));
                break;
            case HORS_GEL:
                zoneModeTextView.setText(getString(R.string.freeze));
                break;
            case PROG:
                zoneModeTextView.setText(getString(R.string.prog));
                break;
        }

        // setPoint
        String setpoint = zone.getPoint() + "°C";
        zoneSetPointTextView.setText(setpoint);

    }

    private void bind() {
        zoneModeTextView = (TextView) findViewById(R.id.zoneModeTextView);
        zoneSetPointTextView = (TextView) findViewById(R.id.zoneSetPointTextView);
        heatersListView = (ListView) findViewById(R.id.heatersListView);
    }


}
