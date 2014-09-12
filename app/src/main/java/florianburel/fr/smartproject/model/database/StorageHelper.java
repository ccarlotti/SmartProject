package florianburel.fr.smartproject.model.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
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
                "  z_id    INTEGER not null primary key autoincrement,\n" +
                "  z_name  \"VARCHAR()\",\n" +
                "  z_mode  INTEGER,\n" +
                "  z_point INTEGER\n" +
                ");";
        sqLiteDatabase.execSQL(sql);

        sql = "create table Device\n" +
                "(\n" +
                "  z_id INTEGER,\n" +
                "  uuid INTEGER\n" +
                ");";
        sqLiteDatabase.execSQL(sql);

        sql = "INSERT INTO ZONE(z_id, z_name, z_mode, z_point) VALUES(0, \"zone 1\", 1, 20);";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO ZONE(z_id, z_name, z_mode, z_point) VALUES(1, \"zone 2\", 1, 20);";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO ZONE(z_id, z_name, z_mode, z_point) VALUES(2, \"zone 3\", 1, 20);";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int version_en_cours, int nouvelle_version) {

        switch (version_en_cours)
        {
            case 1:
                // maj v1 -> v2

            case 2:
                // maj v2 -> v3
        }
    }

    public ArrayList<Zone> getAllZone()
    {
        String sql = "SELECT z_id, z_name, z_mode, z_point FROM Zone;";

        // Stockera les resultats a retourner
        ArrayList<Zone> zones = new ArrayList<Zone>();

        // Recuperer la bdd en mode lecture seule
        SQLiteDatabase db = getReadableDatabase();

        // Executer le SQL
        Cursor c = db.rawQuery(sql, null); // 2eme param = arg[] (syntaxe printf)

        c.moveToFirst(); // deplace le cursor en haut a gauche
        while (!c.isAfterLast()) // parcours du tableau
        {
            // Recuperation des infos
            int id = c.getInt(0); // id
            String name = c.getString(1);
            int mode = c.getInt(2);
            int point  = c.getInt(3);

            // Chaque ligne = 1 Zone
            Zone zone = new Zone(name);
            zone.setMode(Zone.HeatingMode.ECO);
            zone.setPoint(point);
            zone.setId(id);

            // Ajout de la zone aux resultats
            zones.add(zone);

            c.moveToNext(); //!!!!!!!!!!!!! ligne suivantes
        }

        c.close(); // !!!!!!!!!!


        return zones;
    }


    public Zone createZone(String name)
    {
        Zone z = new Zone(name);
        z.setPoint(10);

        // Transformation en contentValues
        ContentValues cv = new ContentValues();
        cv.put("z_name", z.getName()); // Colonne - Valeur
        cv.put("z_point", z.getPoint());

        // Recup de la db en mode ecriture
        SQLiteDatabase db = getWritableDatabase();

         // Insertion en base
         db.insert("Zone", null, cv); // Insert cv dans la table Zone

        return z;
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
