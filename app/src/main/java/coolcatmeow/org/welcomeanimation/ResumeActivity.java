package coolcatmeow.org.welcomeanimation;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ResumeActivity extends AppCompatActivity {
/*
    public   static String FIRST_NAME = "coolcatmeow.org.welcomeanimation.FIRST_NAME";
    public   static String LAST_NAME = "coolcatmeow.org.welcomeanimation.LAST_NAME";
    public   static String EMAIL = "coolcatmeow.org.welcomeanimation.EMAIL";
    public   static String PHONE = "coolcatmeow.org.welcomeanimation.PHONE";
    public   static String SCHOOL_NAME = "coolcatmeow.org.welcomeanimation.SCHOOL_NAME";
    public   static String MAJOR = "coolcatmeow.org.welcomeanimation.MAJOR";
    public   static String COMPANY_NAME = "coolcatmeow.org.welcomeanimation.COMPANY_NAME";
    public   static String COMPANY_POSITION = "coolcatmeow.org.welcomeanimation.COMPANY_POSITION";
*/
    public  final static String FIRST_NAME = "coolcatmeow.org.welcomeanimation.FIRST_NAME";
    public  final static String LAST_NAME = "coolcatmeow.org.welcomeanimation.LAST_NAME";
    public  final static String EMAIL = "coolcatmeow.org.welcomeanimation.EMAIL";
    public  final static String PHONE = "coolcatmeow.org.welcomeanimation.PHONE";
    public  final static String SCHOOL_NAME = "coolcatmeow.org.welcomeanimation.SCHOOL_NAME";
    public  final static String MAJOR = "coolcatmeow.org.welcomeanimation.MAJOR";
    public  final static String COMPANY_NAME = "coolcatmeow.org.welcomeanimation.COMPANY_NAME";
    public  final static String COMPANY_POSITION = "coolcatmeow.org.welcomeanimation.COMPANY_POSITION";

    private myClass connectionTask = null;
    private ServerConnection serverConnection = null;
    //private UserLoginEmail globalEmail
    private static String loginEmail = LogInActivity.USEREMAIL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(loginEmail == null)
        {
            String text = "Please log in or create an account";
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        Button goToDisplay = (Button) findViewById(R.id.buttonGoToDisplay);
        goToDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* TODO
                    Juan, we should set something up so that, once the user logs in, it pulls data
                    about the resume down from the server and populates the fields. That way, any
                    time the user wants to update something, they don't have to fill in the
                    entire resume section again.

                    We can do this by accessing the user's loginEmail and run the following commands:
                    *********************************************************************************
                    String text = "::getInfo%%" + loginEmail + ";";
                    String userInfo = "";
                    connectionTask = new myClass();
                    try
                    {
                        userInfo = connectionTask.execute(text).get();
                    }catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                    String[] infoArr = userInfo.split(";");
                    ************************************************************************

                    The info will be in the following format when returned so you may properly populate the
                    the resume fields.
                    infoArr[0] = First_Name
                    infoArr[1] = Last_Name
                    infoArr[2] = Email
                    infoArr[3] = Phone
                    infoArr[4] = School_Name
                    infoArr[5] = Major
                    infoArr[6] = Company_Name
                    infoArr[7] = Company_Position
                 */

                //COPY THE TEXT IN THE FIRST NAME
                EditText fn = (EditText) findViewById(R.id.editTextFistName);
                final String firstName = fn.getText().toString();

                EditText ln = (EditText) findViewById(R.id.editTextLastName);
                final String lastName = ln.getText().toString();

                EditText email = (EditText) findViewById(R.id.editTextEmail);
                final String eMail = email.getText().toString();

                EditText phone = (EditText) findViewById(R.id.editTextPhone);
                final String phtext = phone.getText().toString();

                EditText scName =(EditText) findViewById(R.id.editTextSchoolName);
                final String schoolName = scName.getText().toString();

                EditText majorText = (EditText) findViewById(R.id.editTextMajor);
                final String major = majorText.getText().toString();

                EditText companyText = (EditText) findViewById(R.id.editTextWorkName);
                final String companyName = companyText.getText().toString();

                EditText positionText = (EditText) findViewById(R.id.editTextWorkTittle);
                final String companyPosition = positionText.getText().toString();


                Intent save = new Intent(ResumeActivity.this, DisplayResumeActivity.class);
                save.putExtra(FIRST_NAME,firstName);
                save.putExtra(LAST_NAME,lastName);
                save.putExtra(EMAIL, eMail);
                save.putExtra(PHONE,phtext);
                save.putExtra(SCHOOL_NAME,schoolName);
                save.putExtra(MAJOR,major);
                save.putExtra(COMPANY_NAME,companyName);
                save.putExtra(COMPANY_POSITION, companyPosition);

                startActivity(save);

                String command = "::updateResume%%";
                String text= "";
                text += command;
                text += loginEmail +";";
                text += firstName +";";
                text += lastName +";";
                text += eMail +";";
                text += phtext +";";
                text += schoolName +";";
                text += major +";";
                text += companyName +";";
                text += companyPosition +";";
                connectionTask = new myClass();
                try
                {
                    connectionTask.execute(text).get();
                }catch(Exception e)
                {
                    e.printStackTrace();
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

                Looper.prepare();
            }catch (Exception e)
            {
                e.printStackTrace();
            }
            return information;
        }
    }
}
