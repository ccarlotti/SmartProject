package florianburel.fr.smartproject;

import android.app.Application;
import android.content.Context;
import android.test.ApplicationTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

       //TEST login with ConnectServer
    public void testConnecServerLogin()
    {
        //On utilise un context dummy car on n'a pas d'activity en Test Unitaire
        createApplication(); //Permet de récupérer un context dans les tests unitaires
        Context ctx = getApplication().getApplicationContext();
        ConnectServer csv = ConnectServer.getInstance(ctx);

        csv.login("ccarlotti@worldsat.fr","cachou13100",new OnServerLoginListener() {
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