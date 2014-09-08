package florianburel.fr.smartproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;


public class Main extends Activity {

    private Heater heater = new Heater();

    private TextView heaterNameTextView;
    private RadioButton powerLowRadioButton;
    private RadioButton powerMediumRadioButton;
    private RadioButton powerHighRadioButton;
    private ImageView onlineImageView;
    private TextView temperatureSetPointTextView;
    private TextView heaterModeTextView;
    private CheckBox localModeCheckBox;
    private TextView TemperatureOffsetTextView;
    private SeekBar TemperatureOffsetSeekBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind(); // recuperer les composants graphiques
    }

    private void bind() {

    }

}
