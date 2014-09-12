package florianburel.fr.smartproject.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import florianburel.fr.smartproject.R;

public class BluetoothActivity extends Activity implements View.OnClickListener {

    private Button button;
    private ListView listView;

    private ArrayList<String> devices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth2);
        bind();
    }

    private void bind() {
        this.button = (Button) findViewById(R.id.button);
        this.listView = (ListView) findViewById(R.id.listView);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}
