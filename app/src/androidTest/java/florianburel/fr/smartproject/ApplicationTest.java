package florianburel.fr.smartproject;

import android.app.Application;
import android.test.ApplicationTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    // TEST : une nouvelle zone doit avoir 10 radiateurs
    public void testNewZoneHas10Heaters()
    {
        Zone z = new Zone("test");

        assertTrue(z.getHeaters().size() == 10);

    }

    // TEST : une nouvelle zone doit avoir un sp entre 16 et 25;
    // TEST : une nouvelle zone doit etre creer en mode STOPPED
    // TEST : Changer le sp d'une zone change le sp de tous ses radiateurs
    // TEST : Changer le mode d'une zone change le mode de tous ces radiateurs.


}