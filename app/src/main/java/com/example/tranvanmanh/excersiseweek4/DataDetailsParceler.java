package com.example.tranvanmanh.excersiseweek4;

import org.parceler.Parcel;

/**
 * Created by tranvanmanh on 3/27/2018.
 */
@Parcel
public class DataDetailsParceler {

    private String RealeaseDate;
    private double VoteAvarage;
    private String tille;
    private String discription;
    private String pathpicture;

    public DataDetailsParceler() {
    }

    public DataDetailsParceler(String realeaseDate, double voteAvarage, String tille, String discription, String pathpicture) {
        RealeaseDate = realeaseDate;
        VoteAvarage = voteAvarage;
        this.tille = tille;
        this.discription = discription;
        this.pathpicture = pathpicture;
    }

    public String getRealeaseDate() {
        return RealeaseDate;
    }

    public double getVoteAvarage() {
        return VoteAvarage;
    }

    public String getTille() {
        return tille;
    }

    public String getDiscription() {
        return discription;
    }

    public String getPathpicture() {
        return pathpicture;
    }
}
