package cf.furryfan.furryfanapp;

import android.app.LauncherActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Browseposts extends AppCompatActivity {

    ListView recyclerView;
    List<LauncherActivity.ListItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browseposts);
        recyclerView = findViewById(R.id.cvr);


        listItems = new ArrayList<>();


        loadRecyclerViewData();
    }

    private void loadRecyclerViewData() {

        final ProgressDialog progressDialog = new ProgressDialog(this);

        progressDialog.setMessage("Getting posts from server");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://furryfan.cf/app/browseview.php", new Response.Listener<String>() {

            @Override
            public void onResponse(String s) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray array = jsonObject.getJSONArray("posts");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject o = array.getJSONObject(i);
                        LauncherActivity.ListItem item;
                        item = new LauncherActivity.ListItem();

                        listItems.add(item);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }
}
