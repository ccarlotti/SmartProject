package florianburel.fr.smartproject.model.webservice;

import florianburel.fr.smartproject.model.modelobjets.Network;

/**
 * Created by fl0 on 10/09/2014.
 */
public interface OnNetworkRetrievedListener {

    void networkFound(Network n);


    void onError(Exception e);
}
