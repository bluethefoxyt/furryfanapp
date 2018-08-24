package cf.furryfan.furryfanapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

public class usershome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usershome);
    }


    public void buttonClicked(View view) {

        if (view.getId() == R.id.logoutbtn) {
            Map<String, String> params = new HashMap<String, String>();
            params.put("username", "");
            params.put("password", "");
            this.finish();
        } else if (view.getId() == R.id.vp) {
            startActivity(new Intent(getApplicationContext(), userprofilepage.class));

        } else if (view.getId() == R.id.b) {
            startActivity(new Intent(getApplicationContext(), Browseposts.class));

        }

    }
}
