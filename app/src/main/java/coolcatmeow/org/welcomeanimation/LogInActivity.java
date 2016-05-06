package coolcatmeow.org.welcomeanimation;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LogInActivity extends AppCompatActivity {
    public  final static String ID = "coolcatmeow.org.welcomeanimation.ID";
    private myClass connectionTask = null;
    private ServerConnection serverConnection = null;
    public static String USEREMAIL = "";
    public static String PASSWORD = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Save String information

        /*
            Juan, we need to set USEREMAIL equal to the user's login email address. That way we can
            use that to sort out who's data to update when updating the database.
         */

        final EditText myEditText = (EditText) findViewById(R.id.LoginEmail);
        myEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                USEREMAIL = myEditText.getText().toString();
            }
        });


        //Button for Create new Account
        Button goToMainActivity = (Button) findViewById(R.id.buttonNewAccount);
        goToMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // final String firstName = id.getText().toString();

                Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                startActivity(intent);
               /*
               TODO
                This is how we can create a new user on the server
                **************************************************
                String command = "::adduser%%";
                String toSend += command;
                toSend += USEREMAIL;
                toSend += PASSWORD;
                connectionTask = new myClass();
                try
                {
                    connectionTask.execute(toSend).get();
                }catch(Exception e)
                {
                    e.printStackTrace();
                }
                **************************************************
                */
            }
        });

        //Button for sign in to account
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
    private class myClass extends AsyncTask<String, Void, String>
    {
        String information = "";

        @Override
        protected String doInBackground(String... foo)
        {
            String info;

            try
            {
                serverConnection = new ServerConnection(foo[0]);
                serverConnection.run();
                info = serverConnection.gMessage;
                information = info;

                Looper.prepare();
            }catch (Exception e)
            {
                e.printStackTrace();
            }
            return information;
        }
    }

}
