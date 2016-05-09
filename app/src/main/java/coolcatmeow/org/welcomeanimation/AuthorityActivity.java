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
import android.widget.Toast;

public class AuthorityActivity extends AppCompatActivity {
    public  final static String CHECKEMAIL = "coolcatmeow.org.welcomeanimation.CHECKEMAIL";
    private String toAuthorize = "";
    private String userEmail = null;
    private myClass connectionTask = null;
    private ServerConnection serverConnection = null;

    //TODO: add check to see if the user is logged in

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authority);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        userEmail = getIntent().getStringExtra(MainActivity.USEREMAIL);
        System.out.println("AUTHORIZED AUTHORIZED AUTHORIZED AUTHORIZED " + userEmail);

        final EditText getEmail = (EditText) findViewById(R.id.editTextAuthorizeEmail);
        if(getEmail != null)
            getEmail.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {}
                @Override
                public void afterTextChanged(Editable s) {
                        toAuthorize = getEmail.getText().toString();
                }
            });

        Button authorize = (Button) findViewById(R.id.buttonAuthorize);
        if(authorize != null)
            authorize.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent(AuthorityActivity.this, AuthorityActivity.class);
                    Intent intent2 = new Intent(AuthorityActivity.this, MainActivity.class);

                    String text;
                    String response = "";

                    String toSend = "::addauthorizeduser%%";
                    toSend += userEmail+";";
                    toSend += toAuthorize+";";
                    connectionTask = new myClass();

                    try
                    {
                        response = connectionTask.execute(toSend).get();
                    }catch(Exception e) {
                        e.printStackTrace();
                    }

                    if(response != null)
                        if(response.toLowerCase().equals("nouser")) {
                            text = "Try again";
                            Context context = getApplicationContext();
                            int duration = Toast.LENGTH_LONG;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                            startActivity(intent2);

                            startActivity(intent1);
                        } else {
                            text = "User added";
                            Context context = getApplicationContext();
                            int duration = Toast.LENGTH_LONG;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();

                            startActivity(intent2);
                        }

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

                if(Looper.myLooper() == null)
                    Looper.prepare();
            }catch (Exception e)
            {
                e.printStackTrace();
            }
            return information;
        }
    }

}
