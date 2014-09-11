package florianburel.fr.smartproject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MyHouse extends Activity {

    private TextView titleTextView;
    private TextView roomsListTextView;
    private ImageButton addRoomImageButton;
    private ImageButton editRoomImageButton2;
    private ListView roomsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_house);
        bind();

        ProgressDialog progressBar = new ProgressDialog(this);
        progressBar.setMessage("Recherche vos pièces installées.");
        progressBar.show();
       
        
    }




    private void bind() {

        titleTextView = (TextView) findViewById(R.id.titleTextView);
        roomsListTextView = (TextView) findViewById(R.id.roomsListTextView);
        addRoomImageButton = (ImageButton) findViewById(R.id.addRoomImageButton);
        editRoomImageButton2 = (ImageButton) findViewById(R.id.editRoomImageButton2);
        roomsListView = (ListView) findViewById(R.id.roomsListView);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_house, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }





}
