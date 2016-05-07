package coolcatmeow.org.welcomeanimation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class AuthorityActivity extends AppCompatActivity {
    public  final static String CHECKEMAIL = "coolcatmeow.org.welcomeanimation.CHECKEMAIL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authority);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Button for the login
        Button goToMainActivity = (Button) findViewById(R.id.button);
        goToMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AuthorityActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });




    }

}
