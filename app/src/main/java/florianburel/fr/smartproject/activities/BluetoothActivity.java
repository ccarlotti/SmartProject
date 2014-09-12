package florianburel.fr.smartproject.activities;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import florianburel.fr.smartproject.R;

public class BluetoothActivity extends Activity implements View.OnClickListener, BluetoothAdapter.LeScanCallback {

    private Button button;
    private ListView listView;

    private ArrayList<String> devices = new ArrayList<String>();
    private BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth2);

        this.devices.add("Test");



        bind();

        refreshListView();
    }

    private void bind() {
        this.button = (Button) findViewById(R.id.button);
        this.listView = (ListView) findViewById(R.id.listView);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(!isBluetoothEnabled() || !BluetoothAdapter.getDefaultAdapter().isEnabled())
        {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(enableBtIntent);
        }
        else
        {


            // Recuperation du bluetooth adapter
            final BluetoothManager bluetoothManager =
                    (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
            bluetoothAdapter = bluetoothManager.getAdapter();


            beginScan();
        }


    }

    private void beginScan() {

        // Start scan pour L.E. device
        bluetoothAdapter.startLeScan(this);

        Toast.makeText(this, "Begin scan", Toast.LENGTH_LONG).show();
    }

    public boolean isBluetoothEnabled()
    {

        BluetoothAdapter blueAdapter = BluetoothAdapter.getDefaultAdapter();
        return blueAdapter != null;
    }

    @Override
    public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bytes) {


        this.devices.add("device : " + bluetoothDevice.getName());

        refreshListView();

    }

    private void refreshListView() {
        this.listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, this.devices));
    }
}
