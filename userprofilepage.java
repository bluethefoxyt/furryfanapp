package cf.furryfan.furryfanapp;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class userprofilepage extends AppCompatActivity {

    TextView mTextView;
    TextView mTextViewbio;

    private String removeHtml(String html) {
        html = html.replaceAll("<(.*?)\\>", " ");
        html = html.replaceAll("<(.*?)\\\n", " ");
        html = html.replaceFirst("(.*?)\\>", " ");
        html = html.replaceAll("&nbsp;", " ");
        html = html.replaceAll("&amp;", " ");
        return html;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofilepage);
        final SharedPreferences mmSettings = getApplicationContext().getSharedPreferences("Login", MODE_PRIVATE);
        StringRequest postRequest = new StringRequest(Request.Method.POST, "https://furryfan.cf/app/profilepicture.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        new DownloadImageTask((ImageView) findViewById(R.id.profilepictureimg))
                                .execute("https://furryfan.cf/" + response);

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("user", mmSettings.getString("username", "t"));

                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(postRequest);
        mTextView = findViewById(R.id.usernamebox);
        mTextView.setText(mmSettings.getString("username", "errorcode: 334"));


        //bio load -----------------------

        StringRequest por = new StringRequest(Request.Method.POST, "https://furryfan.cf/app/bio.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        mTextView = findViewById(R.id.biobox);
                        mTextView.setText(removeHtml(response));

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("user", mmSettings.getString("username", "t"));

                return params;
            }
        };

        RequestQueue q = Volley.newRequestQueue(this);
        q.add(por);
    }


}

class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}
