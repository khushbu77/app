package com.example.retrivedata;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;
import static com.example.retrivedata.Constants.URL_VOTE;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context mCtx;
    private List<Product> productList;
    public Button btnVote;

//    private AdapterView.OnItemClickListener mlistener;

    public interface OnItemCLickListener {
        void onbtnClick(int position);
    }

    public ProductAdapter(Context mCtx, List<Product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

//    public void setOnItemViewClickListener(AdapterView.OnItemClickListener listener) {mlistener = listener;}

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.product_list, null);
        final ProductViewHolder vHolder = new ProductViewHolder(view);
        Button btnVote = view.findViewById(R.id.VoteButton);
        Button btnSubmitVote = view.findViewById(R.id.btnSubmitVote);

        vHolder.btnVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = SharedPrefManager.getInstance(mCtx).getUsername();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_VOTE, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Toast.makeText(mCtx, jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            mCtx.startActivity(new Intent(mCtx, ResultActivity.class));


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(mCtx, "You have already voted!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(mCtx, ResultActivity.class);
                        mCtx.startActivity(intent);
                    }
                }) {
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("username", username);
                        return params;
                    }
                };



                RequestHandler.getInstance(mCtx).addToRequestQueue(stringRequest);





            }


        });


        return vHolder;
    }


    @Override
    public void onBindViewHolder(ProductViewHolder holder, final int position) {
        final Product product = productList.get(position);

        //loading the image
        Glide.with(mCtx)
                .load(product.getImage())
                .into(holder.imageView);

        holder.textViewTitle.setText(product.getTitle());
        holder.textViewShortDesc.setText(product.getShortdesc());
        holder.btnVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on " + productList.get(position));
                Toast.makeText(mCtx, "You have selected at position "+productList.get(position).getId(), Toast.LENGTH_SHORT).show();
                Intent myintent = new Intent(mCtx, ResultActivity.class);
                myintent.putExtra("image_url", productList.get(position).getImage());
                myintent.putExtra("image_name", productList.get(position).getTitle());
                mCtx.startActivity(myintent);
            }
        });


    }


    @Override
    public int getItemCount() {

        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc ;
        ImageView imageView;
        Button btnVote,btnSubmitVote;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            imageView = itemView.findViewById(R.id.imageView);
            btnVote = itemView.findViewById(R.id.VoteButton);
            btnSubmitVote = itemView.findViewById(R.id.btnSubmitVote);
        }


        }
    }

