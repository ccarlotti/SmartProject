package florianburel.fr.smartproject;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by fl0 on 10/09/2014.
 */
public class NetworkManager
{

    private Network network;

    public void retrieveNetwork(final OnNetworkRetrievedListener l)
    {
        //Délais virtuel d'attente de 10s
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Network n = new Network("toto");
                l.networkFound(n);
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, 10000);

     /*   if(network != null)
        {
            l.networkFound(network);
        }
        else
        {
            String network_id = recupereIDDansLesPreferencesDeLAppli();

            if(network_id != null)
            {
                network = new Network(network_id);
                l.networkFound(network);
            }
            else
            {
                network_id = recupererLIDViaParse();
                saveDansLesPreferencesDeLAppli(network_id);
                network = new Network(network_id);
                l.networkFound(network);
            }
        }*/
    }




























 /*******************Singleton***********************/

    private static NetworkManager instance;


    public static NetworkManager getInstance() {
        if(instance == null)
        {
            instance = new NetworkManager();
        }
        return instance;
    }

    private NetworkManager() {
    }

    /*************************************************/
}
