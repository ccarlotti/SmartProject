package florianburel.fr.smartproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import static android.R.drawable.star_big_off;
import static android.R.drawable.star_big_on;


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
    private TextView temperatureOffsetTextView;
    private SeekBar temperatureOffsetSeekBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind(); // recuperer les composants graphiques
        setDisplay(); // afficher les valeurs du model dans la vue
    }

    private void setDisplay() {

        //title
        heaterNameTextView.setText(heater.getName());

        // Power
        powerLowRadioButton.setChecked(heater.getPower() == Heater.HeaterPower.LOW);
        powerMediumRadioButton.setChecked(heater.getPower() == Heater.HeaterPower.MEDIUM);
        powerHighRadioButton.setChecked(heater.getPower() == Heater.HeaterPower.HIGH);

        //  status
        if(heater.isOnline())
        {
            onlineImageView.setImageResource(star_big_on);
        }
        else
        {
            onlineImageView.setImageResource(star_big_off);
        }

        // setPoint
        String setpoint = heater.getPoint() + "°C";
        temperatureSetPointTextView.setText(setpoint);

        // Mode
        switch (heater.getMode())
        {

            case CONFORT:
                heaterModeTextView.setText("Confort");
                break;
            case STOP:
                heaterModeTextView.setText("Stopped");
                break;
            case ECO:
                heaterModeTextView.setText("Eco");
                break;
            case HORS_GEL:
                heaterModeTextView.setText("Min");
                break;
            case PROG:
                heaterModeTextView.setText("Prog");
                break;
        }

        // local
        localModeCheckBox.setChecked(heater.isLocalModeEnabled());

        // offset
        String offset = heater.getOffset() + "°C";
        temperatureOffsetTextView.setText(offset);
        int progress = (int) (100 * (heater.getOffset() + 8) / 16);
        temperatureOffsetSeekBar.setProgress(progress);
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
        temperatureOffsetTextView = (TextView) findViewById(R.id.TemperatureOffsetTextView);
        temperatureOffsetSeekBar = (SeekBar) findViewById(R.id.TemperatureOffsetSeekBar);
    }

}
