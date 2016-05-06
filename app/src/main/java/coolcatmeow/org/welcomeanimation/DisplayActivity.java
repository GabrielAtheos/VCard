package coolcatmeow.org.welcomeanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //So far whatever you type in the search field will be copied onto the Display Activity
        String receiveEmail = getIntent().getStringExtra(QrActivity.GETEMAIL);
        TextView textEmail = (TextView) findViewById(R.id.email2);
        textEmail.setText(receiveEmail);
    }

}
