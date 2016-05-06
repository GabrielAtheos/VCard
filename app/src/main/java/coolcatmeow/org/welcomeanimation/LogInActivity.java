package coolcatmeow.org.welcomeanimation;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class LogInActivity extends AppCompatActivity {
    public final static String ID = "coolcatmeow.org.welcomeanimation.ID";
    private myClass connectionTask = null;
    private ServerConnection serverConnection = null;
    public static String USEREMAIL = null;
    public static String PASSWORD = null;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

//        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        /*
            Juan, we need to set USEREMAIL equal to the user's login email address. That way we can
            use that to sort out who's data to update when updating the database.
         */

        //login email text field
        final EditText emailTextEdit = (EditText) findViewById(R.id.editLoginEmail);
        emailTextEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() != 0)
                    USEREMAIL = emailTextEdit.getText().toString();
            }
        });


        //password text field
        final EditText passwordTextEdit = (EditText) findViewById(R.id.editPasswordText);
        passwordTextEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() != 0)
                    PASSWORD = passwordTextEdit.getText().toString();
            }
        });

        //Button to Create new Account
        Button goToMainActivity = (Button) findViewById(R.id.buttonNewAccount);
        goToMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                startActivity(intent);

                String text = "";
                if(USEREMAIL == null || PASSWORD == null)
                {
                    text = "Email and password fields must not be left blank";
                }
                else
                {
                    //let's hope--------------------
                    String command = "::adduser%%";
                    String toSend = command;
                    toSend += USEREMAIL+";";
                    toSend += PASSWORD+";";
                    connectionTask = new myClass();
                    try
                    {
                        connectionTask.execute(toSend).get();
                    }catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                    //-------------------------------
                    text = "User Created!";
                }
                System.out.println("MESSAGE!!! "+text);

                Context context = getApplicationContext();
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

        //Button to sign in to account
        Button goToYourActivity = (Button) findViewById(R.id.buttonSignIn);
        assert goToMainActivity != null;
        goToYourActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                startActivity(intent);

                /*TODO
                This is how we can log into the server
                **************************************************
                String command = "::login%%";
                String toSend += command;
                toSend += USEREMAIL;
                toSend += PASSWORD;
                String verification = "";
                connectionTask = new myClass();
                try
                {
                    verification = connectionTask.execute(toSend).get();
                }catch(Exception e)
                {
                    e.printStackTrace();
                }
                //This part is a rough guess, we need to do a little more work to be sure
                if(verification.equals("success"))
                    //login
                else if(verification.equals("wrongpassword"))
                    //re-enter password
                else
                    //email doesn't exist
                **************************************************
                */

            }
        });
    }

    /**
     * myClass:
     * the extension of AsyncTask<String, Void, String> allows for database queries to occur
     * in the background. The information retrieved can be fetched by instance.execute().get()
     */
    private class myClass extends AsyncTask<String, Void, String> {
        String information = "";

        @Override
        protected String doInBackground(String... foo) {
            String info;

            try {
                serverConnection = new ServerConnection(foo[0]);
                serverConnection.run();
                info = serverConnection.gMessage;
                information = info;

                Looper.prepare();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return information;
        }
    }

}
