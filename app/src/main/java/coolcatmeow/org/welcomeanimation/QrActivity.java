package coolcatmeow.org.welcomeanimation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class QrActivity extends AppCompatActivity {
    public  final static String GETEMAIL = "coolcatmeow.org.welcomeanimation.GETEMAIL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button goToDisplay = (Button) findViewById(R.id.buttonGetInfo);
        goToDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText SearchEmail = (EditText) findViewById(R.id.editTextWriteEmail2);
                final String lookEmail = SearchEmail.getText().toString();

                Intent intent = new Intent(QrActivity.this, DisplayActivity.class);
                intent.putExtra(GETEMAIL, lookEmail);

                startActivity(intent);
            }
        });
    }

}
