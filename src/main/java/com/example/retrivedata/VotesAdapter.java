package com.example.retrivedata;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class VotesAdapter extends RecyclerView.Adapter<VotesAdapter.VotesViewHolder> {
    private Context mCtx1;
    private List<Votes> voteList;

    public VotesAdapter(Context mCtx1, List<Votes> voteList) {
        this.mCtx1 = mCtx1;
        this.voteList = voteList;
    }

    @Override
    public VotesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx1);
        View view = inflater.inflate(R.layout.display_votes, null);
        return new VotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VotesViewHolder holder, int position) {
        Votes votes = voteList.get(position);

        //loading the image
        Glide.with(mCtx1)
                .load(votes.getImage1())
                .into(holder.imageView);

        holder.textViewCandidate.setText(votes.getTitle1());

        holder.textViewVotes.setText(String.valueOf(votes.getRating1()));
    }

    @Override
    public int getItemCount() {
        return voteList.size();
    }

    class VotesViewHolder extends RecyclerView.ViewHolder {

        TextView textViewCandidate,textViewVotes;
        ImageView imageView;

        public VotesViewHolder(View itemView) {
            super(itemView);

            textViewCandidate = itemView.findViewById(R.id.textViewCandidate);
            textViewVotes = itemView.findViewById(R.id.textViewVotes);
            imageView = itemView.findViewById(R.id.imageViewResult);
        }
    }
}
