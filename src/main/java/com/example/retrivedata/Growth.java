package com.example.retrivedata;

import com.google.gson.annotations.SerializedName;

public class Growth {

    @SerializedName("id")
    private int CandidateNo;

    @SerializedName("rating")
    private int Votes;

    public int getCandidate() {
        return CandidateNo;
    }

    public int getVotes() {
        return Votes;
    }
}
