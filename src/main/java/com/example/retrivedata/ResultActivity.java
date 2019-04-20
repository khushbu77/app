package com.example.retrivedata;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ResultActivity extends AppCompatActivity {

    private static final String TAG = "ResultActivity";

//    TextView title,shortdesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Log.d(TAG, "onCreate: started");

        getIncomingIntent();
    }

    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents");

        if(getIntent().hasExtra("image_url")&& getIntent().hasExtra("image_name")){
            Log.d(TAG, "getIncomingIntent: found intent extras");

            String imageUrl = getIntent().getStringExtra("image_url");
            String imageName = getIntent().getStringExtra("image_name");

            setImage(imageUrl,imageName);
        }
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


//        setTitle();
//        setShortdesc();
//
//    }
//
//    public void setTitle(){
//        title=findViewById(R.id.textViewTitle);
//        title.setText("Name "+getIntent().getStringExtra("title"));
//    }
//
//    public void setShortdesc(){
//        shortdesc=findViewById(R.id.textViewShortDesc);
//        shortdesc.setText("Shortdesc "+getIntent().getStringExtra("shortdesc"));
//    }



//    Bundle bundle = getIntent().getExtras();
//    String title = bundle.getString("title");
//    String shortdesc = bundle.getString("shortdesc");

