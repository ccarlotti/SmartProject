package florianburel.fr.smartproject;

/**
 * Created by Charly on 10/09/2014.
 */
public class NetworkManager
{
    //Méthode ASYNCHRONE pour rechercher le Network
    public void retrieveNetwork(OnNetworkRetrivedListener l)
    {
       // do something
       Network n = new Network("toto");

       l.networkFound(n); //Méthode de l'interface OnNetworkRetrivedListener

       l.onError(new Exception());
    }

    /********************SINGLETON*******************/
    private static NetworkManager instance;

    public static NetworkManager getInstance()
    {
        if(instance==null)
            instance = new NetworkManager();

        return instance;
    }

    //CONSTRUCTOR en private pour interdire l'accés direct
    private NetworkManager() {
    }
    /**************************************************/
}
