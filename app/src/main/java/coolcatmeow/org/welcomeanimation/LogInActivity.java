package coolcatmeow.org.welcomeanimation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    public static String USEREMAIL = null ;
    public static String user_email = null;
    private static String PASSWORD = null;
    private String tempEmail = null;
    private String tempPassword = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        //login email text field
        final EditText emailTextEdit = (EditText) findViewById(R.id.editLoginEmail);
        if(emailTextEdit != null)
            emailTextEdit.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {}
                @Override
                public void afterTextChanged(Editable s) {
                    try {
                        tempEmail = emailTextEdit.getText().toString();
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            });


        //password text field
        final EditText passwordTextEdit = (EditText) findViewById(R.id.editPasswordText);
        if(passwordTextEdit != null)
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
        if(goToMainActivity != null)
            goToMainActivity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LogInActivity.this, MainActivity.class);

                    String text;
                    if(tempEmail == null || tempPassword == null)
                    {
                        text = "Email and password fields must not be left blank";
                    }
                    else
                    {
                        USEREMAIL = tempEmail;
                        PASSWORD = tempPassword;
                        String toSend = "::adduser%%";
                        toSend += USEREMAIL+";";
                        toSend += PASSWORD+";";
                        connectionTask = new myClass();
                        intent.putExtra(user_email,tempEmail);
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

                    intent.putExtra(user_email, tempEmail);

                    startActivity(intent);
                }
            });

        //Button to sign in to account
        Button goToYourActivity = (Button) findViewById(R.id.buttonSignIn);
        if(goToYourActivity != null)
            goToYourActivity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent(LogInActivity.this, MainActivity.class);
                    Intent intent2 = new Intent(LogInActivity.this, LogInActivity.class);

                    USEREMAIL = null;

                    USEREMAIL = tempEmail;

                    String text = "";
                    String toSend = "::login%%";
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

                    if(verification != null) {
                        if (verification.toLowerCase().equals("success")) {
                            String resumeInfo;
                            intent1.putExtra(user_email, tempEmail);
                            text = "Login successful";
                            startActivity(intent1);
                        } else if (verification.toLowerCase().equals("wrongpassword")) {
                            text = "Incorrect password";
                            System.out.println(text);
                            //finish();
                            startActivity(intent2);
                        } else if (verification.toLowerCase().equals("wrongemail")) {
                            text = "Incorrect email";
                            System.out.println(text);
                            //finish();
                            startActivity(intent2);
                        }
                    }else {
                        text = "unknown error";
                    }

                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    startActivity(intent1);
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

                if(Looper.myLooper() == null)
                    Looper.prepare();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return information;
        }
    }

}
