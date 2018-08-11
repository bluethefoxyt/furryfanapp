package cf.furryfan.furryfanapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

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
        SharedPreferences mSettings = getApplicationContext().getSharedPreferences("Login", MODE_PRIVATE);
        if (mSettings.getString("loginsuccess", "0").equals("1")) {


            startActivity(new Intent(getApplicationContext(), usershome.class));

        } else {


        }
        Relay1 = findViewById(R.id.Relay1);
        handler.postDelayed(runnable, 2000);

        /* button click events */


    }


    //login verification code
    public void login() {
        final SharedPreferences mSettings = getApplicationContext().getSharedPreferences("Login", MODE_PRIVATE);
        final TextView tv = findViewById(R.id.logintt);
        final EditText username = findViewById(R.id.utb);
        final EditText password = findViewById(R.id.ptb);
        StringRequest postRequest = new StringRequest(Request.Method.POST, "https://furryfan.cf/app/login.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        if (response.equals("1")) {
                            SharedPreferences.Editor editor;
                            editor = mSettings.edit();
                            editor.putString("loginsuccess", "1");
                            editor.putString("username", username.getText().toString());
                            editor.commit();
                            startActivity(new Intent(getApplicationContext(), usershome.class));

                        } else {


                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        password.setBackgroundColor(4);

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username.getText().toString());
                params.put("password", password.getText().toString());

                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(postRequest);
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

