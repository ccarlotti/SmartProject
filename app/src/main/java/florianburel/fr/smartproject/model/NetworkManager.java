package florianburel.fr.smartproject.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import florianburel.fr.smartproject.model.database.StorageHelper;
import florianburel.fr.smartproject.model.modelobjets.Network;
import florianburel.fr.smartproject.model.modelobjets.Zone;
import florianburel.fr.smartproject.model.webservice.ConnectServer;
import florianburel.fr.smartproject.model.webservice.OnNetworkRetrievedListener;
import florianburel.fr.smartproject.model.webservice.OnServerGetNetworkIdListener;

/**
 * Created by fl0 on 10/09/2014.
 */
public class NetworkManager
{

    private Network network;

    public void retrieveNetwork(final OnNetworkRetrievedListener l)
    {
         if(network != null)
        {
            l.networkFound(network);
        }
        else
        {
            final StorageHelper store = new StorageHelper(context);
            String network_id = store.getNetworkID();

            //Si networkId enregistré dans les préférence des Applications
            if(network_id != null)
            {
                //alors se connecter au réseau domestique
                network = new Network(network_id);
                l.networkFound(network);
            }
            //Si networkId non enregistré dans les préférence des Applications
            //aller la chercher sur PARSE
            else
            {
                ConnectServer server = ConnectServer.getInstance(context); //récupération du Singleton

                //se connecter au serveur et récupérer le networkId
                final String finalNetwork_id = network_id;
                server.getNetworkId(new OnServerGetNetworkIdListener()
                {
                    //Exécuté lorsque l'Id est trouvé
                    @Override
                    public void onNetworkFound(String id)
                    {
                      //Sauvegarder le networkId dans les préférences des App
                      store.setNetworkID(id);
                        //alors se connecter au réseau domestique
                        network = new Network(id);
                        l.networkFound(network);
                    }
                });
            }
        }
    }

    public ArrayList<Zone> getAllZone(Context context)
    {
        ArrayList<Zone> z=  new ArrayList<Zone>();
        z.add(new Zone("toto"));
        z.add(new Zone("titi"));
        z.add(new Zone("tutu"));
        z.add(new Zone("tata"));
        return z;
    }

    public Zone addZone(Context context, String name)
    {
        return new Zone(name);
    }


 /*******************Singleton***********************/

    private static NetworkManager instance;
    private static Context context;


    public static NetworkManager getInstance(Context c) {
        if(instance == null)
        {
            instance = new NetworkManager(c);
        }
        return instance;
    }

    //Constructeur
    //A chaque fois qu'on appèle le NetworkManager, on récupère le ctx courant
    private NetworkManager(Context ctx)
    {
      ctx = ctx.getApplicationContext();//On récupère le  ctx courant
      context = ctx; //update du singleton
    }

    /*************************************************/
}
