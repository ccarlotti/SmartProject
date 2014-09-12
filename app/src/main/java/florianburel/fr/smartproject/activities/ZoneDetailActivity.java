package florianburel.fr.smartproject.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import florianburel.fr.smartproject.R;
import florianburel.fr.smartproject.model.modelobjets.Heater;
import florianburel.fr.smartproject.model.modelobjets.Zone;


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

        // Liste des radiateurs
        HeaterAdapter adapter = new HeaterAdapter();
        heatersListView.setAdapter(adapter);
    }

    private void bind() {
        zoneModeTextView = (TextView) findViewById(R.id.zoneModeTextView);
        zoneSetPointTextView = (TextView) findViewById(R.id.zoneSetPointTextView);
        heatersListView = (ListView) findViewById(R.id.heatersListView);
    }


    private class HeaterAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            // Retourne le nombre de cellules à afficher
            return zone.getHeaters().size();
        }

        @Override
        public Object getItem(int i) {
            // Retourne l'objet (le radiateur) à la position i
            return zone.getHeaters().get(i);
        }

        @Override
        public long getItemId(int i) {
            // Retourne un identifiant unique pour la position
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup)
        {
            // Récuperer l'élément à afficher
            Heater heater = (Heater) getItem(i);

            // Récuperer une cellule d'apres le layout XML
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View cell = inflater.inflate(R.layout.cell_heater, null);

            // Recupération des elements graphiques
            TextView cellTextView = (TextView) cell.findViewById(R.id.cellTextView);
            ImageView cellImageView = (ImageView) cell.findViewById(R.id.cellImageView);

            // Nom du radiateur
            cellTextView.setText(heater.getName());

            // Image online
            if(heater.isOnline())
            {
                cellImageView.setImageResource(android.R.drawable.star_big_on);
            }
            else
            {
                cellImageView.setImageResource(android.R.drawable.star_big_off);
            }

            return cell;
        }
    }
}
