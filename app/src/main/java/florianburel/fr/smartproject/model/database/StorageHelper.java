package florianburel.fr.smartproject.model.database;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import florianburel.fr.smartproject.R;
import florianburel.fr.smartproject.model.modelobjets.Zone;

/**
 * Created by fl0 on 11/09/2014.
 */
public class StorageHelper extends SQLiteOpenHelper{

    private final static String PrefAppFileName="fr.smartpilot.www"; //Notation DNS inversée pourquoi pas ?
    private final static String PrefAppKeyNetworkId="key_networdid"; //Clé associée dans le dictionnaire des Keys

    private Context context;

    private final static String DB_NAME = "SMARTPROJECT.db";
    private final static int DB_VERSION = 1;
    private static final SQLiteDatabase.CursorFactory DB_FACTORY = null;


    public StorageHelper(Context context) {
        super(context, DB_NAME, DB_FACTORY, DB_VERSION);
        this.context = context; // utiliser pour récuperer les preferences
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // CREATION DE LA TABLE ZONE
        String sql = "create table Zone\n" +
                "(\n" +
                "  z_name \"VARCHAR()\",\n" +
                ");";
        sqLiteDatabase.execSQL(sql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        // Code de màj de la db
    }

    public ArrayList<Zone> getAllZone()
    {
        String sql = "SELECT z_name FROM Zone;";

        // TODO : Recuperer le resultat de la requete et le tranformer en ArrayList<Zone>

        return null;
    }


    public Zone createZone(String name)
    {
        // TODO : ajouter une nouvelle zone dans la database
        return null;
    }

    // Store & retrieve NetworkID in the preference system
    public String getNetworkID()
    {
        // TODO : récuperer le networkID dans les preferences
        SharedPreferences sharedPref = context.getSharedPreferences(PrefAppFileName,Context.MODE_PRIVATE);
        return sharedPref.getString(PrefAppKeyNetworkId,null); //Key,Value_default
    }

    //Dans les préférences de l'application
    public void setNetworkID(String networkID)
    {
        // TODO : enregistrer le networkID dans les preferences
        SharedPreferences sharedPref = context.getSharedPreferences(PrefAppFileName,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(PrefAppKeyNetworkId, networkID); //Key, Value
        editor.commit();//On écrit
    }
}
