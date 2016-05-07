//Make sure to concatenate with ::command%% info1;info2;info...
package coolcatmeow.org.welcomeanimation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Button for the login
        Button goToLogInActivity = (Button) findViewById(R.id.buttonFIVE);
        goToLogInActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LogInActivity.class);
                startActivity(intent);

            }
        });

        //Create a Button and listener to goto different pages.
        Button goToResumeActivity = (Button) findViewById(R.id.buttonONE);
        goToResumeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ResumeActivity.class);
                startActivity(intent);
            }
        });

        Button goToEditActivity = (Button) findViewById(R.id.buttonTWO);
        goToEditActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                startActivity(intent);
            }
        });

        Button goToDisplayActivity = (Button) findViewById(R.id.buttonTHREE);
        goToDisplayActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                startActivity(intent);
            }
        });

        Button goToQrCodeActivity = (Button) findViewById(R.id.buttonFOUR);
        goToQrCodeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QrActivity.class);
                startActivity(intent);
            }
        });

        //new Button to Authorize code
        Button goToAA = (Button) findViewById(R.id.buttonSix);
       goToAA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AuthorityActivity.class);
                startActivity(intent);
            }
        });

    }
}
