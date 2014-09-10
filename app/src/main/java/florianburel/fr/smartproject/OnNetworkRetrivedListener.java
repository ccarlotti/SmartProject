package florianburel.fr.smartproject;

/**
 * Created by Charly on 10/09/2014.
 */
public interface OnNetworkRetrivedListener
{

    void networkFound(Network n);

    void onError(Exception e);
}
