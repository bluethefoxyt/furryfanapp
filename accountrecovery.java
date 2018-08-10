package cf.furryfan.furryfanapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class accountrecovery extends AppCompatActivity {
    private Button loginbtn;

    public void goback() {

        this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //animation of login screen
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountrecovery);


    }
}
