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

public class QrActivity extends AppCompatActivity {
    public  final static String GETEMAIL = "coolcatmeow.org.welcomeanimation.GETEMAIL";
    private String requestedEmail = null;
    private myClass connectionTask = null;
    private ServerConnection serverConnection = null;
    private String USEREMAIL = LogInActivity.USEREMAIL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final EditText getEmail = (EditText) findViewById(R.id.editTextWriteEmail2);
        getEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                requestedEmail = getEmail.getText().toString();
            }
        });

        Button goToDisplay = (Button) findViewById(R.id.buttonGetInfo);
        goToDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "";
                String returned = "";

                String command = "::get_other_resume%%";
                String toSend = command;
                toSend += USEREMAIL+";";
                toSend += requestedEmail+";";
                connectionTask = new myClass();
                try
                {
                    returned = connectionTask.execute(toSend).get();
                }catch(Exception e)
                {
                    e.printStackTrace();
                }

                if() {
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

                Intent intent = new Intent(QrActivity.this, DisplayActivity.class);
                intent.putExtra(GETEMAIL, returned);

                startActivity(intent);
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
