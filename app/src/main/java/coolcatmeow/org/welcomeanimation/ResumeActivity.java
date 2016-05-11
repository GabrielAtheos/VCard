package coolcatmeow.org.welcomeanimation;

import android.app.Activity;
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

import java.util.concurrent.TimeUnit;

public class ResumeActivity extends AppCompatActivity {
    public static String FIRST_NAME = "coolcatmeow.org.welcomeanimation.FIRST_NAME";
    public static String LAST_NAME = "coolcatmeow.org.welcomeanimation.LAST_NAME";
    public static String EMAIL = "coolcatmeow.org.welcomeanimation.EMAIL";
    public static String PHONE = "coolcatmeow.org.welcomeanimation.PHONE";
    public static String SCHOOL_NAME = "coolcatmeow.org.welcomeanimation.SCHOOL_NAME";
    public static String MAJOR = "coolcatmeow.org.welcomeanimation.MAJOR";
    public static String COMPANY_NAME = "coolcatmeow.org.welcomeanimation.COMPANY_NAME";
    public static String COMPANY_POSITION = "coolcatmeow.org.welcomeanimation.COMPANY_POSITION";

    private myClass connectionTask = null;
    private ServerConnection serverConnection = null;
    //private static String loginEmail = LogInActivity.USEREMAIL;
    private static String loginEmail = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        loginEmail = getIntent().getStringExtra(MainActivity.USEREMAIL);

        if (loginEmail == null) {
            String text = "Please log in or create an account";
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            Intent error = new Intent(ResumeActivity.this, MainActivity.class);
            startActivity(error);
        } else {

            String text2 = "::getInfo%%" + loginEmail + ";";
            String userInfo = "";
            connectionTask = new myClass();
            try {
                userInfo = connectionTask.execute(text2).get();
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("USERINFO: " + userInfo);
            String[] infoArr = new String[8];
            if(userInfo != null) {
                infoArr = userInfo.split(";");
            } else {
                Intent error = new Intent(ResumeActivity.this, MainActivity.class);
                startActivity(error);
                for (int i=0;i<8;i++) {
	                infoArr[i] = "no";
                }
            }

            if (!infoArr[0].toLowerCase().equals("no")) {
                EditText editText = (EditText) findViewById(R.id.editTextFistName);
                if(editText != null)
	                editText.setText(infoArr[0]);
            }
            if (!infoArr[1].toLowerCase().equals("no")) {
                EditText editText = (EditText) findViewById(R.id.editTextLastName);
                if(editText != null)
	                editText.setText(infoArr[1]);
            }
            if (!infoArr[2].toLowerCase().equals("no")) {
                EditText editText = (EditText) findViewById(R.id.editTextEmail);
	            if(editText != null)
	                editText.setText(infoArr[2]);
            }
            if (!infoArr[3].toLowerCase().equals("no")) {
                EditText editText = (EditText) findViewById(R.id.editTextPhone);
	            if(editText != null)
	                editText.setText(infoArr[3]);
            }
            if (!infoArr[4].toLowerCase().equals("no")) {
                EditText editText = (EditText) findViewById(R.id.editTextSchoolName);
	            if(editText != null)
	                editText.setText(infoArr[4]);
            }
            if (!infoArr[5].toLowerCase().equals("no")) {
                EditText editText = (EditText) findViewById(R.id.editTextMajor);
	            if(editText != null)
	                editText.setText(infoArr[5]);
            }
            if (!infoArr[6].toLowerCase().equals("no")) {
                EditText editText = (EditText) findViewById(R.id.editTextWorkName);
	            if(editText != null)
	                editText.setText(infoArr[6]);
            }
            if (!infoArr[7].toLowerCase().equals("no")) {
                EditText editText = (EditText) findViewById(R.id.editTextWorkTittle);
	            if(editText != null)
	                editText.setText(infoArr[7]);
            }
        }
        Button goToDisplay = (Button) findViewById(R.id.buttonGoToDisplay);
        if(goToDisplay != null)
		    goToDisplay.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                String foo;
	                String command = "::updateResume%%";
	                String text = "";
	                text += command;
	                text += loginEmail + ";";

	                EditText fn = (EditText) findViewById(R.id.editTextFistName);
	                String firstName = " ";
		            if(fn != null)
		                if (fn.getText().length() != 0) {
		                    foo = firstName = fn.getText().toString();
		                } else {
		                    foo = "no";
		                }
		            else
		                foo = "no";

	                text += foo + ";";

	                EditText ln = (EditText) findViewById(R.id.editTextLastName);
	                String lastName = " ";
		            if(ln != null)
			            if (ln.getText().length() != 0) {
		                    foo = lastName = ln.getText().toString();
		                } else {
		                    foo = "no";
		                }
		            else
			            foo = "no";

	                text += foo + ";";

	                EditText email = (EditText) findViewById(R.id.editTextEmail);
		            String eMail = "";
		            if(email != null)
		                if (email.getText().length() != 0) {
		                    foo = eMail = email.getText().toString();
		                } else {
		                    foo = "no";
		                }
		            else
		                foo = "no";

	                text += foo + ";";

	                EditText phone = (EditText) findViewById(R.id.editTextPhone);
	                String phtext = " ";
		            if(phone != null)
		                if (phone.getText().length() != 0) {
		                    foo = phtext = phone.getText().toString();
		                } else {
		                    foo = "no";
		                }
		            else
			            foo = "no";

	                text += foo + ";";

	                EditText scName = (EditText) findViewById(R.id.editTextSchoolName);
	                String schoolName = " ";
		            if(scName != null)
			            if (scName.getText().length() != 0) {
		                    foo = schoolName = scName.getText().toString();
		                } else {
		                    foo = "no";
		                }
		            else
			            foo = "no";

	                text += foo + ";";

	                EditText majorText = (EditText) findViewById(R.id.editTextMajor);
	                String major = " ";
		            if(majorText != null)
			            if (majorText.getText().length() != 0) {
		                    foo = major = majorText.getText().toString();
		                } else {
		                    foo = "no";
		                }
		            else
			            foo = "no";

	                text += foo + ";";

	                EditText companyText = (EditText) findViewById(R.id.editTextWorkName);
	                String companyName = " ";
		            if(companyText != null)
			            if (companyText.getText().length() != 0) {
		                    foo = companyName = companyText.getText().toString();
		                } else {
		                    foo = "no";
		                }
		            else
			            foo = "no";

	                text += foo + ";";

	                EditText positionText = (EditText) findViewById(R.id.editTextWorkTittle);
	                String companyPosition = " ";
		            if(positionText != null)
			            if (positionText.getText().length() != 0) {
		                    foo = companyPosition = positionText.getText().toString();
		                } else {
		                    foo = "no";
		                }
		            else
			            foo = "no";

	                text += foo + ";";

	                Intent save = new Intent(ResumeActivity.this, DisplayResumeActivity.class);
	                save.putExtra(FIRST_NAME, firstName);
	                save.putExtra(LAST_NAME, lastName);
	                save.putExtra(EMAIL, eMail);
	                save.putExtra(PHONE, phtext);
	                save.putExtra(SCHOOL_NAME, schoolName);
	                save.putExtra(MAJOR, major);
	                save.putExtra(COMPANY_NAME, companyName);
	                save.putExtra(COMPANY_POSITION, companyPosition);

	                startActivity(save);

	                connectionTask = new myClass();
	                try {
	                    connectionTask.execute(text).get();
	                } catch (Exception e) {
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
