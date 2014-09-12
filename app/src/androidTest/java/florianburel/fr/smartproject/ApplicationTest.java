package florianburel.fr.smartproject;

import android.app.Application;
import android.content.Context;
import android.test.ApplicationTestCase;

import florianburel.fr.smartproject.model.NetworkManager;
import florianburel.fr.smartproject.model.modelobjets.Heater;
import florianburel.fr.smartproject.model.modelobjets.Network;
import florianburel.fr.smartproject.model.modelobjets.Zone;
import florianburel.fr.smartproject.model.webservice.ConnectServer;
import florianburel.fr.smartproject.model.webservice.OnNetworkRetrievedListener;
import florianburel.fr.smartproject.model.webservice.OnServerCreateAccountListener;
import florianburel.fr.smartproject.model.webservice.OnServerGetNetworkIdListener;
import florianburel.fr.smartproject.model.webservice.OnServerLoginListener;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    //TEST : createAccount with ConnectServer
    public  void testConnectServerCreateAccount()
    {
        //On utilise un context dummy car on n'a pas d'activity en Test Unitaire
        createApplication(); //Permet de récupérer un context dans les tests unitaires
        Context ctx = getApplication().getApplicationContext();
        ConnectServer csv = ConnectServer.getInstance(ctx);

        csv.createAccount("charly13111@hotmail.fr","courrier",new OnServerCreateAccountListener()
        {

            @Override
            public void CreateAccountOK() {
                assertTrue(true);

            }

            @Override
            public void CreateAccountKO() {
                assertTrue(false);

            }
        });
    }

    //TEST login with ConnectServer
    public void testConnecServerLogin()
    {
        //On utilise un context dummy car on n'a pas d'activity en Test Unitaire
        createApplication(); //Permet de récupérer un context dans les tests unitaires
        Context ctx = getApplication().getApplicationContext();
        ConnectServer csv = ConnectServer.getInstance(ctx);

        /*csv.login("ccarlotti@worldsat.fr", "cachou13100", new OnServerLoginListener()*/
        csv.login("charly13111@hotmail.fr", "courrier", new OnServerLoginListener(){
            @Override
            public void OnSucces() {
                assertTrue(true);

            }

            @Override
            public void OnFailed() {
                assertTrue(false);

            }
        });
    }

    //TEST : getNetworkId with ConnectServer
    public void testConnectServerGetNetworkId()
    {
        /*****************/
        /* Connection */
        /****************/
        //On utilise un context dummy car on n'a pas d'activity en Test Unitaire
        createApplication(); //Permet de récupérer un context dans les tests unitaires
        Context ctx = getApplication().getApplicationContext();
        final ConnectServer csv = ConnectServer.getInstance(ctx);

        csv.login("ccarlotti@worldsat.fr","cachou13100",new OnServerLoginListener() {
            @Override
            public void OnSucces()
            {
                /****************/
                /* getNetworkID */
                /****************/
                csv.getNetworkId(new OnServerGetNetworkIdListener() {
                    @Override
                    public void onNetworkFound(String id)
                    {
                        String s = "ADADADAD"; //NetworhID de test dans la table "networkID" de PARSE
                        assertTrue(id.equals(s));
                    }
                });
            }

            @Override
            public void OnFailed() {

            }
        });
    }

    // TEST : Recuperation du reseau
    public void testRetrieveNetwork()
    {
        NetworkManager mgr = NetworkManager.getInstance();

        mgr.retrieveNetwork(new OnNetworkRetrievedListener() {
            @Override
            public void networkFound(Network n) {
                n.getIdentifer().equals("toto");
            }

            @Override
            public void onError(Exception e) {
                assertTrue(false);

            }

        });
    }

    // TEST : une nouvelle zone doit avoir 10 radiateurs
    public void testNewZoneHas10Heaters()
    {
        Zone z = new Zone("test");

        assertTrue(z.getHeaters().size() == 10);

    }

    // TEST : une nouvelle zone doit avoir un sp entre 16 et 25;
    public void testNewZoneSPBetween16And25()
    {
        Zone z = new Zone("Test");
        assertTrue(z.getPoint() < 26 && z.getPoint() > 15);
    }

    // TEST : une nouvelle zone doit etre creer en mode STOPPED
    public void testNewZoneIsInStopMode()
    {
        Zone z = new Zone("TEST");
        assertTrue(z.getMode() == Zone.HeatingMode.STOP);
    }

    // TEST : Changer le sp d'une zone change le sp de tous ses radiateurs
    public void testSPChangeToAllHeaters()
    {
        Zone z = new Zone("Test");
        for(int i = 16; i < 26; i++)
        {
            z.setPoint(i);

            for(Heater h : z.getHeaters())
            {
                assertTrue(h.getPoint() == i);
            }
        }


    }

    // TEST : Changer le mode d'une zone change le mode de tous ces radiateurs.
    public void testModeChangeToAllHeaters()
    {
        Zone z = new Zone("Test");

        Zone.HeatingMode test;
        test = Zone.HeatingMode.ECO;
        z.setMode(test);

        for(Heater h : z.getHeaters())
        {
            assertTrue(h.getMode() == test);
        }

        test = Zone.HeatingMode.PROG;
        z.setMode(test);

        for(Heater h : z.getHeaters())
        {
            assertTrue(h.getMode() == test);
        }

    }


}