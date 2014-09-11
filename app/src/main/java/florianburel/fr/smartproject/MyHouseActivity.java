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

import java.util.ArrayList;


public class MyHouseActivity extends Activity {
    private Network network;
    private ArrayList<Zone> zones;
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

        //Si j'ai un intent avec des données
        int value=0;
        try {
            value = this.getIntent().getExtras().getInt("dummy_value"); // dummy value passée depuis LoginActivity
        }
        catch (NullPointerException e)
        {
            //ya un pb !
        }


        recupReseau();
       
        
    }

    private void recupReseau() {

        final ProgressDialog progressBar = new ProgressDialog(this);
        progressBar.setMessage("Bienvenue sur l'application SmartPilot");
        progressBar.show();

        NetworkManager mgr = NetworkManager.getInstance();

        mgr.retrieveNetwork(new OnNetworkRetrievedListener() {
            @Override
            public void networkFound(Network n) {

                progressBar.dismiss();
                network = n;
                recupZones();
                
                


            }

            @Override
            public void onError(Exception e) {
                progressBar.setMessage("Impossible de se connecter au réseau");

            }

        });
    }

    private void recupZones() {
        // Je récupère les noms de zones leur point et leur HeatingMode
        NetworkManager mgr = NetworkManager.getInstance();
        zones = mgr.getAllZone(this);
        
        if(zones.size()==0){
            //Afficher le tuto
        }
        else{
            refresh();
        }
        
        
        
        
        
       
        
        


    }

    private void refresh() {
    }


    private void bind() {

        titleTextView = (TextView) findViewById(R.id.titleTextView);
        roomsListTextView = (TextView) findViewById(R.id.roomsListTextView);
        addRoomImageButton = (ImageButton) findViewById(R.id.addRoomImageButton);
        editRoomImageButton2 = (ImageButton) findViewById(R.id.editRoomImageButton2);
        roomsListView = (ListView) findViewById(R.id.roomsListView);


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
