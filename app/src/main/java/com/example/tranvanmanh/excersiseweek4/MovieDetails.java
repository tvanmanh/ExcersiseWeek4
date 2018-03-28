package com.example.tranvanmanh.excersiseweek4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

public class MovieDetails extends AppCompatActivity {

    ImageView imvPoster;
    TextView tvTitle;
    TextView tvReleaseDate;
    RatingBar ratingBar;
    TextView tvOverView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        imvPoster = (ImageView)findViewById(R.id.imvposter);
        tvTitle = (TextView) findViewById(R.id.tvtitle);
        tvReleaseDate = (TextView) findViewById(R.id.tvReleaseday);
        ratingBar = (RatingBar) findViewById(R.id.ratingbar);
        tvOverView = (TextView) findViewById(R.id.tvdiscription);

        DataDetailsParceler data = (DataDetailsParceler) Parcels.unwrap(getIntent().getParcelableExtra("data"));
//        Bundle data = getIntent().getExtras();

        if(data != null)
        {
            Picasso.with(this).load(data.getPathpicture()).into(imvPoster);
            tvTitle.setText(data.getTille());
            String ReleaseDate = data.getRealeaseDate();
            double numberStart = ((data.getVoteAvarage()*5)/10);
            ratingBar.setRating((float)numberStart );
            tvReleaseDate.setText("Release Date: " + ReleaseDate);
            tvOverView.setText(data.getDiscription());
        }
    }
}
