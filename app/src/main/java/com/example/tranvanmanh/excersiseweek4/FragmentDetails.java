package com.example.tranvanmanh.excersiseweek4;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

/**
 * Created by tranvanmanh on 3/27/2018.
 */

public class FragmentDetails extends Fragment {

    ImageView imvPoster;
    TextView tvTitle;
    TextView tvReleaseDate;
    RatingBar ratingBar;
    TextView tvOverView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_details, container, false);
        imvPoster = (ImageView)view.findViewById(R.id.imvposter);
        tvTitle = (TextView) view.findViewById(R.id.tvtitle);
        tvReleaseDate = (TextView) view.findViewById(R.id.tvReleaseday);
        ratingBar = (RatingBar) view.findViewById(R.id.ratingbar);
        tvOverView = (TextView) view.findViewById(R.id.tvdiscription);
        return view;
    }
}
