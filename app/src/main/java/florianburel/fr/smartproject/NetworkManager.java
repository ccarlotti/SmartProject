package florianburel.fr.smartproject;

/**
 * Created by fl0 on 10/09/2014.
 */
public class NetworkManager
{


    public void retrieveNetwork(OnNetworkRetrievedListener l)
    {
        // do something

        Network n = new Network("toto");

        l.networkFound(n);

        l.onError(new Exception());


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
