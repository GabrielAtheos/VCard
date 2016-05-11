//Make sure to concatenate with ::command%% info1;info2;info...
package coolcatmeow.org.welcomeanimation;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static String USEREMAIL = null;
	private myClass connectionTask = null;
	private ServerConnection serverConnection = null;
	public static String RESUMEINFO = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		if(getIntent().getStringExtra(LogInActivity.user_email) != null) {
			USEREMAIL = getIntent().getStringExtra(LogInActivity.user_email);
		}
	    if(USEREMAIL != null) {
		    String text2 = "::getInfo%%" + USEREMAIL + ";";
		    String userInfo = "";
		    connectionTask = new myClass();
		    try {
			    userInfo = connectionTask.execute(text2).get();
		    } catch (Exception e) {
			    e.printStackTrace();
		    }
		    if(userInfo.equals(""))
		        RESUMEINFO = userInfo;
	    }

        //Button for the login
        Button goToLogInActivity = (Button) findViewById(R.id.buttonFIVE);
        if(goToLogInActivity != null)
            goToLogInActivity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, LogInActivity.class);
                    startActivity(intent);

                }
            });

        //Create a Button and listener to goto different pages.
        Button goToResumeActivity = (Button) findViewById(R.id.buttonONE);
	    if(goToResumeActivity != null)
	        goToResumeActivity.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                Intent intent = new Intent(MainActivity.this, ResumeActivity.class);
	                intent.putExtra(USEREMAIL,USEREMAIL);
	                startActivity(intent);
	            }
	        });

        Button goToEditActivity = (Button) findViewById(R.id.buttonTWO);
	    if(goToEditActivity != null)
	        goToEditActivity.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                Intent intent = new Intent(MainActivity.this, EditActivity.class);
	                startActivity(intent);
	            }
	        });

        Button goToDisplayActivity = (Button) findViewById(R.id.buttonTHREE);
	    if(goToDisplayActivity != null)
	        goToDisplayActivity.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
	                intent.putExtra(USEREMAIL,USEREMAIL);
		            intent.putExtra(RESUMEINFO,RESUMEINFO);
	                startActivity(intent);
	            }
	        });

        Button goToQrCodeActivity = (Button) findViewById(R.id.buttonFOUR);
	    if(goToQrCodeActivity != null)
	        goToQrCodeActivity.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                Intent intent = new Intent(MainActivity.this, QrActivity.class);
	                intent.putExtra(USEREMAIL,USEREMAIL);
	                startActivity(intent);
	            }
	        });

        //new Button to Authorize code
        Button goToAA = (Button) findViewById(R.id.buttonSix);
	    if(goToAA != null)
	       goToAA.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                Intent intent = new Intent(MainActivity.this, AuthorityActivity.class);
	                intent.putExtra(USEREMAIL,USEREMAIL);
	                startActivity(intent);
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
