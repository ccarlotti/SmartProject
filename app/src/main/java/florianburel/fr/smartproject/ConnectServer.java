package florianburel.fr.smartproject;

import android.content.Context;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;

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

    //méthode login
    public void login(String email,String pw, final OnServerLoginListener l)
    {
        ParseUser.logInInBackground(email, pw, new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                if(parseUser != null)//si login succes
                {
                    l.OnSucces(); //event du listener
                }
                else
                {
                    l.OnFailed();
                }
            }
        });
    }
}
