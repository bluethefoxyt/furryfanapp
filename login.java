package cf.furryfan.furryfanapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

public class login extends AppCompatActivity {
    RelativeLayout Relay1;

    Handler handler = new Handler();

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Relay1.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //animation of login screen
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Relay1 = findViewById(R.id.Relay1);
        handler.postDelayed(runnable, 2000);

        /* button click events */


    }


    //login verification code
    public void login() {

    }


    public void buttonClicked(View view) {

        if (view.getId() == R.id.accountrecovery) {
            startActivity(new Intent(getApplicationContext(), accountrecovery.class));
        } else if (view.getId() == R.id.signupbtn) {
            startActivity(new Intent(getApplicationContext(), signup.class));
        } else if (view.getId() == R.id.loginbtn) {
            login();
        }

    }

}

