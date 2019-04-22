package com.example.retrivedata;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ResultActivity extends AppCompatActivity {

    private static final String TAG = "ResultActivity";

    private Button btnSubmitVote;

    public String username;
    public String candidate;
    public String candidate_image;

//    TextView title,shortdesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        username = SharedPrefManager.getInstance(this).getUsername();
        candidate = getIntent().getStringExtra("image_name");
        candidate_image = getIntent().getStringExtra("image_url");

        setImage(candidate_image, candidate);
//        Toast.makeText(this, username + " " + candidate, Toast.LENGTH_SHORT).show();

        btnSubmitVote = (Button) findViewById(R.id.btnSubmitVote);
        btnSubmitVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, ChartActivity.class);
                startActivity(intent);
                submitVote();
            }
        });
        Log.d(TAG, "onCreate: started");




    }

    public void submitVote(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_VOTE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Toast.makeText(ResultActivity.this, jsonObject.getString("message"), Toast.LENGTH_LONG).show();

                            if(jsonObject.getString("message").equals("Voted successfully"))
                            {
                                finish();
                                Intent myIntent = new Intent(getApplicationContext(), ChartActivity.class);
                                startActivity(myIntent);

//
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(ResultActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("title", candidate);
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }



    private void setImage(String imageUrl, String imageName){
        Log.d(TAG, "setImage: setting to image and name to widgets");

        TextView name = findViewById(R.id.textViewName);
        name.setText(imageName);

        ImageView image = findViewById(R.id.imageViewCandidate);

        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(image);
    }



}


