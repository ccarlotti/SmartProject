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

    private void bind()
    {
        heaterNameTextView = (TextView) findViewById(R.id.heaterNameTextView);
        powerLowRadioButton = (RadioButton) findViewById(R.id.powerLowRadioButton);
        powerMediumRadioButton = (RadioButton) findViewById(R.id.powerMediumRadioButton);
        powerHighRadioButton = (RadioButton) findViewById(R.id.powerHighRadioButton);
        onlineImageView = (ImageView) findViewById(R.id.onlineImageView);
        temperatureSetPointTextView = (TextView) findViewById(R.id.temperatureSetPointTextView);
        heaterModeTextView = (TextView) findViewById(R.id.heaterModeTextView);
        localModeCheckBox = (CheckBox) findViewById(R.id.localModeCheckBox);
        TemperatureOffsetTextView = (TextView) findViewById(R.id.TemperatureOffsetTextView);
        TemperatureOffsetSeekBar = (SeekBar) findViewById(R.id.TemperatureOffsetSeekBar);
    }

}
