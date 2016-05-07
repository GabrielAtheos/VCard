package coolcatmeow.org.welcomeanimation;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogInActivity extends AppCompatActivity {
    private myClass connectionTask = null;
    private ServerConnection serverConnection = null;
    public static String USEREMAIL = null;
    private static String PASSWORD = null;
    private String tempEmail = null;
    private String tempPassword = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        //login email text field
        final EditText emailTextEdit = (EditText) findViewById(R.id.editLoginEmail);
        emailTextEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                    tempEmail = emailTextEdit.getText().toString();
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
                    tempPassword = passwordTextEdit.getText().toString();
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
                if(tempEmail == null || tempPassword == null)
                {
                    text = "Email and password fields must not be left blank";
                }
                else
                {
                    USEREMAIL = tempEmail;
                    PASSWORD = tempPassword;
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
                    text = "User Created!";
                }
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
                Intent intent1 = new Intent(LogInActivity.this, MainActivity.class);
                Intent intent2 = new Intent(LogInActivity.this, LogInActivity.class);
                //startActivity(intent1);

                String text = "";
                String command = "::login%%";
                String toSend = command;
                toSend += tempEmail+";";
                toSend += tempPassword+";";
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
                if(verification.toLowerCase().equals("success")){
                    USEREMAIL = tempEmail;
                    text = "Login successful";
                    System.out.println(text);
                    startActivity(intent1);
                }
                else if(verification.toLowerCase().equals("wrongpassword")){
                    text = "Incorrect password";
                    System.out.println(text);
                    startActivity(intent2);
                }
                else if(verification.toLowerCase().equals("wrongemail")){
                    text = "Incorrect email";
                    System.out.println(text);
                    startActivity(intent2);
                }

                Context context = getApplicationContext();
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
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
