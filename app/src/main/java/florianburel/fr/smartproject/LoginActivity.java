package florianburel.fr.smartproject;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.LoaderManager.LoaderCallbacks;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.SignInButton;
import java.util.ArrayList;
import java.util.List;


/**
 * A login screen that offers login via email/password and via Google+ sign in.
 * <p/>
 * ************ IMPORTANT SETUP NOTES: ************
 * In order for Google+ sign in to work with your app, you must first go to:
 * https://developers.google.com/+/mobile/android/getting-started#step_1_enable_the_google_api
 * and follow the steps in "Step 1" to create an OAuth 2.0 client for your package.
 */
public class LoginActivity extends Activity
{



    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        bind();

        // Gere le click sur <Entrée> sur le clavier lors de la saisie du mot de passe
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        // Click sur le btn s'identifier
        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        // Click sur le bouton s'enregistrer
        Button signup = (Button) findViewById(R.id.email_sign_up);
        signup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    private void bind() {
        mEmailView = (EditText) findViewById(R.id.email);

        mPasswordView = (EditText) findViewById(R.id.password);
    }


    /**
     * Appeler lorsque l'utilisateur click sur "S'identifier"
     */
    public void attemptLogin()
    {
        final Context context = this;

        //On récupère le compte et le pw
        String emailSaisie = mEmailView.getText().toString(); //toString() car getText() retourne un tableau de char
        String passwordSaisie = mPasswordView.getText().toString();

        // verification de l'email / password
        if(isEmailValid(emailSaisie) && isPasswordValid(passwordSaisie))
        {
            // afficher un spinner
            final ProgressDialog dialog = new ProgressDialog(this); // this : (Context) activité en cours!
            dialog.setTitle(getString(R.string.spinner_title)); //Titre du spinner indexé pour les langues
            dialog.setMessage(getString(R.string.spinner_please_wait)); //Message du spinner
            dialog.show();//pour afficher à l'écran


            // demander a Parse de l'authentifier
            ConnectServer csv = ConnectServer.getInstance(this); //this car activité en cours

            csv.login(emailSaisie,passwordSaisie,new OnServerLoginListener() {
                // si ok : efface le spinner, on va à l'ecran suivant
                @Override
                public void OnSucces() {
                    dialog.dismiss();//pour effacer le spinner
                    gotoMyHouse(context);
                }
                // si erreur : effacer email / password et afficher un msg
                @Override
                public void OnFailed() {
                    dialog.dismiss();//pour effacer le spinner
                    AlertDialog.Builder ad = new AlertDialog.Builder(context); //this est le context en cours
                    ad.setTitle(getString(R.string.error_login_popup_title)); //Title du popup
                    ad.setMessage(getString(R.string.error_login_popup_msg));//Msg du popup
                    ad.show();

                }
            });

        }
        else
        {
            // Afficher message mauvais format d'email / password
        }



    }

    private void gotoMyHouse(Context context) {
        //On bascule sur l'autre activity "my_house_activity"
        Intent t = new Intent(context,MyHouseActivity.class);
        t.putExtra("dummy_value",10); //Valeur à passer à MyHouseActivity
        startActivity(t); //aller on change d'activitiy !
    }

    /**
     * Appeler lorsque l'utilisateur clique sur S'enregistrer
     */
    private void register()
    {
        final String emailSaisie = mEmailView.getText().toString();
        final String motDePasseSaisie = mPasswordView.getText().toString();

        final Context context = this;

        if(isEmailValid(emailSaisie) && isPasswordValid(motDePasseSaisie))
        {
            final AlertDialog.Builder ad = new AlertDialog.Builder(context); //this est le context en cours
            ad.setTitle("Voulez vous creer un compte"); //Title du popup
            ad.setPositiveButton("Ok", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    ConnectServer server = ConnectServer.getInstance(context);

                    server.createAccount(emailSaisie,motDePasseSaisie,new OnServerCreateAccountListener()
                    {

                        @Override
                        public void CreateAccountOK()
                        {
                            gotoMyHouse(context);
                        }

                        @Override
                        public void CreateAccountKO()
                        {
                            Toast.makeText(context,"Impossible de créer le compte",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            ad.show();
        }
        else
        {
            // Prevenir l'utilisateur d'une erreur de saisie
        }

    }



    // Methode verifiant la conformité des login & mot de passe
    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

}



