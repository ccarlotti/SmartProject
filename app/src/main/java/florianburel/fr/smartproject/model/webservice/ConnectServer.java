package florianburel.fr.smartproject.model.webservice;

import android.content.Context;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.List;

/**
 * Created by Charly on 11/09/2014.
 */
public class ConnectServer
{
    /*******************Singleton***********************/

    private static ConnectServer instance;


    public static ConnectServer getInstance(Context c) {
        if(instance == null)
        {
            instance = new ConnectServer(c);

        }
        return instance;
    }

    //Constructor
    private ConnectServer(Context context) {
        Context c = context.getApplicationContext();//récupère le contexte de l'activity encours
        //car Parse à besoin du context de l'activity en cours, on ne sait pas pourquoi
        Parse.initialize(c,"JkuhbtHCDAV8ca5zunTclyFFSL5GZy5w01LrTwp8","0Mq7v9KNCl8HlohmJE1UpNkiPCSBaPME8HdaJuLd");//Parse APPId + Client Key
    }

    /*************************************************/
    private ParseUser connectedUser; //Variable qui dure toute la durée de vie du singleton
     //c'est comme une static car variable de l'instance du singleton

    //méthode login
    public void login(String email,String pw, final OnServerLoginListener l)
    {
        ParseUser.logInInBackground(email, pw, new LogInCallback()
        {
            @Override
            public void done(ParseUser parseUser, ParseException e)
            {
                if(parseUser != null)//si login succes
                {
                    connectedUser = parseUser;
                    l.OnSucces(); //event du listener
                }
                else
                {
                    l.OnFailed();
                }
            }
        });
    }

    //méthode getNetworkId
    public  void getNetworkId(final OnServerGetNetworkIdListener l)
    {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("network"); //nom de la table créee dans le prj parse.com "SmartPilot"
        query.whereEqualTo("user",connectedUser); //nom de la colonne à filtrer dans la table ci-dessus avec la valeur du filtre
        //Listener lorsque l'info à été trouvée dans la table
        query.findInBackground(new FindCallback<ParseObject>()
        {
            @Override
            public void done(List<ParseObject> objects, ParseException e)
            {
                //Si pas de pb
                if (e == null) {
                    ParseObject network = objects.get(0);//On récupère le premier objet de la liste retournée car un seul élément
                    String id = network.getString("networkID"); //On récupère le networkID dans la colonne du même nom de la table "network"
                    l.onNetworkFound(id); //On solicite le listener
                } else {

                }
            }
        });
    }

    public void createAccount(String emailSaisie, String motDePasseSaisie,final OnServerCreateAccountListener l )
    {
        final ParseUser user = new ParseUser();
        user.setUsername(emailSaisie); //same as email
        user.setPassword(motDePasseSaisie);
        user.setEmail(emailSaisie);

        //méthode asynchrone
        user.signUpInBackground(new SignUpCallback()
        {
            public void done(ParseException e)
            {
                if (e == null)
                {
                    // Hooray! Let them use the app now.
                    connectedUser = user; // On set le connectedUser car le connect est fait en même temps que le SignUp
                    l.CreateAccountOK();
                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                    l.CreateAccountKO();
                }
            }
        });
    }
}
